
package com.onei.birdus;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "LaunchRequest";
        System.out.println(speechText);

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

            String seen = "";

            for (Model model : models) {
                seen = seen + model.getCommonName() + ", ";
            }

            System.out.println(seen);
            return input.getResponseBuilder()
                    .withSpeech("Seen yesterday " + seen)
                    .withSimpleCard("HelloWorld", "Seen yesterday " + seen)
                    .withReprompt("Seen yesterday " + seen)
                    .build();
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }

}