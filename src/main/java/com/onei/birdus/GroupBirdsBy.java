package com.onei.birdus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupBirdsBy {


    public String getResults() {
        StringBuilder result = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("result.json").getFile());

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        List<Model> models = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");
            models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
            });
        } catch (IOException e) {
            System.out.println(e);
        }

        Map<String, List<Model>> byCounties = models.stream().collect(Collectors.groupingBy(Model::getCounty));


        result.append("Today has " + models.size() + " sightings. ");

        for (List<Model> county : byCounties.values()) {

            result.append("In " + county.get(0).getCounty() + ". ");
            county.forEach(model -> result.append(model.getCommonName() + ", "));

        }

        System.out.println(result);

        return result.toString();
    }
}
