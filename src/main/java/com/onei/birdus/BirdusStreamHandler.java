package com.onei.birdus;


import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;


public class BirdusStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LocationIntentHandler(),
                        new FallbackIntentHandler(),
                        new DateIntentHandler(),
                        new DayIntentHandler(),
                        new LocationDateIntentHandler()
                )
                .build();
    }

    public BirdusStreamHandler() {
        super(getSkill());
    }

}