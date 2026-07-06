package STudentDevelopmentScratch.SMS.Entity;


import STudentDevelopmentScratch.SMS.Enum.Roles;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "StudentTable")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Student name is required")
    @Size(min = 3, max = 30, message = "Enter a valid name (3-30 characters)")
    private String stuName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(unique = true)
    private String stuEmail;

    @NotBlank(message = "Age is required")
    private String stuAge;

    @NotBlank(message = "Phone number is required")
    @Column(unique = true)
    private String stuPhone;

    @NotBlank(message = "Department is required")
    @Column(name = "stu_Dept")
    private String stuDept;

    @NotBlank(message = "Address is required")
    private String stuAddress;

    @NotBlank(message = "Roll number is required")
    @Column(unique = true)
    private String studentRollNumber;

    private String stu_pass;

    // 🔑 Many students belong to one course
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "courseCode", nullable = false)

    private CourseEntity courseEntity;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarksEntity> marksEntities;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

}
