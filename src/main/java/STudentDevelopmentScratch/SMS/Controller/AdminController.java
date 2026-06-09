package STudentDevelopmentScratch.SMS.Controller;

import STudentDevelopmentScratch.SMS.DTOs.AdminDtos;
import STudentDevelopmentScratch.SMS.Service.AdminServices.AdminService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class AdminController {

    private final AdminService adminService;


    @GetMapping("/admin")
    public ResponseEntity<List<AdminDtos>>getDeatilsofAdmins() {
        List<AdminDtos> adminDtos = adminService.getDeatilsofAdmins();

        return ResponseEntity.status(HttpStatus.OK).body(adminDtos);
    }

    @PostMapping("/admin/postdata")
    public ResponseEntity<AdminDtos>postData(@RequestBody AdminDtos adminDtos){
        AdminDtos Dtos = adminService.postData(adminDtos);

        return ResponseEntity.status(HttpStatus.CREATED).body(Dtos);
    }
    @GetMapping("{adminEmail}/admin")
    public ResponseEntity<AdminDtos>getbyID(@PathVariable String adminEmail){
        AdminDtos dtos = adminService.getbyID(adminEmail);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
