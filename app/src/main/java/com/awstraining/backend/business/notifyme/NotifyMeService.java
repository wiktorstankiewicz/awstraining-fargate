package com.awstraining.backend.business.notifyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyMeService {

    private final Translator translator;
    private final Sentiment sentiment;
    // TODO: lab1
    //  1. Inject MessageSender.
    private MessageSender messageSender;
    // TODO lab2
    //  1. Inject Translator
    // TODO lab3
    //  1. Inject sentiment detector
    @Autowired
    public NotifyMeService(MessageSender messageSender, Translator translator, Sentiment sentiment) {
        this.messageSender = messageSender;
        this.translator = translator;
        this.sentiment = sentiment;
    }
    
    public String notifyMe(NotifyMeDO notifyMe) {
      
        // TODO: lab1
        //  1. Send text using sender.
        //  2. Return sent message.
        String text = notifyMe.text();
        messageSender.send(text);
        String sentiment = this.sentiment.detectSentiment(notifyMe.sourceLc(),notifyMe.text());
        String translatedText = translator.translate(notifyMe);

        return sentiment + ": " + translatedText;
        // TODO: lab2
        //  1. Translate text from using translator.
        //  2. Change sending of text to "translated text" and return it.
        // TODO: lab3
        //  1. Detect sentiment of translated message.
        //  2. Change sending of text to "setiment: translated text" and return it.
    }
    
}
