package uit.java.mysql;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uit.java.mysql.databases.*;
import uit.java.mysql.json_parsing.JsonReader;
import uit.java.mysql.repositories.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@PropertySource("classpath:application.properties")
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
    @Autowired
    private UserSubsetRepository userSubsetRepository;

    @Value("${annotation-json-directory}")
    private String annotationJsonDir;
    @Value("${user-json-directory}")
    private String userJsonDir;
    @Value("${assignment-json-directory}")
    private String assignmentJsonDir;
    @Value("${image-json-directory}")
    private String imageJsonDir;
    @Value("${subset-json-directory}")
    private String subsetJsonDir;

    @EventListener
    public void start(ContextRefreshedEvent event) {

        // create Subset table
        List<JSONObject> subsetJsonObjects = jsonReader.readJson(subsetJsonDir).stream().toList();
        for (JSONObject jsonObject : subsetJsonObjects) {
            Long subsetId = (Long) jsonObject.get("id");
            Subset subset = new Subset(subsetId);
            subsetRepository.save(subset);
        }

        // create User table
        List<JSONObject> userJsonObjects = jsonReader.readJson(userJsonDir).stream().toList();
        for (JSONObject object: userJsonObjects) {
            String username = (String) object.get("username");
            String firstname = (String) object.get("firstname");
            String lastname = (String) object.get("lastname");
            String password = (String) object.get("password");
            String role = (String) object.get("role");
            User user = new User(username, firstname, lastname, password, role);
            userRepository.save(user);
        }

        // create Image table
        List<JSONObject> imageJsonObjects = jsonReader.readJson(imageJsonDir).stream().toList();
        for (JSONObject jsonObject : imageJsonObjects) {
            Long id = (Long) jsonObject.get("id");
            String filename = (String) jsonObject.get("filename");
            Long subsetId = (Long) jsonObject.get("subset_id");
            boolean toDelete = (boolean) jsonObject.get("to_delete");
            String url = (String) jsonObject.get("url");
            Image image = new Image(id, url, filename, subsetId, toDelete);
            imageRepository.save(image);
        }

        // create annotation table
        List<JSONObject> annotationJsonObjects = jsonReader.readJson(annotationJsonDir).stream().toList();
        for (JSONObject jsonObject: annotationJsonObjects) {
            Long imageId = (Long) jsonObject.get("image_id");
            UUID userId = userRepository.findByUsername(jsonObject.get("username").toString()).get().getId();
            String question = jsonObject.get("question").toString();
            String answer = jsonObject.get("answer").toString();
            Long questionType = (Long) jsonObject.get("question_type");
            Long answerType = (Long) jsonObject.get("answer_type");
            boolean textQA = (boolean) jsonObject.get("text_QA");
            boolean stateQA = (boolean) jsonObject.get("state_QA");
            boolean actionQA = (boolean) jsonObject.get("action_QA");
            Annotation annotation = new Annotation(imageId, userId, question, answer, questionType, answerType, textQA, stateQA, actionQA);
            annotationRepository.save(annotation);
        }

        // create assignment table
        List<JSONObject> assignmentJsonObjects = jsonReader.readJson(assignmentJsonDir).stream().toList();
        for (JSONObject jsonObject: assignmentJsonObjects) {
            UUID userId = userRepository.findByUsername(jsonObject.get("username").toString()).get().getId();
            Long subsetId = (Long) jsonObject.get("subset_id");
            boolean isValidation = (boolean) jsonObject.get("is_validation");
            String assignDate = jsonObject.get("assign_date").toString();
            String finishDate = jsonObject.get("finish_date").toString();
            UserSubset userSubset = new UserSubset(userId, subsetId, assignDate, finishDate, isValidation);
            userSubsetRepository.save(userSubset);
        }
    }
}
