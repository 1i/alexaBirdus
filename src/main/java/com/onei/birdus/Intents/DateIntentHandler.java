package com.onei.birdus.Intents;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.onei.birdus.BirdusS3Client;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class DateIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        log.debug("input", input);
        return input.matches(Predicates.intentName("dateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "DateIntent";
        log.debug(speechText);

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot date = slots.get("date");
        String slotValue = (date != null) ? date.getValue() : "null";
        log.debug("slotValue " + slotValue);
        log.debug("slot date " + date);
        BirdusS3Client birdusS3Client = new BirdusS3Client();
        String results = birdusS3Client.getResultsForDate(slotValue);
        return input.getResponseBuilder()
                .withSpeech(results)
                .withSimpleCard("Results for " + slotValue, results)
                .build();
    }

}