package uit.java.mysql.databases;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "UserSubset")
@Table(name = "users_subset")
@Slf4j
public class UserSubset {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "subset_id", nullable = false)
    private Long subsetId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", unique = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(targetEntity = Subset.class)
    @JoinColumn(name = "subset_id", unique = false, insertable = false, updatable = false)
    private Subset subset;
    @Column(name = "assign_date", nullable = false)
    private String assignDate;
    @Column(name = "finish_date", nullable = false)
    private String finishDate;
    @Column(name = "is_validation", nullable = false)
    boolean isValidation;

    public UserSubset() {
    }

    public UserSubset(UUID userId, Long subsetId, String assignDate, String finishDate, boolean isValidation) {
        this.userId = userId;
        this.subsetId = subsetId;
        this.assignDate = assignDate;
        this.finishDate = finishDate;
        this.isValidation = isValidation;
    }
}