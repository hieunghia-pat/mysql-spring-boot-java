package uit.java.mysql.databases;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Image")
@Table(name = "Image")
public class Image {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "filename", nullable = false)
    private String filename;
    @Column(name = "subset_id", nullable = false)
    private Long subset_id;
    @Column(name = "to_delete", nullable = false)
    private boolean to_delete;
    @OneToMany(mappedBy = "image")
    private List<Annotation> annotations;
    @ManyToOne
    @JoinColumn(name = "subset_id", insertable = false, updatable = false)
    private Subset subset;

    public Image() {

    }

    public Image(Long id, String filename, Long subset_id, boolean to_delete) {
        this.id = id;
        this.filename = filename;
        this.subset_id = subset_id;
        this.to_delete = to_delete;
    }
}
