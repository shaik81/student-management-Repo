package STudentDevelopmentScratch.SMS.Service.AdminServices;

import STudentDevelopmentScratch.SMS.DTOs.AdminDtos;


import java.util.List;

public interface AdminserviceImpl {


    List<AdminDtos>getDeatilsofAdmins();

    AdminDtos postData(AdminDtos adminDtos);

    AdminDtos getbyID(String adminEmail);



}
