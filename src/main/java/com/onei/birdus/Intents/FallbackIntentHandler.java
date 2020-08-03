package com.onei.birdus.Intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.onei.birdus.BirdusS3Client;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Did you want the sightings for yesterday? If not ask for Help. ";
        BirdusS3Client birdusS3Client = new BirdusS3Client();
        String results = birdusS3Client.getResults();
        return input.getResponseBuilder()
                .withSpeech(speechText + results)
                .withSimpleCard("FallbackIntentHandler", speechText)
                .withReprompt(speechText)
                .build();
    }
}