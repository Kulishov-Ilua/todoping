package com.example.todobackend.services;

import com.example.todobackend.dto.*;
import com.example.todobackend.entities.*;
import com.example.todobackend.exception.NoAccessToSpace;
import com.example.todobackend.exception.SpaceNotFoundException;
import com.example.todobackend.mappers.PriorityMapper;
import com.example.todobackend.repositories.SpaceRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final ChangeService changeService;
    private final EventService eventService;
    private final TaskService taskService;
    private final AttendeeService attendeeService;

    public List<Space> findByOwnerAndPersonal(int owner,boolean isPersonal){
        return spaceRepository.findByOwnerAndIsPersonal(owner,isPersonal);
    }
    public List<Space> findByUser(int owner){
        List<Space> spaces=spaceRepository.findByOwner(owner);
        spaces.addAll(spaceRepository.findByAttendee(owner));
        return spaces;
    }

    public List<ShortSpaceDto> getShortSpaceDtoFromSpaceList(List<Space> spaces){
        return spaces.stream()
                .map(s->new ShortSpaceDto(s.getId(),s.getName(),s.getColor()))
                .toList();
    }

    public Space findSpaceById(int spaceId,int userId){
        Space space=spaceRepository.findById(spaceId).orElseThrow(()->new SpaceNotFoundException("Пространнство не найдено!"));
        if(space.getAttendees().stream().noneMatch(a->a.getUser().getId()==userId)&&space.getOwner()!=userId){
            throw new NoAccessToSpace("Вы не можете посмотреть данное пространство!");
        }
        return space;
    }

    public BigSpaceDto getBigSpaceDtoFromSpace(Space space,int userId) {

        String accessStatus="admin";
        if(space.getOwner()!=userId){
            accessStatus=space.getAttendees().stream()
                .filter(a->a.getUser().getId()==userId)
                .findFirst().orElseThrow(()->new NoAccessToSpace("Вы не включены в пространство"))
                .getAccessStatus();}
        List<ShortEventDto> events=space.getEvents().stream()
                .map(e->new ShortEventDto(e.getId(),e.getName(),e.getStartDate().toString(),e.getStartTime().toString(),e.getPlace()))
                .toList();
        List<ShortTaskDto> tasks=space.getTasks().stream()
                .map(t-> new ShortTaskDto(t.getId(),t.getName(),t.getDeadline().toString(), "",PriorityMapper.intPriorityToStringPriority(t.getPriority())))
                .toList();
        if(space.getOwner() != userId || space.isPersonal()){
            tasks.forEach(t->{
                t.setStatus(changeService.findChangeByTaskAndUser(t.getId(),userId).getStatus());
            });
        }
        List<AttendeeDto> attendees=space.getAttendees().stream()
                .map(a->new AttendeeDto(a.getUser().getId(),a.getUser().getName()))
                .toList();
        return new BigSpaceDto(space.getId(),space.getName(),accessStatus,space.getCode(),space.isPersonal(),events,tasks,attendees);
    }

    public List<TaskStatisticDto> getTestStatisticDtoListFromSpace(Space space, int userId){
        List<Task> tasks=space.getTasks();
        int attendeeCount=space.getAttendees().size();
        List<TaskStatisticDto>  taskStatisticDto=new ArrayList<>();
        tasks.forEach(t->{
             List<Change> changes=t.getChanges();
             changes=changes.stream()
                     .filter(c->c.getStatus().equals("сделано"))
                     .toList();
            long avgMs=changes.stream()
                    .mapToLong(c->c.getEndDate().getTime()-c.getStartDate().getTime())
                    .sum();
            long avg = TimeUnit.MILLISECONDS.toDays(avgMs);
            String label="день";
            if(avg==0){
                label="час";
                avg=TimeUnit.MILLISECONDS.toHours(avgMs);
            }
            taskStatisticDto.add(new TaskStatisticDto(t.getId(),t.getName(),t.getDescription(),t.getDeadline().toString(),PriorityMapper.intPriorityToStringPriority(t.getPriority()),changes.size(),attendeeCount,avg,label));
        });
        return taskStatisticDto;
    }
    public TaskUserStatisticDto getUserStatisticDtoFromSpace(Space space,int userId){
        List<Task> tasks=space.getTasks();
        List<Change> changes=tasks.stream()
                .flatMap(t->t.getChanges().stream())
                .toList();
        int allTaskCount=tasks.size();
        int doneTaskCount=(int)changes.stream()
                .filter(c->c.getUserId()==userId&&c.getStatus().equals("сделано"))
                .count();
        int lateTaskCount=(int)changes.stream()
                .filter(c->c.getUserId()==userId&&isTaskDoneLate(c.getTask(),c))
                .count();
        List<Long> avgUsers=changes.stream()
                .filter(c->c.getStatus().equals("сделано")&&c.getUserId()!=userId)
                .map(c->c.getEndDate().getTime()-c.getStartDate().getTime())
                .toList();
        Long avgMe=changes.stream()
                .filter(c->c.getUserId()==userId&&c.getStatus().equals("сделано"))
                .mapToLong(c->c.getEndDate().getTime()-c.getStartDate().getTime())
                .sum();
        int avgCount=(int)avgUsers.stream()
                .filter(a->a>avgMe)
                .count();
        long avg = TimeUnit.MILLISECONDS.toDays(avgMe);
        String label="день";
        if(avg==0){
            label="час";
            avg=TimeUnit.MILLISECONDS.toHours(avgMe);
        }
        return new TaskUserStatisticDto(doneTaskCount,lateTaskCount,allTaskCount,(int)avg,label,avgCount);


    }

    public boolean isTaskDoneLate(Task task,Change change){
        boolean res=true;
        Date date=new Date();

        Date deadline=Date.from(task.getDeadline().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(change.getStatus().equals("сделано")&&change.getEndDate().compareTo(deadline)<=0){
            res=false;
        }
        else if(!change.getStatus().equals("сделано")&&!LocalDate.now().isAfter(task.getDeadline())){
            res=false;
        }
        return res;
    }

    public void addEventInSpace(int spaceId, CreateEventRequest event, int userId) {
        Space space=spaceRepository.findById(spaceId).orElseThrow(()->new SpaceNotFoundException("Пространнство не найдено!"));
        if(space.getOwner()!=userId){
            throw new NoAccessToSpace("Вы не можете добавлять события в данное пространство!");
        }
        Event newEvent=new Event();
        newEvent.setName(event.getName());
        newEvent.setOwner(userId);
        newEvent.setSpaceId(spaceId);
        newEvent.setDescription(event.getDescription());
        newEvent.setPlace(event.getPlace());
        newEvent.setStartDate(event.getStartDate().toLocalDate());
        newEvent.setStartTime(event.getStartDate().toLocalTime());
        newEvent.setEndTime(event.getEndDate().toLocalTime());
        newEvent.setBusy(event.isBusy());
        newEvent.setReminders(event.getReminder());
        eventService.saveEvent(newEvent);
    }

    public void createSpace(CreateSpaceRequest space, int userId) {
        Space mySpace=new Space();
        String code=generateCode();
        while(spaceRepository.existsByCode(code)){
            code=generateCode();
        }
        mySpace.setCode(code);
        mySpace.setName(space.getName());
        mySpace.setDescription(space.getDescription());
        mySpace.setOwner(userId);
        mySpace.setPersonal(space.isPersonal());
        mySpace.setColor(space.getColor());
        spaceRepository.save(mySpace);
    }
    private String generateCode(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public void addTaskInSpace(int spaceId, CreateTaskRequest task, int userId) {
        Space space=spaceRepository.findById(spaceId).orElseThrow(()->new SpaceNotFoundException("Пространнство не найдено!"));
        if(space.getOwner()!=userId){
            throw new NoAccessToSpace("Вы не можете добавить задачи в данное пространство!");
        }
        Task createdTask=taskService.addTask(task,spaceId);
        changeService.addChangesToAttendees(createdTask,space.getAttendees());
        if(space.isPersonal()){
            changeService.addChangesToOwner(createdTask,space.getOwner());
        }
    }

    public List<Space> findByAttendee(int attendeeId){
        return spaceRepository.findByAttendee(attendeeId);
    }

    public void addUserInSpace(String code,int userId) {
        Space space = spaceRepository.findByCode(code);
        if(space==null){
            throw new SpaceNotFoundException("Пространнство не найдено!");

        }
        if (space.getOwner() == userId || space.getAttendees().stream().filter(a -> a.getUser().getId() == userId).findFirst().orElse(null) != null) {
            throw new NoAccessToSpace("Вы уже являетесь участником данного пространства!");
        }
        if (space.getCode().equals(code)) {
            User newUser = new User();
            newUser.setId(userId);
            Attendee attendee = new Attendee();
            attendee.setSpace(space);
            attendee.setUser(newUser);
            attendee.setAccessStatus("user");
            attendeeService.saveAttendee(attendee);
            space.setCode(generateCode());
            spaceRepository.save(space);
            List<Change> changes = space.getTasks().stream()
                    .map(t -> new Change(t, userId, "надо сделать"))
                    .toList();
            changeService.addChangesToAttendee(changes);
        }
    }
}
