package STudentDevelopmentScratch.SMS.Service.AdminServices;

import STudentDevelopmentScratch.SMS.DTOs.AdminDtos;
import STudentDevelopmentScratch.SMS.DTOs.HideAdminPassDTOS;
import STudentDevelopmentScratch.SMS.Entity.AdminEntity;
import STudentDevelopmentScratch.SMS.Repositry.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminService implements AdminserviceImpl{

    private final AdminRepo adminRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public List<AdminDtos>getDeatilsofAdmins() {
        List<AdminEntity> adminEntity = adminRepo.findAll();

        return adminEntity.
                stream()
                .map(entity ->{
                    AdminDtos dtos = modelMapper
                            .map(entity,AdminDtos.class);
                    dtos.setAdminPass(null);
                    return dtos;
                }).toList();

    }

    public AdminDtos postData(AdminDtos adminDtos){

        AdminEntity entity = modelMapper.map(adminDtos,AdminEntity.class);

        entity.setAdminPass(passwordEncoder.encode(adminDtos.getAdminPass()));

        AdminEntity savedEntity = adminRepo.save(entity);

        return modelMapper.map(savedEntity,AdminDtos.class);

    }

    public AdminDtos getbyID(String adminEmail){


        AdminEntity  entity = adminRepo.findByAdminEmail(adminEmail).orElseThrow(()->
                new RuntimeException("Enter a Valid Email " ));

            AdminDtos dtos = modelMapper.map(entity,AdminDtos.class);
            dtos.setAdminPass(null);
            return dtos;


    }

}
