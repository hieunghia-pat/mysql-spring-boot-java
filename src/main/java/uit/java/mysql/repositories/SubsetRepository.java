package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.Subset;

@Repository
public interface SubsetRepository extends JpaRepository<Subset, Long> {
}
