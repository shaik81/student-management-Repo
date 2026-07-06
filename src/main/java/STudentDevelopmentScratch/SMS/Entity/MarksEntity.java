        package STudentDevelopmentScratch.SMS.Entity;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.NotNull;
        import lombok.*;
        import org.aspectj.bridge.IMessage;

        @Entity
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class MarksEntity {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @NotNull(message = "Marks are required to enroll")
            private Integer internalmarks;

            @NotNull(message = "Marks are required to enroll")
            private Integer externalmarks;

            private String decisiongrade;

            @ManyToOne
            @JoinColumn(name = "student_id",referencedColumnName = "ID", nullable = false)
            private StudentEntity student;

            // 🔑 Many marks belong to one course
            @ManyToOne
            @JoinColumn(name = "courseCode", nullable = false)
            private CourseEntity course;

            @Column(unique = true, nullable = false)
            @NotNull(message = "StudentRollNumber is Required")
            private String studentRollNumber;
        }
