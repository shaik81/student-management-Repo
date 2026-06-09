package STudentDevelopmentScratch.SMS.Repositry;

import STudentDevelopmentScratch.SMS.Entity.MarksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarksRepo extends JpaRepository<MarksEntity, Long> {

    // Finds marks by student roll number and course code
    Optional<MarksEntity> findByStudent_StudentRollNumberAndCourse_CourseCode(
            String studentRollNumber,
            String courseCode
    );
}
