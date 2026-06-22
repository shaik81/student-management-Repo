package STudentDevelopmentScratch.SMS;

import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
import STudentDevelopmentScratch.SMS.Repositry.StudentRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Disabled("Temporarily Disabling the Test Folder")
class StudentTest {
 @Autowired
    private StudentRepo studentRepo ;

 @Test
    public void teststudetnRepo(){
     List<StudentEntity> entities = studentRepo.findAll();
     System.out.println(entities);
 }

}
