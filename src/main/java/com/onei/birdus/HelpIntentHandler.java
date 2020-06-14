package com.onei.birdus;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "HelpIntentHandler";
        URL resource = Model.class.getClassLoader().getResource("result.json");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("result.json").getFile());
        List<Model> models = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            models = objectMapper.readValue(file, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            System.out.println(e);
        }


        System.out.println(models.size());
        if (models != null) {

            models.forEach(model -> System.out.println(model.getCommonName() + " in " + model.getCounty()));

            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("HelloWorld", models.get(1).getCommonName())
                    .withReprompt(speechText)
                    .build();
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }
}
