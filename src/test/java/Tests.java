import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onei.birdus.GroupBirdsBy;
import com.onei.birdus.Model;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tests {

    Model model = new Model();
    GroupBirdsBy groupBirdsBy = new GroupBirdsBy();

    @Test
    public void json() throws Exception {
        URL resource = Model.class.getClassLoader().getResource("result.json");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("result.json").getFile());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Model> models = objectMapper.readValue(file, new TypeReference<List<Model>>() {
        });


        System.out.println(models.size());
        models.forEach(model -> System.out.println(model.getCommonName() + " in " + model.getCounty()));

    }

    @Test
    public void formatter(){

        groupBirdsBy.getResults();
    }

    @Test
    public void getJson() throws Exception {

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        URL path = new URL("http://birdus.s3-eu-west-1.amazonaws.com/"+today+".json");

        List<Model> models;
        ObjectMapper objectMapper = new ObjectMapper();
        models = objectMapper.readValue(path, new TypeReference<List<Model>>() {
        });

        System.out.println(models);
    }

    @Test
    public void getResultsForTest(){
        String results = groupBirdsBy.getResultsFor("cork");

        System.out.println(results);
    }

    @Test
    public void getResultsForDay(){

        String today = LocalDate.now().toString();

        String results = groupBirdsBy.getResultsForDate(today);

        System.out.println(results);
    }

    @Test
    public void getDayOfWeek(){

        String date =  "monday";
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(date.toUpperCase());
        System.out.println(today);
        System.out.println(dayOfWeek);

        System.out.println();
        int differenceOfDays = today.minus(dayOfWeek.getValue()).getValue();
        String expectedDate = LocalDate.now().minusDays(differenceOfDays).toString();

        String results = groupBirdsBy.getResultsForDate(expectedDate);
        System.out.println(results);
    }

    @Test
    public void getLocationByDayOfWeek(){

        String date = LocalDate.now().minusDays(1).toString();
        String county =  "dublin";

        String results = groupBirdsBy.getResultsForCountyByDay(county,date);
        System.out.println(results);
    }

      @Test
    public void getLocationByDayOfWeekCork(){

        String date = LocalDate.now().minusDays(1).toString();
        String county =  "cork";

        String results = groupBirdsBy.getResultsForCountyByDay(county,date);
        System.out.println(results);
    }
}
