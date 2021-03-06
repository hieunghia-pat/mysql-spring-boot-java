package uit.java.mysql.databases;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Annotation")
@Table(name = "Annotation")
public class Annotation {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "image_id", nullable = false)
    private Long image_id;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "question_type")
    private Long question_type;
    @Column(name = "answer_type")
    private Long answer_type;
    @Column(name = "text_QA")
    private boolean textQA;
    @Column(name = "state_QA")
    private boolean stateQA;
    @Column(name = "action_QA", nullable = false)
    private boolean actionQA;
    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false, insertable = false, updatable = false)
    private Image image;

    public Annotation() {

    }

    public Annotation(Long id, Long image_id, String question, String answer, Long question_type,
                      Long answer_type, boolean textQA, boolean stateQA, boolean actionQA) {
        this.id = id;
        this.image_id = image_id;
        this.question = question;
        this.answer = answer;
        this.question_type = question_type;
        this.answer_type = answer_type;
        this.textQA = textQA;
        this.stateQA = stateQA;
        this.actionQA = actionQA;
    }
}
