package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.java.mysql.databases.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
