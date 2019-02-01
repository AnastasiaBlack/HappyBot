package com.happybot.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HappyBot extends TelegramLongPollingBot {
    private MessageContentBuilder messageContentBuilder = new MessageContentBuilder();

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.getText().equalsIgnoreCase("/sing")) {
            sendMessage("https://youtu.be/7wtfhZwyrcc", message.getChatId());
        }
        else if (message.getText().equalsIgnoreCase("/w")) {
            sendMessage(messageContentBuilder.getTodaysWeather(), message.getChatId());
        }
        else {
            sendMessage(messageContentBuilder.getQuote(), message.getChatId());
        }
    }

    public String getBotUsername() {
        return "your_happy_bot";
    }

    public String getBotToken() {
        return "674150184:AAGimRiDb99gAwsEbwRW_B-oaiQXfLVNMO4";
    }

    private void sendMessage(String content, Long chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(content);
        try {
            execute(sendMessage);
        } catch (TelegramApiException te) {
            te.getMessage();
        }
    }
}
