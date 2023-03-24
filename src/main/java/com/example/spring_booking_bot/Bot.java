package com.example.spring_booking_bot;

import com.example.spring_booking_bot.commands.LogInCommand;
import com.example.spring_booking_bot.commands.WorkerCommand;
import com.example.spring_booking_bot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class Bot extends TelegramLongPollingBot {
  /*  public Bot(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    final
    UserRepository userRepository;*/
    @Override
    public String getBotUsername() {
        return "manager_medicine_bot";
    }

    @Override
    public String getBotToken(){
        return "6250025485:AAHlrOJDQzkns3okARySFFcScqlE-wc07YI";
    }
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите действие");
        KeyboardRow k = new KeyboardRow();
       /*if (userRepository.findUserModelByTgId(update.getMessage().getFrom().getId().toString())==null) */

        k.add(new KeyboardButton("Зарегистрироваться"));
        k.add(new KeyboardButton("Записаться к врачу"));


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(k));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        List<WorkerCommand> list = new ArrayList<>();
        list.add(new LogInCommand());

        for(WorkerCommand w: list){
            if (w.start(update)!=null){
                sendMessage = w.start(update);
                break;
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


  /*  @Override
    public void onRegister() {
        super.onRegister();
    }*/
}
