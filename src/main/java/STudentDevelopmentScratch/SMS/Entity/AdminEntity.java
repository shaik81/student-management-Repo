package STudentDevelopmentScratch.SMS.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADMIN")

public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;
    @NotBlank
    @Size(min = 2 ,max = 30,message = "Enter a valid Name ")
    private String adminName;

    @Email(message ="Enter a valid Email example@gmail.com")
    @NotBlank
    private String adminEmail;

    @NotBlank
    @Column(unique = true)
    private String adminPhone;

    @NotBlank
    private String adminDepartment;

    @NotBlank
    private String adminRole;

   @NotBlank(message = "Required Password")
    private String adminPass;


}
