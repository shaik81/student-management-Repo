package STudentDevelopmentScratch.SMS.Controller;

import STudentDevelopmentScratch.SMS.DTOs.MarksDTOS;
import STudentDevelopmentScratch.SMS.Service.MarksService.MarkService;
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
@RequestMapping("/Marks")
public class MarksController {
    private final MarkService markService;

    @PostMapping("/{studentRollNumber}")
    public ResponseEntity <MarksDTOS> postmarks(@PathVariable String studentRollNumber,
                                                @RequestBody MarksDTOS marksDTOS){
        MarksDTOS savedDto = markService.postmarks(studentRollNumber,marksDTOS);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
    @GetMapping("/{studentRollNumber}/course/{courseCode}/marks")
    public ResponseEntity<MarksDTOS> getStudentMarksByID(@PathVariable String studentRollNumber ,@PathVariable
    String courseCode){
        MarksDTOS savedDTo = markService.getStudentMarksByID(studentRollNumber,courseCode);

        return ResponseEntity.status(HttpStatus.OK).body(savedDTo);
    }

}
