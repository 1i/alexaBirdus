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
public class LocationIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("locationIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "LocationIntentHandler";
        log.info(speechText);

        Request request1 =  input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Request request = input.getRequest();
        String county = (String) input.getAttributesManager().getSessionAttributes().get("county");
        System.out.println("County "+ county);
        System.out.println("Request " + request);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LocationIntentHandler", speechText)
                .build();
    }

}