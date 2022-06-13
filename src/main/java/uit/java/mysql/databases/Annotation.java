package uit.java.mysql.databases;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name = "Annotation")
@Table(name = "Annotation")
@EqualsAndHashCode(of = "id")
public class Annotation {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "image_id", nullable = false)
    private Long imageId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "question_type")
    private Long questionType;
    @Column(name = "answer_type")
    private Long answerType;
    @Column(name = "text_QA")
    private boolean textQA;
    @Column(name = "state_QA")
    private boolean stateQA;
    @Column(name = "action_QA", nullable = false)
    private boolean actionQA;
    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false, insertable = false, updatable = false)
    private Image image;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User annotator;

    public Annotation() {

    }

    public Annotation(Long imageId, UUID userId, String question, String answer, Long questionType,
                      Long answerType, boolean textQA, boolean stateQA, boolean actionQA) {
        this.imageId = imageId;
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.questionType = questionType;
        this.answerType = answerType;
        this.textQA = textQA;
        this.stateQA = stateQA;
        this.actionQA = actionQA;
    }
}
