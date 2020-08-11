package com.onei.birdus.Intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.SessionEndedRequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

import java.util.Optional;

public class SessionEndHandler implements SessionEndedRequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput, SessionEndedRequest sessionEndedRequest) {
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, SessionEndedRequest sessionEndedRequest) {
        return Optional.empty();
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return Optional.empty();
    }
}
