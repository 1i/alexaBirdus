package com.onei.birdus;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DayIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        System.out.println(input);
        return input.matches(Predicates.intentName("dayIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "DayIntent";
        System.out.println(speechText);
        GroupBirdsBy groupBirdsBy = new GroupBirdsBy();

        Request request1 = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request1;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot day = slots.get("day");
        String slotValue = (day != null) ? day.getValue() : "null";
        System.out.println("slotValue " + slotValue);
        System.out.println("slot day " + day);
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(slotValue.toUpperCase());
        int differenceOfDays = today.minus(dayOfWeek.getValue()).getValue();
        String expectedDate = LocalDate.now().minusDays(differenceOfDays).toString();
        String results = groupBirdsBy.getResultsForDate(expectedDate);
        return input.getResponseBuilder()
                .withSpeech(results)
                .withSimpleCard("DayIntent", speechText)
                .build();
    }

}