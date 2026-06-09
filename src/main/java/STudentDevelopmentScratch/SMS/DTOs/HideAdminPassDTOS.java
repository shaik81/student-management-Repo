package STudentDevelopmentScratch.SMS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HideAdminPassDTOS {


    private String adminName;

    private String adminEmail;

    private String adminPhone;

    private String adminDepartment;

    private String adminRole;


}
