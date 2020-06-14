import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onei.birdus.Model;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
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
        //List<Model> models = Jackson.loadFrom(file, );


        System.out.println(models.size());
        models.forEach(model -> System.out.println(model.getCommonName() + " in " + model.getCounty()));

    }
}
