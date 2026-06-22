package STudentDevelopmentScratch.SMS.DTOs;
import STudentDevelopmentScratch.SMS.Enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentDTOSS{


    private String stuName;


    private String stuEmail;


    private String stuAge;



    private String stuPhone;


    private String stuDept;


    private String stuAddress;


    private String studentRollNumber;

    private String stu_pass;

    private String courseCode;

    private Roles roles;

}



