package uit.java.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.java.mysql.databases.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
