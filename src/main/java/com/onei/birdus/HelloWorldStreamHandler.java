package com.onei.birdus;


import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;


public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new FallbackIntentHandler()
                )
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}