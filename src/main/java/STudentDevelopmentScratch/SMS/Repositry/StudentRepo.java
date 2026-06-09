package STudentDevelopmentScratch.SMS.Repositry;

import STudentDevelopmentScratch.SMS.Entity.CourseEntity;
import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {

  Optional<StudentEntity> findByStudentRollNumber(String studentRollNumber);

  Optional<StudentEntity> findByCourseEntity_CourseCode(String courseCode);

//  List<StudentEntity> findByStudentRollNumberContainingIgnoreCaseOrStuNameContainingIgnoreCaseOrStuEmailContainingIgnoreCase
//          (
//          String studentRollNumber,
//          String stuName,
//          String stuEmail);

  @Query("""
              SELECT s FROM StudentEntity s WHERE
              LOWER(s.stuName) LIKE LOWER(CONCAT('%', :q, '%')) OR
              LOWER(s.stuEmail) LIKE LOWER(CONCAT('%', :q, '%')) OR
              LOWER(s.stuPhone) LIKE LOWER(CONCAT('%', :q, '%')) OR
              LOWER(s.studentRollNumber) LIKE LOWER(CONCAT('%', :q, '%'))
          """)
  List<StudentEntity> searchStudent(@Param("q") String query);
}