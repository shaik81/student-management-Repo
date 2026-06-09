package STudentDevelopmentScratch.SMS.Service.CourseService;

import STudentDevelopmentScratch.SMS.DTOs.CourseDTOS;
import STudentDevelopmentScratch.SMS.DTOs.SearchCourse;
import org.springframework.core.annotation.MergedAnnotations;

import java.util.List;

public interface CourseofStudent {
    List<CourseDTOS>createCourse(List<CourseDTOS>courseDTOS);
    List<CourseDTOS>getAllcourse();
    CourseDTOS getBuIDCourse(String courseCode);
//    CourseDTOS deleteBYID(String courseCodee);
    CourseDTOS  updateStudent(String courseCode,CourseDTOS courseDTOS);
    CourseDTOS deletecourse(String courseCode,CourseDTOS courseDTOS);
    List<SearchCourse> searchByCourse(String keyword);
}
