import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onei.birdus.GroupBirdsBy;
import com.onei.birdus.Model;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tests {

    Model model = new Model();

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
        GroupBirdsBy groupBirdsBy = new GroupBirdsBy();

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
}
