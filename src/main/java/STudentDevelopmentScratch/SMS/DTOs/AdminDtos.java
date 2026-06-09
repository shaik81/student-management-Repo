package STudentDevelopmentScratch.SMS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class AdminDtos {


    private String adminName;

    private String adminEmail;

    private String adminPhone;

    private String adminDepartment;

    private String adminRole;

    private String adminPass;


}
