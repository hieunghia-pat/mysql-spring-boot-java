package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.java.mysql.databases.Student;

public interface StudentRepostitory extends JpaRepository<Student, Integer> {
}
