package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(
            value = "SELECT * FROM users WHERE username=?1",
            nativeQuery = true
    )
    Optional<User> findByUsername(String username);
}
