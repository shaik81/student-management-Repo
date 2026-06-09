package STudentDevelopmentScratch.SMS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String studentRollNumber;

    private String stu_pass;
}
