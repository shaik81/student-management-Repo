            package STudentDevelopmentScratch.SMS.controller;

            import STudentDevelopmentScratch.SMS.DTOs.LoginRequestDTO;
            import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
            import STudentDevelopmentScratch.SMS.DTOs.StudentDTOSS;

            import STudentDevelopmentScratch.SMS.Service.StudentService.StudentService;
            import jakarta.annotation.security.PermitAll;
            import jakarta.validation.Valid;
            import lombok.RequiredArgsConstructor;
            import org.springframework.http.ResponseEntity;
            import org.springframework.security.access.prepost.PreAuthorize;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @RestController

            @RequiredArgsConstructor
            @RequestMapping("/students")

            public class StudentController {

                private final StudentService studentService;

                @PostMapping("/postdata")
                @PreAuthorize("hasRole('Admin')")
                public ResponseEntity <List<StudentDTOSS>> CreateStudent(@RequestBody List <StudentDTOSS>studentDTOSS) {
                   List <StudentDTOSS >  savedStudent = studentService.CreateStudent(studentDTOSS);
                    return ResponseEntity.ok(savedStudent);
                }

                @GetMapping("/Allstudents")
                @PreAuthorize("hasRole('Admin')")
                public ResponseEntity<List<StudentDTOSS>> getALLStudents() {
                    List<StudentDTOSS> savedStudents = studentService.getALLStudents();
                    return ResponseEntity.ok(savedStudents);
                }


                @GetMapping("/{studentRollNumber}/student")
                @PreAuthorize("hasRole('Admin') or hasRole('Student')")
                public ResponseEntity<StudentDTOSS>getByID(@PathVariable String studentRollNumber) {
                    StudentDTOSS dto = studentService.getByID(studentRollNumber);
                    return ResponseEntity.ok(dto);
                }
                @DeleteMapping("/{studentRollNumber}")
                @PreAuthorize("hasRole('Admin')")
                public ResponseEntity<StudentDTOSS>deleteStudent(@PathVariable String studentRollNumber){
                    StudentDTOSS studentDTOSS = studentService.deleteStudent(studentRollNumber);
                    return ResponseEntity.ok(studentDTOSS);
                }
                @PatchMapping("/{studentRollNumber}/patchmapping")
                @PreAuthorize("hasRole('Admin')")
                public ResponseEntity<StudentDTOSS>updateStudent(@RequestBody StudentDTOSS studentDTOSS,@PathVariable String
                                                                 studentRollNumber){
                    StudentDTOSS Dtoss = studentService.updateStudent(studentRollNumber,studentDTOSS);
                    return ResponseEntity.ok(Dtoss);
                }

                @PutMapping("/{studentRollNumber}/put")
                @PreAuthorize("hasRole('Admin')")
                public ResponseEntity<StudentDTOSS>putUpdate(@PathVariable String studentRollNumber,@RequestBody @Valid StudentDTOSS
                                                              studentDTOSS){
                    StudentDTOSS putUpdate = studentService.putUpdate(studentRollNumber,studentDTOSS);

                    return ResponseEntity.ok(putUpdate);
                }
                @PostMapping("/login")
                public ResponseEntity<StudentDTOSS>login(@RequestBody LoginRequestDTO loginRequestDTO){
                    StudentDTOSS dtoss = studentService.login(loginRequestDTO);
                    return ResponseEntity.ok(dtoss);
                }
//                @GetMapping("/search")
//                public ResponseEntity <List<SearchStudent>>searchbyStudentID(@RequestParam String keyword){
//
//                    List < SearchStudent >  searchstudent = studentService.searchbyStudentID(keyword);
//                    return ResponseEntity.ok(searchstudent);
//                }

                @GetMapping("/search")
                @PermitAll
                public ResponseEntity<List<SearchStudent>> searchByid (@RequestParam String keyword){
                    List<SearchStudent> searchStudents = studentService.searchByStudentreference(keyword);

                    return ResponseEntity.ok(searchStudents);
                }

            }
