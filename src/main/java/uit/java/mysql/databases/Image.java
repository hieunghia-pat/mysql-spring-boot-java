package uit.java.mysql.databases;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Image")
@Table(name = "Image")
@EqualsAndHashCode(of = "id")
public class Image {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "filename", nullable = false)
    private String filename;
    @Column(name = "subset_id", nullable = false)
    private Long subsetId;
    @Column(name = "to_delete", nullable = false)
    private boolean toDelete;
    @OneToMany(mappedBy = "image")
    private List<Annotation> annotations;
    @ManyToOne
    @JoinColumn(name = "subset_id", insertable = false, updatable = false)
    private Subset subset;

    public Image() {

    }

    public Image(Long id, String url, String filename, Long subsetId, boolean toDelete) {
        this.id = id;
        this.url = url;
        this.filename = filename;
        this.subsetId = subsetId;
        this.toDelete = toDelete;
    }
}
