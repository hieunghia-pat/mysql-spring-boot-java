package uit.java.mysql.databases;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Subset")
@Table(name = "Subset")
@Data
@EqualsAndHashCode(of = "id")
public class Subset {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @OneToMany(mappedBy = "subset")
    private List<Image> images;
    @ManyToMany(mappedBy = "subsets")
    private List<User> users;

    public Subset() {

    }

    public Subset(Long id) {
        this.id = id;
    }
}
