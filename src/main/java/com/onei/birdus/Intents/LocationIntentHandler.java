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
public class LocationIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("locationIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request1 = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot slotCounty = slots.get("county");
        String county = (slotCounty != null) ? slotCounty.getValue() : "null";
        log.debug("county " + county);
        log.debug("slot County " + slotCounty);
        Request request = input.getRequest();
        //String county = (String) input.getAttributesManager().getSessionAttributes().get("county");
        log.debug("County " + county);
        log.debug("Request " + request);
        BirdusS3Client birdusS3Client = new BirdusS3Client();
        String results = birdusS3Client.getResultsForCounty(county);

        return input.getResponseBuilder()
                .withSpeech(results)
                .withSimpleCard("Results for "+county, results)
                .build();
    }

}