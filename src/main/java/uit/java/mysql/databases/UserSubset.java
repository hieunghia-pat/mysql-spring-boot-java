package uit.java.mysql.databases;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "UserSubset")
@Table(name = "user_subset")
@Slf4j
public class UserSubset {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;
    @ManyToOne(targetEntity = Subset.class)
    @JoinColumn(name = "subset_id", nullable = false)
    private Long subsetId;
    @Column(name = "assign_date", nullable = false)
    private Date assignDate;
    @Column(name = "finish_date", nullable = false)
    private Date finishDate;
    @Column(name = "is_validation", nullable = false)
    boolean isValidation;

    public UserSubset() {
    }

    public UserSubset(String userId, Long subsetId, Date assignDate, Date finishDate, boolean isValidation) {
        this.userId = userId;
        this.subsetId = subsetId;
        this.assignDate = assignDate;
        this.finishDate = finishDate;
        this.isValidation = isValidation;
    }
}
