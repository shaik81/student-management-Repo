package STudentDevelopmentScratch.SMS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchCourse {

    private String courseCode;

    private String courseName;

     private String courseDept;


}
