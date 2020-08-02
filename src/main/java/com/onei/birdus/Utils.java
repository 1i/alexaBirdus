package com.onei.birdus;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Utils {

    public static String getDateFromDay(String input){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
        int differenceOfDays = today.minus(dayOfWeek.getValue()).getValue();
        String expectedDate = LocalDate.now().minusDays(differenceOfDays).toString();
        return expectedDate;
    }
}
