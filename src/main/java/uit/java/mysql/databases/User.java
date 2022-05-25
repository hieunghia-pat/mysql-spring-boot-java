package uit.java.mysql.databases;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "User")
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @OneToMany(mappedBy = "userId")
    private Set<UserSubset> userSubsets;

    public User() {

    }

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
