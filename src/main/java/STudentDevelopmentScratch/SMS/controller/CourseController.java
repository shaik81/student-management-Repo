package STudentDevelopmentScratch.SMS.controller;

import STudentDevelopmentScratch.SMS.DTOs.CourseDTOS;
import STudentDevelopmentScratch.SMS.DTOs.SearchCourse;
import STudentDevelopmentScratch.SMS.Service.CourseService.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/course")
@RestController


@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/post")
    public ResponseEntity<List<CourseDTOS>> createCourse(@RequestBody List<CourseDTOS> courseDTOS) {
        List<CourseDTOS> savedStudent = courseService.createCourse(courseDTOS);
        return ResponseEntity.ok(savedStudent);
    }
    @PreAuthorize("hasAnyRole('Admin','Student')")
    @GetMapping("/Allcourses")
    public ResponseEntity<List<CourseDTOS>> getAllCourse() {
        List<CourseDTOS> savedCourse = courseService.getAllcourse();
        return ResponseEntity.ok(savedCourse);
    }
    @PreAuthorize("hasRole('Admin')")
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
    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/{courseCode}")
    public ResponseEntity<CourseDTOS>updateStudent(@PathVariable String courseCode ,@RequestBody CourseDTOS courseDTOS){
        CourseDTOS updateStudent = courseService.updateStudent(courseCode,courseDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(updateStudent);
    }
    @PreAuthorize("hasRole('Admin')")
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
