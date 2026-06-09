package STudentDevelopmentScratch.SMS.Repositry;

import STudentDevelopmentScratch.SMS.Entity.CourseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<CourseEntity,Long> {

    Optional<CourseEntity> findBycourseCode(String courseCode);

    @Query("""
    SELECT s from CourseEntity s WHERE 
    LOWER(s.courseCode) LIKE LOWER (CONCAT('%', :q , '%'))OR 
    LOWER(s.courseName) LIKE LOWER (CONCAT('%', :q ,  '%'))OR
    LOWER(s.courseDept) LIKE LOWER (CONCAT('%', :q ,  '%'))
        """)
    List<CourseEntity>searchCourse(@Param("q")String querey);

}
