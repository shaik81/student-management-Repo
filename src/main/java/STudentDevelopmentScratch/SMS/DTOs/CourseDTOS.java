package STudentDevelopmentScratch.SMS.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CourseDTOS {


    String courseCode;

    String courseName;

    String courseDept;


    Integer courseCredits;


}
