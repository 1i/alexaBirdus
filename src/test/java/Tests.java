import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onei.birdus.BirdusS3Client;
import com.onei.birdus.Model;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private BirdusS3Client birdusS3Client = new BirdusS3Client();

    @Test
    public void localJson() throws Exception {

        File file = new File(getClass().getClassLoader().getResource("20-06-12.json").getFile());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Model> models = objectMapper.readValue(file, new TypeReference<List<Model>>() {
        });

        assertEquals(15, models.size());
        models.forEach(model -> assertNotNull(model.getCommonName()));
        models.forEach(model -> assertNotNull(model.getCounty()));
        models.forEach(model -> System.out.println(model.getCommonName() + " in " + model.getCounty()));
    }

    @Test
    public void formatter() {
        String results = birdusS3Client.getResults();
        assertNotNull(results);
    }

    @Test
    public void getJson() throws Exception {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/" + today + ".json");

        List<Model> models;
        ObjectMapper objectMapper = new ObjectMapper();
        models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
        });

        System.out.println(models);
    }

    @Test
    public void getResultsForTest() {
        String results = birdusS3Client.getResultsForCounty("cork");

        System.out.println(results);
    }

    @Test
    public void getResultsForDay() {
        String results = birdusS3Client.getResultsForDate(LocalDate.now());
    }

    @Test
    public void getDayOfWeek() {
        String date = "monday";
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(date.toUpperCase());

        int differenceOfDays = today.minus(dayOfWeek.getValue()).getValue();
        LocalDate expectedDate = LocalDate.now().minusDays(differenceOfDays);
        String results = birdusS3Client.getResultsForDate(expectedDate);
        System.out.println(results);
    }

    @Test
    public void getLocationByDayOfWeek() {
        LocalDate date = LocalDate.now().minusDays(4);
        String county = "dublin";

        String results = birdusS3Client.getResultsForCountyByDay(county, date);
        assertNotNull(results);
        assertTrue(results.contains("dublin") && results.contains("sightings"));
    }

    @Test
    public void getLocationByDayOfWeekCork() {
        LocalDate date = LocalDate.now().minusDays(7);
        String county = "cork";

        String results = birdusS3Client.getResultsForCountyByDay(county, date);
        assertNotNull(results);
        assertTrue(results.contains("cork") && results.contains("sightings"));
    }
}
