package com.example.remindBot.bot;


import com.example.remindBot.entities.*;
import com.example.remindBot.repositories.EventRepository;
import com.example.remindBot.repositories.SpaceRepository;
import com.example.remindBot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

@Component
public class ReminderBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    String botName;
    @Value("7799997920:AAFfjE5HyN0FDk9v7_rstEG84Mps_gGdWZo")
    String botToken;

    //private final RecipeHandler recipeHandler;
    public ReminderBot(@Value("7799997920:AAFfjE5HyN0FDk9v7_rstEG84Mps_gGdWZo") String token, EventRepository eventRepository, UserRepository userRepository, SpaceRepository spaceRepository) {
        super(token);
        // this.recipeHandler=recipeHandler;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.spaceRepository = spaceRepository;
    }
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SpaceRepository spaceRepository;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.getMessage() != null) {
                Long userId = update.getMessage().getFrom().getId();
                SendMessage sendMessage=processMessage(update.getMessage(),userId);
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private SendMessage processMessage(Message message, Long userId) {
        StringBuilder text=new StringBuilder();
        switch (message.getText()){
            case "/events"->text=getEvents(Math.toIntExact(userId));
            case "/tasks"->text=getTasks(Math.toIntExact(userId));
        }
        return new SendMessage(Long.toString(userId),text.toString());

    }

    private StringBuilder getTasks(int user) {
        StringBuilder sb=new StringBuilder("-----Задачи-----\n\n");
        User myUser=userRepository.findByTgId(Integer.toString(user));
        if(myUser!=null) {
            List<Space> spaces=spaceRepository.findByOwnerAndIsPersonal(myUser.getId(),true);
            spaces.addAll(spaceRepository.findByAttendee(myUser.getId()));
            List<Change> changes=spaces.stream()
                    .flatMap(c->c.getTasks().stream().flatMap(t->t.getChanges().stream()))
                    .filter(c->c.getUserId()==myUser.getId()&&!c.getStatus().equals("сделано"))
                    .toList();
            List<Task> tasks=spaces.stream()
                    .flatMap(s->s.getTasks().stream())
                    .filter(t->changes.stream().anyMatch(c->c.getTask().getId()==t.getId()))
                    .sorted((t1,t2)->{
                        if(t1.getDeadline().equals(t2.getDeadline())){
                            return Integer.compare(t1.getPriority(),t2.getPriority());
                        }return t1.getDeadline().compareTo(t2.getDeadline());
                    })
                    .toList();
            tasks.forEach(t->{
                sb.append(t.getName()).append("\n").append(t.getDeadline()).append("\n").append(intPriorityToStringPriority(t.getPriority()));
                sb.append("\n").append("---------------------------\n");
            });

        }
        return sb;
    }

    private StringBuilder getEvents(int user){
        StringBuilder sb=new StringBuilder("-----События-----\n\n");
        User myUser=userRepository.findByTgId(Integer.toString(user));
        if(myUser!=null) {
            List<Space> spaces=spaceRepository.findByOwnerAndIsPersonal(myUser.getId(),true);
            spaces.addAll(spaceRepository.findByAttendee(myUser.getId()));
            List<Event> events=spaces.stream()
                    .flatMap(s->s.getEvents().stream())
                    .sorted((e1,e2)->{
                        if (e1.getStartDate().equals(e2.getStartDate())){
                            return e1.getStartTime().compareTo(e2.getStartTime());
                        } return e2.getStartDate().compareTo(e1.getStartDate());
                    })
                    .toList();
            events.forEach(e -> {
                sb.append(e.getName()).append(" ").append(e.getPlace()).append("\n").append(e.getStartDate()).append(" ").append(e.getStartTime());
                sb.append("\n").append("---------------------------\n");
            });
        }
        return sb;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public void sendMessage(Event eventToRemind, User user) {
            StringBuilder sb=new StringBuilder("Напоминание!\n");
            sb.append(eventToRemind.getName()).append(" ").append(eventToRemind.getPlace()).append("\n").append(eventToRemind.getStartDate()).append(" ").append(eventToRemind.getStartTime());
            SendMessage sendMessage=new SendMessage(user.getTgId(),sb.toString());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
    }

    public static String intPriorityToStringPriority(int i){
        String res;
        switch (i){
            case 1->res="не важно";
            case 2->res="важно";
            case 3-> res="очень важно";
            default -> res="";
        }
        return res;
    }
}