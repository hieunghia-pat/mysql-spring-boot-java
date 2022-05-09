package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
