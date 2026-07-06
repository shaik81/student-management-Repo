package STudentDevelopmentScratch.SMS.Service.StudentService;

import STudentDevelopmentScratch.SMS.DTOs.LoginRequestDTO;
import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
import STudentDevelopmentScratch.SMS.DTOs.StudentDTOSS;

import java.util.List;

public interface MethodsOFService {
    List<StudentDTOSS> CreateStudent(List <StudentDTOSS> studentDTOSS);

    List<StudentDTOSS> getALLStudents();

    StudentDTOSS getByID(String studentRollNumber);

    StudentDTOSS deleteStudent(String studentRollNumber);

    StudentDTOSS updateStudent(String studentRollNumber, StudentDTOSS studentDTOSS);

    StudentDTOSS putUpdate(String studentRollNumber, StudentDTOSS studentDTOSS);

    StudentDTOSS login(LoginRequestDTO loginRequestDTO);

//    List < SearchStudent >  searchbyStudentID(String keyword);

    List<SearchStudent>searchByStudentreference(String keyword);
}

