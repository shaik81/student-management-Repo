        package STudentDevelopmentScratch.SMS.Entity;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.List;


        @Entity
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Table(name = "Course")
        public class CourseEntity {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            Long courseId;
            @NotBlank
            @Column(unique = true)
            String courseCode;
            @NotBlank
            String courseName;
            @NotBlank
            String courseDept;

            @NotNull
            Integer  courseCredits;

            @OneToMany(mappedBy = "courseEntity")
            private List<StudentEntity> studentEntities;

            @OneToMany(mappedBy = "course")
            private List<MarksEntity> marksEntities;

    }