
package com.onei.birdus;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "LaunchRequest";

        GroupBirdsBy groupBirdsBy = new GroupBirdsBy();

        return input.getResponseBuilder()
                .withSpeech(groupBirdsBy.getResults())
                .withSimpleCard(speechText, groupBirdsBy.getResults())
                .withReprompt(groupBirdsBy.getResults())
                .build();
    }

}