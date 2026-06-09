package STudentDevelopmentScratch.SMS.Repositry;

import STudentDevelopmentScratch.SMS.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity>findByAdminEmail(String email);




}
