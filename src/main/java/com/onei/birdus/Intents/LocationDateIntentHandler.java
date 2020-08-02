package com.onei.birdus.Intents;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.onei.birdus.BirdusS3Client;
import com.onei.birdus.Utils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class LocationDateIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("LocationDateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "LocationDateIntentHandler";
        System.out.println(speechText);

        Request request1 = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot slotCounty = slots.get("county");
        Slot slotDay = slots.get("day");
        String countyValue = (slotCounty != null) ? slotCounty.getValue() : "null";
        System.out.println(" " + countyValue);
        System.out.println("slot County " + slotCounty);
        String dayValue = (slotDay != null) ? slotDay.getValue(): LocalDate.now().toString();
        System.out.println("dayValue " + dayValue);
        Request request = input.getRequest();
        String county = (String) input.getAttributesManager().getSessionAttributes().get("county");
        String day = (String) input.getAttributesManager().getSessionAttributes().get("day");

        System.out.println("County " + county);
        System.out.println("Day " + day);
        System.out.println("Request " + request);
        BirdusS3Client birdusS3Client = new BirdusS3Client();

        String dateFromDay = Utils.getDateFromDay(dayValue);
        String results = birdusS3Client.getResultsForCountyByDay(countyValue,dateFromDay);

        return input.getResponseBuilder()
                .withSpeech(results)
                .withSimpleCard("LocationDateIntentHandler", results)
                .build();
    }

}