package STudentDevelopmentScratch.SMS.Service.StudentService;

import STudentDevelopmentScratch.SMS.DTOs.LoginRequestDTO;
import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
import STudentDevelopmentScratch.SMS.DTOs.StudentDTOSS;
import STudentDevelopmentScratch.SMS.Entity.CourseEntity;
import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
import STudentDevelopmentScratch.SMS.Enum.Roles;
import STudentDevelopmentScratch.SMS.Repositry.CourseRepo;
import STudentDevelopmentScratch.SMS.Repositry.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements MethodsOFService {

    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;
    private final CourseRepo courseRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public List< StudentDTOSS>CreateStudent(List<StudentDTOSS> studentDTOSS) {

        List<StudentEntity> studentEntities = studentDTOSS.stream()
                .map(dtoss -> {
                    if (dtoss.getCourseCode() == null || dtoss.getCourseCode().isBlank()) {
                        throw new RuntimeException("Course code is required");
                    }
                    CourseEntity courseEntity = courseRepo.findBycourseCode(dtoss.getCourseCode())
                            .orElseThrow(() -> new RuntimeException("Course code not found"));

                    StudentEntity studentEntity = modelMapper.map(dtoss, StudentEntity.class);

                    studentEntity.setRoles(Roles.Student);

                    studentEntity.setStu_pass(passwordEncoder.encode(studentEntity.getStu_pass()));

                    studentEntity.setCourseEntity(courseEntity);

                    return studentEntity;


                })
                .toList();

        List<StudentEntity> entityList = studentRepo.saveAll(studentEntities);

        List<StudentEntity>entities = studentRepo.saveAll(entityList);

        return entityList.stream()
                .map(studentEntity ->{
                    StudentDTOSS dtoss  = modelMapper.map(studentEntity,StudentDTOSS.class);
                    dtoss.setStu_pass(null);
                    return dtoss;
                })
                .toList();
    }



    @Override
    public List<StudentDTOSS> getALLStudents() {
        List<StudentEntity> studentEntities = studentRepo.findAll();
        return studentEntities.stream()
                .map(entity -> {
                    StudentDTOSS dto = modelMapper.map(entity, StudentDTOSS.class);
                    dto.setStu_pass(null); // hide password
                    return dto;
                })
                .toList();
    }

    // ✅ Get student by roll number
    @Override
    public StudentDTOSS getByID(String studentRollNumber) {
        StudentEntity entity = studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() -> new RuntimeException("Student Not found: " + studentRollNumber));
        StudentDTOSS dto = modelMapper.map(entity, StudentDTOSS.class);
        dto.setStu_pass(null);
        return dto;
    }

    // ✅ Delete student
    @Override
    @Transactional
    public StudentDTOSS deleteStudent(String studentRollNumber) {
        StudentEntity entity = studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() -> new RuntimeException("Enter a Valid Student ID: " + studentRollNumber));
        studentRepo.delete(entity);
        StudentDTOSS dto = modelMapper.map(entity, StudentDTOSS.class);
        dto.setStu_pass(null);
        return dto;
    }

    // ✅ Patch update student
    @Override
    @Transactional
    public StudentDTOSS updateStudent(String studentRollNumber, StudentDTOSS studentDTOSS) {
        StudentEntity entity = studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() -> new RuntimeException("Enter a Valid Student ID for Update"));

        if (studentDTOSS.getStuName() != null && !studentDTOSS.getStuName().isBlank()) {
            entity.setStuName(studentDTOSS.getStuName());
        }
        if (studentDTOSS.getStuEmail() != null && !studentDTOSS.getStuEmail().isBlank()) {
            entity.setStuEmail(studentDTOSS.getStuEmail());
        }
        if (studentDTOSS.getStuAge() != null && !studentDTOSS.getStuAge().isBlank()) {
            entity.setStuAge(studentDTOSS.getStuAge());
        }
        if (studentDTOSS.getStuPhone() != null && !studentDTOSS.getStuPhone().isBlank()) {
            entity.setStuPhone(studentDTOSS.getStuPhone());
        }
        if (studentDTOSS.getStuDept() != null && !studentDTOSS.getStuDept().isBlank()) {
            entity.setStuDept(studentDTOSS.getStuDept());
        }
        if (studentDTOSS.getStuAddress() != null && !studentDTOSS.getStuAddress().isBlank()) {
            entity.setStuAddress(studentDTOSS.getStuAddress());
        }
        if (studentDTOSS.getStu_pass() != null && !studentDTOSS.getStu_pass().isBlank()) {
            entity.setStu_pass(passwordEncoder.encode(studentDTOSS.getStu_pass()));
        }
        if (studentDTOSS.getRoles()!=null){
            entity.setRoles(studentDTOSS.getRoles());
        }
        StudentEntity updated = studentRepo.save(entity);
        StudentDTOSS dto = modelMapper.map(updated, StudentDTOSS.class);
        dto.setStu_pass(null);


        return dto;
    }

    @Override
    @Transactional
    public StudentDTOSS putUpdate(String studentRollNumber, StudentDTOSS studentDTOSS) {
        StudentEntity entity = studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() -> new RuntimeException("Enter a Valid Roll Number of Student: " + studentRollNumber));

        entity.setStuName(studentDTOSS.getStuName());
        entity.setStuEmail(studentDTOSS.getStuEmail());
        entity.setStuAge(studentDTOSS.getStuAge());
        entity.setStuPhone(studentDTOSS.getStuPhone());
        entity.setStuAddress(studentDTOSS.getStuAddress());
        entity.setStuDept(studentDTOSS.getStuDept());
        entity.setStu_pass(passwordEncoder.encode(studentDTOSS.getStu_pass()));


        StudentEntity savedEntity = studentRepo.save(entity);
        StudentDTOSS dto = modelMapper.map(savedEntity, StudentDTOSS.class);
        dto.setStu_pass(null);
        return dto;
    }

    @Override
    public StudentDTOSS login(LoginRequestDTO loginRequestDTO) {
        StudentEntity studentEntity = studentRepo.findByStudentRollNumber(loginRequestDTO.getStudentRollNumber())
                .orElseThrow(() -> new RuntimeException("Invalid Roll number"));

        String rawPass = loginRequestDTO.getStu_pass();
        String encodedPass = studentEntity.getStu_pass();

        if (!passwordEncoder.matches(rawPass, encodedPass)) {
            throw new RuntimeException("Invalid Password");
        }

        StudentDTOSS dto = modelMapper.map(studentEntity, StudentDTOSS.class);
        dto.setStu_pass(null);
        return dto;
    }

//    public List <SearchStudent >  searchbyStudentID(String keyword) {
//       List <StudentEntity >  entites = studentRepo.findByStudentRollNumberContainingIgnoreCaseOrStuNameContainingIgnoreCaseOrStuEmailContainingIgnoreCase(
//                keyword,keyword,keyword);
//
//       if (entites.isEmpty()){
//           throw new RuntimeException("Student Not Found");
//       }
//
//       return entites.stream()
//               .map(mapping->modelMapper.map(entites,SearchStudent.class))
//               .collect(Collectors.toList());
//
//    }

    public List<SearchStudent> searchByStudentreference(String keyword) {
        List<StudentEntity> entities = studentRepo.searchStudent(keyword);

        if (entities.isEmpty()) {
            throw new RuntimeException("Student Not Found ");

        }
        return entities.stream()
                .map(mapping->modelMapper.map(mapping,SearchStudent.class))
                .toList();
    }
}

