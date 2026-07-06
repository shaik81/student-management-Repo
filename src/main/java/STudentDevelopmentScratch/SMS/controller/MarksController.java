    package STudentDevelopmentScratch.SMS.controller;

    import STudentDevelopmentScratch.SMS.DTOs.MarksDTOS;
    import STudentDevelopmentScratch.SMS.Service.MarksService.MarkService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;
    import org.yaml.snakeyaml.error.Mark;

    @RestController
    @RequestMapping("/Marks")
    @RequiredArgsConstructor
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
    public class MarksController {

        private final MarkService markService;
        @PreAuthorize("hasRole('Admin')")
        @PostMapping("/{studentRollNumber}/postdata")
        public ResponseEntity<MarksDTOS> postmarks(@PathVariable String studentRollNumber,
                                                   @RequestBody MarksDTOS marksDTOS){
            MarksDTOS savedDto = markService.postmarks(studentRollNumber, marksDTOS);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
        }
        @PreAuthorize("hasRole('Admin')")
        @GetMapping("/{studentRollNumber}/{courseCode}")
        public ResponseEntity<MarksDTOS> getStudentMarksByID(@PathVariable String studentRollNumber,
                                                             @PathVariable String courseCode){
            MarksDTOS savedDTo = markService.getStudentMarksByID(studentRollNumber, courseCode);
            return ResponseEntity.status(HttpStatus.OK).body(savedDTo);
        }
        @PutMapping
        public ResponseEntity<MarksDTOS>updateMarks(@PathVariable String ){

            MarksDTOS updatedto = markService.updateMarks

        }
    }