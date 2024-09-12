package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import org.itstep.config.BotConfig;
import org.itstep.model.User;
import org.itstep.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private final BotConfig botConfig;
    @Autowired
    private UserRepository userRepository;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;

        //добавляем меню
        List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/mydata", "get your data stored"));
        botCommands.add(new BotCommand("/delete", "delete your data"));
        botCommands.add(new BotCommand("/help", "info how to use the bot"));
        botCommands.add(new BotCommand("/settings", "set your preferences"));
        botCommands.add(new BotCommand("/register", "register"));
        try {
            //добавляем меню по дефолту
            this.execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(),null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot`s command");
            throw new RuntimeException(e);
        }


    }

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
            System.out.println(firstName+"   "+chatId);

            System.out.println("name user "+firstName);
            switch (messageText){
                case "/hello":
                    start(chatId,firstName);
                    break;
                case "/help":
                    sendMessage(chatId,"Demo menu");
                    break;
                case "/start":
                    registerUser(update.getMessage());
                    break;
                case "/register":
                    register(chatId);
                    break;
                default:
                    sendMessage(chatId,"Sorry, the command was not recognized");
            }
        }
        else if (update.hasCallbackQuery()){
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String callbackData = update.getCallbackQuery().getData();
            String text = "";
            if (callbackData.equals("YES_BUTTON")){
                text ="You pressed YES button";
            }
            else if (callbackData.equals("NO_BUTTON")){
                text ="You pressed NO button";
            }
            SendMessage message = new SendMessage();
            //EditMessageText message = new EditMessageText();
            message.setChatId(String.valueOf(chatId));
            message.setText(text);
            //message.setMessageId((int)messageId);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Error occured: "+ e.getMessage());
            }
        }
    }
    private void start (long chatId, String firstName){
        String answer = String.format("Hi, %s!",firstName);
        sendMessage (chatId, answer);
    }

    private void register (long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you really want to register?");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyBoard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        var yesButton = new InlineKeyboardButton("yes");
        yesButton.setCallbackData("YES_BUTTON");
        var noButton = new InlineKeyboardButton("no");
        noButton.setCallbackData("NO_BUTTON");
        row.add(yesButton);
        row.add(noButton);
        keyBoard.add(row);
        inlineKeyboardMarkup.setKeyboard(keyBoard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

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
    private void registerUser (Message message){
        //существует ли в бд данный пользователь
        if (userRepository.findById(message.getChatId()).isEmpty()){
            User user = new User(message.getChatId(),
                    message.getChat().getFirstName(),
                    message.getChat().getLastName(),
                    message.getChat().getUserName(),
                    new Timestamp(System.currentTimeMillis()));


            userRepository.save(user);
            log.info("user saved: "+user);
        }

    }

}
