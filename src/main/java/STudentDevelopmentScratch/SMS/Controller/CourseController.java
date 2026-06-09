package STudentDevelopmentScratch.SMS.Controller;

import STudentDevelopmentScratch.SMS.DTOs.CourseDTOS;
import STudentDevelopmentScratch.SMS.DTOs.SearchCourse;
import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
import STudentDevelopmentScratch.SMS.DTOs.StudentDTOSS;
import STudentDevelopmentScratch.SMS.Service.CourseService.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/course/")
@RestController
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)

@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<List<CourseDTOS>> createCourse(@RequestBody List<CourseDTOS> courseDTOS) {
        List<CourseDTOS> savedStudent = courseService.createCourse(courseDTOS);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTOS>> getAllCourse() {
        List<CourseDTOS> savedCourse = courseService.getAllcourse();
        return ResponseEntity.ok(savedCourse);
    }
    @GetMapping("/{courseCode}")
    public ResponseEntity<CourseDTOS> getBuIDCourse(@PathVariable String courseCode){
        CourseDTOS savedcourse = courseService.getBuIDCourse(courseCode);

        return ResponseEntity.ok(savedcourse);
    }
//    @DeleteMapping("/{courseCode}")
//    public ResponseEntity<CourseDTOS>deleteBYID(@PathVariable String courseCode){
//        CourseDTOS deletingdata = courseService.deleteBYID(courseCode);
//
//        return ResponseEntity.ok(deletingdata);
//    }
    @PatchMapping("/{courseCode}")
    public ResponseEntity<CourseDTOS>updateStudent(@PathVariable String courseCode ,@RequestBody CourseDTOS courseDTOS){
        CourseDTOS updateStudent = courseService.updateStudent(courseCode,courseDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(updateStudent);
    }
    @DeleteMapping("/{courseCode}")
    public ResponseEntity<CourseDTOS>deletecourse(@PathVariable String courseCode,@RequestBody CourseDTOS courseDTOS){
        CourseDTOS deleteCourses = courseService.deletecourse(courseCode,courseDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(deleteCourses);
    }
       public ResponseEntity<List<SearchCourse>> searchByCourse(@RequestParam String keyword){
                List<SearchCourse> searchCourse = courseService.searchByCourse(keyword);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(searchCourse);
       }
}
