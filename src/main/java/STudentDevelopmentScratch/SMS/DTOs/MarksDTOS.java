package STudentDevelopmentScratch.SMS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarksDTOS {

    Integer internalmarks;

    Integer externalmarks;


    private String courseCode;

}
