package uit.java.mysql;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uit.java.mysql.databases.Annotation;
import uit.java.mysql.databases.Image;
import uit.java.mysql.databases.Subset;
import uit.java.mysql.json_parsing.JsonReader;
import uit.java.mysql.repositories.AnnotationRepository;
import uit.java.mysql.repositories.ImageRepository;
import uit.java.mysql.repositories.SubsetRepository;

import java.util.List;

@Slf4j
@Component
public class DataCreator {

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private SubsetRepository subsetRepository;

    @EventListener
    public void start(ContextRefreshedEvent event) {

        JSONArray jsonArray = jsonReader.readJson();

        if (jsonArray.equals(null))
            return;

        List<JSONObject> jsonObjects = jsonArray.stream().toList();

        for (JSONObject jsonObject : jsonObjects) {
            String filename = (String) jsonObject.get("filename");
            String filepath = (String) jsonObject.get("filepath");
            List<JSONObject> annotations = ((JSONArray) jsonObject.get("annotations")).stream().toList();
            boolean toDelete = (boolean) jsonObject.get("delete");

            Long image_id = Long.parseLong(filename.split("\\.")[0]);
            Long subsetId = Long.parseLong(filepath.split("_")[1]);

            Image image = new Image(image_id, filename, subsetId, toDelete);

            imageRepository.save(image);

            Subset subset = new Subset(subsetId);
            subsetRepository.save(subset);

            for (JSONObject annotation : annotations) {
                Long ann_id = (Long) annotation.get("ann_id");
                String question = (String) annotation.get("question");
                String answer = (String) annotation.get("answer");
                Integer questionType = ((Long) annotation.get("question-type")).intValue();
                Integer answerType = ((Long) annotation.get("answer-type")).intValue();
                boolean textQA = (boolean) annotation.get("text-QA");
                boolean stateQA = (boolean) annotation.get("state-QA");
                boolean actionQA = (boolean) annotation.get("action-QA");

                Annotation ann = new Annotation(ann_id, image_id, question, answer, questionType, answerType, textQA, stateQA, actionQA);
                annotationRepository.save(ann);
            }

        }
    }
}
