package STudentDevelopmentScratch.SMS.Service.AdminService;

import STudentDevelopmentScratch.SMS.DTOs.AdminDtos;

import java.util.List;

public interface Adminservices {


  List<AdminDtos>getDeatilsofAdmins();

  AdminDtos postData(AdminDtos adminDtos);

  AdminDtos getbyID(String adminEmail);
}
