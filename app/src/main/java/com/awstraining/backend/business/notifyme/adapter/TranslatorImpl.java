package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.awstraining.backend.business.notifyme.NotifyMeDO;
import com.awstraining.backend.business.notifyme.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslatorImpl implements Translator {

    private static final Logger LOGGER = LogManager.getLogger(TranslatorImpl.class);
    private final AmazonTranslate amazonTranslate;

    @Autowired
    public TranslatorImpl(AmazonTranslate amazonTranslate) {
        this.amazonTranslate = amazonTranslate;
    }
    
    @Override
    public String translate(NotifyMeDO notifyMeDO) {
        String text = notifyMeDO.text();
        String sl = notifyMeDO.sourceLc();
        String tl = notifyMeDO.targetLc();
        TranslateTextRequest ttr = new TranslateTextRequest().withText(text).withSourceLanguageCode(sl).withTargetLanguageCode(tl);

        TranslateTextResult ttl = amazonTranslate.translateText(ttr);
        LOGGER.info("Translated text from '{}' to '{}'.\n Source: '{}' \n Target: '{}'",sl,tl,text,ttl.getTranslatedText());
        return ttl.getTranslatedText();
    }
}
