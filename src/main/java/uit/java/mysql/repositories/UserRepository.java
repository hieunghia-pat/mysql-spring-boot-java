package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
