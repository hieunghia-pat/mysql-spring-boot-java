package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.UserSubset;

@Repository
public interface UserSubsetRepository extends JpaRepository<UserSubset, Long> {
}
