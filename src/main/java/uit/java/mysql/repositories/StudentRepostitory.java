package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.Student;

@Repository
public interface StudentRepostitory extends JpaRepository<Student, Integer> {
}
