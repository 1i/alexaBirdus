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
        System.out.println(input);
        return input.matches(Predicates.intentName("dateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "DateIntent";
        System.out.println(speechText);

        Request request1 = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot date = slots.get("date");
        String slotValue = (date != null) ? date.getValue() : "null";
        System.out.println("slotValue " + slotValue);
        System.out.println("slot date " + date);
        Request request = input.getRequest();
        String date1 = (String) input.getAttributesManager().getSessionAttributes().get("date");
        System.out.println("date " + date1);
        System.out.println("Request " + request);
        GroupBirdsBy groupBirdsBy = new GroupBirdsBy();
        String results = groupBirdsBy.getResultsFor(slotValue);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("DateIntent", speechText)
                .build();
    }

}