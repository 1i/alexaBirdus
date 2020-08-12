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
public class BirdusS3Client {

    private StringBuilder result = new StringBuilder();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Model> models = null;


    public String getResults() {
        log.debug("Get Results");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        Map<String, List<Model>> byCounties = models.stream().collect(Collectors.groupingBy(Model::getCounty));

        byCounties.entrySet().stream().sorted(Comparator.comparing(size -> size.getValue().size()));

        result.append("Yesterday had " + models.size() + " sightings. ");

        for (List<Model> county : byCounties.values()) {
            result.append("In " + county.get(0).getCounty() + ". ");
            county.forEach(model -> result.append(model.getCommonName() + ", "));
        }
        return result.toString();
    }

    public String getResultsFor(String county) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        List<Model> counties = models.stream().filter(model -> model.getCounty().equalsIgnoreCase(county)).collect(Collectors.toList());
        result.append(county + " yesterday had " + counties.size() + " sightings. ");

        for (Model location : counties) {
            result.append(location.getCommonName() + ", ");
        }
        return result.toString();
    }

    public String getResultsForDate(String date) {
        log.debug("Start getResultsForDate " + date);
        String formattedDate = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + formattedDate + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        Map<String, List<Model>> byCounties = models.stream().collect(Collectors.groupingBy(Model::getCounty));

        byCounties.entrySet().stream().sorted(Comparator.comparing(size -> size.getValue().size()));
        result.append("Last " + LocalDate.parse(date).getDayOfWeek().toString() + " had " + models.size() + " sightings. ");

        for (List<Model> county : byCounties.values()) {
            result.append("In " + county.get(0).getCounty() + ". ");
            county.forEach(model -> result.append(model.getCommonName() + ", "));
        }
        return result.toString();
    }

    public String getResultsForCountyByDay(String county, String date) {
        log.debug("Start getResultsForCountyByDay " + county + " " + date);
        String formattedDate = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + formattedDate + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }

        List<Model> counties = models.stream().filter(model -> model.getCounty().equalsIgnoreCase(county)).collect(Collectors.toList());
        result.append("Last " + LocalDate.parse(date).getDayOfWeek().toString() + "  ");
        result.append(county + " had " + counties.size() + " sightings. ");

        for (Model location : counties) {
            result.append(location.getCommonName() + ", ");
        }
        return result.toString();
    }
}
