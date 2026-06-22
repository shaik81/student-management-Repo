package STudentDevelopmentScratch.SMS.controller;



import STudentDevelopmentScratch.SMS.DTOs.AdminDtos;
import STudentDevelopmentScratch.SMS.Service.AdminService.Adminserviceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class Admincontroller {

    private final Adminserviceimpl adminserviceimpl;


    @GetMapping("/alladmins")
    public ResponseEntity<List<AdminDtos>>getDeatilsofAdmins() {
        List<AdminDtos> adminDtos = adminserviceimpl.getDeatilsofAdmins();

        return ResponseEntity.status(HttpStatus.OK).body(adminDtos);
    }

    @PostMapping("/admin/postdata")
    public ResponseEntity<AdminDtos>postData(@RequestBody AdminDtos adminDtos){
        AdminDtos Dtos = adminserviceimpl.postData(adminDtos);

        return ResponseEntity.status(HttpStatus.CREATED).body(Dtos);
    }
    @GetMapping("{adminEmail}/admin")
    public ResponseEntity<AdminDtos>getbyID(@PathVariable String adminEmail){
        AdminDtos dtos = adminserviceimpl.getbyID(adminEmail);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


}
