package uit.java.mysql.databases;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Subset")
@Table(name = "Subset")
@Data
@EqualsAndHashCode(of = "id")
public class Subset {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    public Subset() {

    }

    public Subset(Long id) {
        this.id = id;
    }
}
