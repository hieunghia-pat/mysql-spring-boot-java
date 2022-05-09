package uit.java.mysql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uit.java.mysql.databases.Student;
import uit.java.mysql.repositories.StudentRepostitory;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepostitory studentRepostitory) {
        return args -> {
            Student hieunghia = new Student(
                    "Nguyen",
                    "Hieu Nghia",
                    "19520178@gm.uit.edu.vn",
                    21
            );

            Student nthy = new Student(
                    "Le",
                    "Nguyen Ngoc Thy",
                    "ln.nthy@gmail.com",
                    18
            );

            studentRepostitory.save(hieunghia);
            studentRepostitory.save(nthy);
        };
    }
}
