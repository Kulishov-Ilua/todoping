package com.example.todobackend.services;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.dto.ShortEventDto;
import com.example.todobackend.dto.ShortTaskDto;
import com.example.todobackend.dto.UserDto;
import com.example.todobackend.entities.*;
import com.example.todobackend.exception.UserAlreadyExistsException;
import com.example.todobackend.exception.UserNotFoundException;
import com.example.todobackend.mappers.PriorityMapper;
import com.example.todobackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ChangeService changeService;
    private final SpaceService spaceService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userRepository.findByLogin(login);
        if(user==null){
            throw new UsernameNotFoundException(String.format("User '%s' not founded", login));
        }
        return new ExtendUserDetails(user.getId(),user.getLogin(), user.getPassword(), "authority");
    }
    public ExtendUserDetails createUser(User user){
        if(userRepository.existsByLogin(user.getLogin())){
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует.");
        }
        user=userRepository.save(user);
        return new ExtendUserDetails(user.getId(),user.getLogin(), user.getPassword(), "authority");
    }

    public User findUserById(int userId){
        return userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDto getUserDtoFromUser(User user){
        List<Space> spaces=spaceService.findByOwnerAndPersonal(user.getId(),true);
        spaces.addAll(spaceService.findByAttendee(user.getId()));
        List<ShortEventDto> events=spaces.stream()
                .flatMap(s->s.getEvents().stream())
                .sorted((e1,e2)->{
                    if (e1.getStartDate().equals(e2.getStartDate())){
                        return e1.getStartTime().compareTo(e2.getStartTime());
                    } return e2.getStartDate().compareTo(e1.getStartDate());
                })
                .map(e->new ShortEventDto(e.getId(),e.getName(),e.getStartDate().toString(),e.getStartTime().toString(),e.getPlace()))
                .toList();
        List<Change> changes=spaces.stream()
                .flatMap(c->c.getTasks().stream().flatMap(t->t.getChanges().stream()))
                .filter(c->c.getUserId()==user.getId())
                .toList();

        List<Change> undoneChanges=changes.stream()
                .filter(c->!c.getStatus().equals("сделано"))
                .toList();
        List<Change> doneChanges=changes.stream()
                .filter(c->c.getStatus().equals("сделано"))
                .toList();

        Long avgMe=changes.stream()
                .filter(c->c.getUserId()==user.getId()&&c.getStatus().equals("сделано"))
                .mapToLong(c->c.getEndDate().getTime()-c.getStartDate().getTime())
                .sum();
        long avg = TimeUnit.MILLISECONDS.toDays(avgMe);
        String label="день";
        if(avg==0){
            label="час";
            avg=TimeUnit.MILLISECONDS.toHours(avgMe);
        }
        int lateTaskCount=(int)changes.stream()
                .filter(c->c.getUserId()==user.getId()&&spaceService.isTaskDoneLate(c.getTask(),c))
                .count();
        List<ShortTaskDto> tasks=spaces.stream()
                .flatMap(s->s.getTasks().stream())
                .filter(t->undoneChanges.stream().anyMatch(c->c.getTask().getId()==t.getId()))
                .sorted((t1,t2)->{
                    if(t1.getDeadline().equals(t2.getDeadline())){
                        return Integer.compare(t1.getPriority(),t2.getPriority());
                    }return t1.getDeadline().compareTo(t2.getDeadline());
                })
                .map(t->new ShortTaskDto(t.getId(),t.getName(),undoneChanges.stream().filter(c->c.getTask().getId()==t.getId()).findFirst().orElse(new Change()).getStatus(),t.getDeadline().toString(), PriorityMapper.intPriorityToStringPriority(t.getPriority())))
                .toList();

        return new UserDto(user.getId(),user.getName(),events,tasks,doneChanges.size(),lateTaskCount,changes.size(),(int)avg,label);
    }

    public void setTg(String id, int user) {
        User authUser=userRepository.findById(user).orElseThrow(()->new UserNotFoundException("пользователь не найден"));
        List<User> users=userRepository.findByTgId(id);
        users.forEach(u->u.setTgId(null));
        userRepository.saveAll(users);
        authUser.setTgId(id);
        userRepository.save(authUser);
    }
}
