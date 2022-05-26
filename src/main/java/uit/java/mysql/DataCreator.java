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
import uit.java.mysql.databases.User;
import uit.java.mysql.json_parsing.JsonReader;
import uit.java.mysql.repositories.AnnotationRepository;
import uit.java.mysql.repositories.ImageRepository;
import uit.java.mysql.repositories.SubsetRepository;
import uit.java.mysql.repositories.UserRepository;

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
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void start(ContextRefreshedEvent event) {

        log.info(jsonReader.getUserJsonDir());
        log.info(jsonReader.getAnnotationJsonDir());

        JSONArray annotationJsonArray = jsonReader.readJson(jsonReader.getAnnotationJsonDir());
        JSONArray userJsonArray = jsonReader.readJson(jsonReader.getUserJsonDir());

        if (annotationJsonArray.equals(null) || userJsonArray.equals(null))
            return;

        List<JSONObject> annotationJsonObjects = annotationJsonArray.stream().toList();

        // create Subset table
        for (JSONObject jsonObject : annotationJsonObjects) {
            String filepath = (String) jsonObject.get("filepath");
            Long subsetId = Long.parseLong(filepath.split("_")[1]);

            Subset subset = new Subset(subsetId);
            subsetRepository.save(subset);
        }

        // create Image and Annotation tables
        for (JSONObject jsonObject : annotationJsonObjects) {
            String filename = (String) jsonObject.get("filename");
            String filepath = (String) jsonObject.get("filepath");
            List<JSONObject> annotations = ((JSONArray) jsonObject.get("annotations")).stream().toList();
            boolean toDelete = (boolean) jsonObject.get("delete");

            Long image_id = Long.parseLong(filename.split("\\.")[0]);
            Long subsetId = Long.parseLong(filepath.split("_")[1]);

            Image image = new Image(image_id, filename, subsetId, toDelete);

            imageRepository.save(image);

            for (JSONObject annotation : annotations) {
                Long ann_id = (Long) annotation.get("ann_id");
                String question = (String) annotation.get("question");
                String answer = (String) annotation.get("answer");
                Long questionType = (Long) annotation.get("question-type");
                Long answerType = (Long) annotation.get("answer-type");
                boolean textQA = (boolean) annotation.get("text-QA");
                boolean stateQA = (boolean) annotation.get("state-QA");
                boolean actionQA = (boolean) annotation.get("action-QA");

                Annotation ann = new Annotation(ann_id, image_id, question, answer, questionType, answerType, textQA, stateQA, actionQA);
                annotationRepository.save(ann);
            }
        }

        // create User table
        List<JSONObject> userJsonObjects = userJsonArray.stream().toList();
        for (JSONObject object: userJsonObjects) {
            String user_id = object.get("id").toString();
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String role = (String) object.get("role");

            User user = new User(user_id, username, password, role);
            userRepository.save(user);
        }
    }
}
