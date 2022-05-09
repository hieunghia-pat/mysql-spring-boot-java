package uit.java.mysql.databases;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "Organization")
public class Organization {

    @Id
    @SequenceGenerator(
            name = "orgnanization_id_generator",
            sequenceName = "orgnanization_id_generator",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orgnanization_id_generator"
    )
    @Column(name = "id")
    private Integer id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "year_of_incorporation")
    private Integer yearOfIncorporation;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "employee_count")
    private Integer employeeCount;
    @Column(name = "slogan")
    private String slogan;

}
