package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.Annotation;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Integer> {
}
