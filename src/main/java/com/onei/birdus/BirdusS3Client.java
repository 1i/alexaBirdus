package com.onei.birdus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class BirdusS3Client {

    private StringBuilder result = new StringBuilder();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public String getResults() {
        List<Model> models = fetchS3Json(LocalDate.now());

        Map<String, List<Model>> byCounties = models.stream().collect(Collectors.groupingBy(Model::getCounty));

        byCounties.entrySet().stream().sorted(Comparator.comparing(size -> size.getValue().size())).collect(Collectors.toList()) ;

        result.append("Yesterday had " + models.size() + " sightings. ");

        for (List<Model> county : byCounties.values()) {
            result.append("In " + county.get(0).getCounty() + ". ");
            county.forEach(model -> result.append(model.getCommonName() + ", "));
        }
        return result.toString();
    }

    public String getResultsForCounty(String county) {
        List<Model> models = fetchS3Json(LocalDate.now());

        List<Model> counties = models.stream().filter(model -> model.getCounty().equalsIgnoreCase(county)).collect(Collectors.toList());
        result.append(county + " yesterday had " + counties.size() + " sightings. ");

        for (Model location : counties) {
            result.append(location.getCommonName() + ", ");
        }
        return result.toString();
    }

    public String getResultsForDate(LocalDate date) {
        List<Model> models = fetchS3Json(date);

        Map<String, List<Model>> byCountiesSightings = models.stream().collect(Collectors.groupingBy(Model::getCounty));

        Stream<Map.Entry<String, List<Model>>> sorted = byCountiesSightings.entrySet().stream().sorted(Comparator.comparing(size -> size.getValue().size()));
        result.append("Last " + date.getDayOfWeek().toString() + " had " + models.size() + " sightings. ");

        for (List<Model> countySighting : byCountiesSightings.values()) {
            List<Model> distictSighting = countySighting.stream().distinct().collect(Collectors.toList());
            result.append("In " + countySighting.get(0).getCounty() + ". ");
            distictSighting.forEach(model -> result.append(model.getCommonName() + ", "));
        }
        return result.toString();
    }

    public String getResultsForCountyByDay(String county, LocalDate date) {
        log.debug("Start getResultsForCountyByDay " + county + " " + date);
        List<Model> models = fetchS3Json(date);

        List<Model> counties = models.stream().filter(model -> model.getCounty().equalsIgnoreCase(county)).collect(Collectors.toList());
        result.append("Last " + date.getDayOfWeek().toString() + "  ");
        result.append(county + " had " + counties.size() + " sightings. ");

        for (Model location : counties) {
            result.append(location.getCommonName() + ", ");
        }
        return result.toString();
    }

    private List<Model> fetchS3Json(LocalDate date) {
        log.debug("Get Results for [{}]", date);
        String formattedDate ;
        try {
            formattedDate = date.format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        } catch (DateTimeParseException dte) {
            log.error("DateTimeParseException ", dte);
            formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
            log.info("DTPE fetch for [{}]", formattedDate);
        }

        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + formattedDate + ".json");
            return objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            log.error(e.toString());
        }
        return Collections.emptyList();
    }
}
