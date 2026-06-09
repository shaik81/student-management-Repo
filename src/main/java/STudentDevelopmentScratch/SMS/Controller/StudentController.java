            package STudentDevelopmentScratch.SMS.Controller;

            import STudentDevelopmentScratch.SMS.DTOs.LoginRequestDTO;
            import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
            import STudentDevelopmentScratch.SMS.DTOs.StudentDTOSS;

            import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
            import STudentDevelopmentScratch.SMS.Service.StudentService.StudentService;
            import jakarta.validation.Valid;
            import lombok.RequiredArgsConstructor;
            import org.springframework.http.HttpStatus;
            import org.springframework.http.ResponseEntity;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

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
            @RequestMapping("/Students")

            public class StudentController {

                private final StudentService studentService;

                @PostMapping("/postdata")
                public ResponseEntity<StudentDTOSS> CreateStudent(@RequestBody StudentDTOSS studentDTOSS) {
                    StudentDTOSS savedStudent = studentService.CreateStudent(studentDTOSS);
                    return ResponseEntity.ok(savedStudent);
                }


                @GetMapping
                public ResponseEntity<List<StudentDTOSS>> getALLStudents() {
                    List<StudentDTOSS> savedStudents = studentService.getALLStudents();
                    return ResponseEntity.ok(savedStudents);
                }

                @GetMapping("/{studentRollNumber}/student")
                public ResponseEntity<StudentDTOSS>getByID(@PathVariable String studentRollNumber) {
                    StudentDTOSS dto = studentService.getByID(studentRollNumber);
                    return ResponseEntity.ok(dto);
                }
                @DeleteMapping("/{studentRollNumber}")

                public ResponseEntity<StudentDTOSS>deleteStudent(@PathVariable String studentRollNumber){
                    StudentDTOSS studentDTOSS = studentService.deleteStudent(studentRollNumber);
                    return ResponseEntity.ok(studentDTOSS);
                }
                @PatchMapping("/{studentRollNumber}/patchmapping")

                public ResponseEntity<StudentDTOSS>updateStudent(@RequestBody StudentDTOSS studentDTOSS,@PathVariable String
                                                                 studentRollNumber){
                    StudentDTOSS Dtoss = studentService.updateStudent(studentRollNumber,studentDTOSS);
                    return ResponseEntity.ok(Dtoss);
                }

                @PutMapping("/{studentRollNumber}/putupdateStudent")

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
                public ResponseEntity<List<SearchStudent>> searchByid (@RequestParam String keyword){
                    List<SearchStudent> searchStudents = studentService.searchByStudentreference(keyword);

                    return ResponseEntity.ok(searchStudents);
                }

            }
