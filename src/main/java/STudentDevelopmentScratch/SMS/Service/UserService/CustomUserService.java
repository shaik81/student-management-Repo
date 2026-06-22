package STudentDevelopmentScratch.SMS.Service.UserService;

import STudentDevelopmentScratch.SMS.Entity.AdminEntity;
import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
import STudentDevelopmentScratch.SMS.Repositry.AdminRepo;
import STudentDevelopmentScratch.SMS.Repositry.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomUserService implements UserDetailsService {

    private final AdminRepo adminRepo;
    private final StudentRepo studentRepo;


    @Override
    public UserDetails loadUserByUsername(String username) {

        AdminEntity adminEntity = adminRepo.findByAdminEmail(username).orElse(null);

        if (adminEntity != null){
            return User.builder()
                    .username(adminEntity.getAdminEmail())
                    .password(adminEntity.getAdminPass())
                    .roles(adminEntity.getRoles().name())
                    .build();
        }

        StudentEntity studentEntity =
                studentRepo.findByStudentRollNumber(username).orElse(null);
        if (studentEntity!=null){
            return User.builder()
                    .username(studentEntity.getStudentRollNumber())
                    .password(studentEntity.getStu_pass())
                    .roles(studentEntity.getRoles().name())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with the Username" +
                ":" + username);
    }
}
