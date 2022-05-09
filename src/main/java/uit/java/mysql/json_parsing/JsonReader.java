package uit.java.mysql.json_parsing;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Component
@Data
public class JsonReader {

    @Autowired
    private String jsonDir;

    public JSONArray readJson() {
        Object jsonObject;
        try {
            jsonObject = new JSONParser().parse(new FileReader(jsonDir));
        }
        catch (FileNotFoundException exception) {
            log.info("Error occured! " + exception.getMessage());
            return null;
        }
        catch (IOException exception) {
            log.info("Error occured! " + exception.getMessage());
            return null;
        }
        catch (ParseException exception) {
            log.info("Error occured! " + exception.getMessage());
            return null;
        }

        JSONArray arrayObject = (JSONArray) jsonObject;

        return arrayObject;
    }

}