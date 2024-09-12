package org.itstep.service;

import lombok.RequiredArgsConstructor;
import org.itstep.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private  BotConfig botConfig;

    @Override
    public String getBotUsername() {
        System.out.println("name bot telegram "+botConfig.getBotName());
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        System.out.println("token bot telegram "+botConfig.getToken());
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            System.out.println(messageText);
            long chatId  =  update.getMessage().getChatId();
            String firstName = update.getMessage().getChat().getFirstName();

            System.out.println("name user "+firstName);
            switch (messageText){
                case "/hello":
                    start(chatId,firstName);
                    break;
                default:
                    sendMessage(chatId,"Sorry, the command was not recognized");
            }
        }
    }
    private void start (long chatId, String firstName){
        String answer = String.format("Hi, %s!",firstName);
        sendMessage (chatId, answer);
    }

    private void sendMessage(long chatId, String answer) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(answer);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
