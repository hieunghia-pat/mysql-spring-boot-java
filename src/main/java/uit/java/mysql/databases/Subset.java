package uit.java.mysql.databases;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "Subset")
@Table(name = "Subset")
public class Subset {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @OneToMany(mappedBy = "subset")
    private Set<Image> images;
    @OneToMany(mappedBy = "subsetId")
    private Set<UserSubset> userSubsets;

    public Subset() {

    }

    public Subset(Long id) {
        this.id = id;
    }
}
