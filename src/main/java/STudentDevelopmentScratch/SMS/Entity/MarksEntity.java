        package STudentDevelopmentScratch.SMS.Entity;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.NotNull;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        @Entity
        @Data
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
            @JoinColumn(name = "student_id", nullable = false)
            private StudentEntity student;

            // 🔑 Many marks belong to one course
            @ManyToOne
            @JoinColumn(name = "course_id", nullable = false)
            private CourseEntity course;
        }
