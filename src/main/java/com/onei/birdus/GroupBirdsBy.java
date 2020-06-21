package com.onei.birdus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class GroupBirdsBy {


    public String getResults() {
        System.out.println("Start getResults");
        StringBuilder result = new StringBuilder();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        List<Model> models = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        Map<String, List<Model>> byCounties = models.stream().collect(Collectors.groupingBy(Model::getCounty));

        byCounties.entrySet().stream().sorted(Comparator.comparing(size -> size.getValue().size()));

        result.append("Today has " + models.size() + " sightings. ");

        for (List<Model> county : byCounties.values()) {

            result.append("In " + county.get(0).getCounty() + ". ");
            county.forEach(model -> result.append(model.getCommonName() + ", "));

        }


        return result.toString();
    }

    public String getResultsFor(String county) {
        System.out.println("Start getResultsFor");
        StringBuilder result = new StringBuilder();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        List<Model> models = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        List<Model> counties = models.stream().filter(model -> model.getCounty().equalsIgnoreCase(county)).collect(Collectors.toList());


        result.append(county + " today has " + counties.size() + " sightings. ");

        for (Model location : counties) {

            result.append(location.getCommonName() + ", ");

        }


        return result.toString();
    }
}
