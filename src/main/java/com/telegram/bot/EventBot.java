package com.telegram.bot;

import com.telegram.bot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Semion Rutshtein
 */
@Component
public class EventBot extends TelegramLongPollingBot {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;



//    @Override
//    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), message);
//    }
//
//
//
//    public synchronized void sendMsg(String chatId, String s) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(s);
//        try {
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            System.out.println(e.getMessage());
//        }
//    }



    @Override
    public void onUpdateReceived(Update update) {

        Integer id = update.getMessage().getFrom().getId();
        System.out.println( "User ID: " + id);

        String userName = update.getMessage().getFrom().getUserName();
        ResponseEntity<User> user = restTemplate.exchange(userServiceUrl + "/users?telegramId=" + id + "&name=" + userName,
                HttpMethod.POST,
                new HttpEntity<>(new HttpHeaders()),
                User.class);

        System.out.println(user.getBody());
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {

    }

    @Override
    public String getBotUsername() {
        return "createEventsTaskHelpBotTestBot";
    }

    @Override
    public String getBotToken() {
        return "1463112520:AAHsCOVAapPDxltAUJFAWavDL3viXg_8WFg";
    }

//    @Override
//    public String getBotPath() {
//        return "https://4174dadf47a8.ngrok.io";
//    }


}
