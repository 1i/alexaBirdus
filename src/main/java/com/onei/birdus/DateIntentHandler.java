package com.onei.birdus;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class DateIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("DateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "DateIntent";
        log.info(speechText);

        Request request1 =  input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Request request = input.getRequest();
        String county = (String) input.getAttributesManager().getSessionAttributes().get("date");
        System.out.println("date "+ county);
        System.out.println("Request " + request);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("DateIntent", speechText)
                .build();
    }

}