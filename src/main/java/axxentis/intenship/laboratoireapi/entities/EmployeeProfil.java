package axxentis.intenship.laboratoireapi.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeProfil extends Common{

    @Id
    @Column(name = "CONTACT_PROFIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "EMPLOYEE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = false)
    private Employee employee;
    @ManyToOne(targetEntity = Profil.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "PROFIL_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = false)
    private Profil profil;
}
