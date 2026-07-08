package STudentDevelopmentScratch.SMS.Service.MarksService;

import STudentDevelopmentScratch.SMS.DTOs.MarksDTOS;
import STudentDevelopmentScratch.SMS.Entity.CourseEntity;
import STudentDevelopmentScratch.SMS.Entity.MarksEntity;
import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
import STudentDevelopmentScratch.SMS.Repositry.CourseRepo;
import STudentDevelopmentScratch.SMS.Repositry.MarksRepo;
import STudentDevelopmentScratch.SMS.Repositry.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarkService implements MarksImpl {

    private final MarksRepo marksRepo;
    private final ModelMapper modelMapper;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    // ✅ POST MARKS WITH AUTO-GRADE
    @Transactional
    public MarksDTOS postmarks(String studentRollNumber, MarksDTOS marksDTOS) {

        StudentEntity student = studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with roll number " + studentRollNumber));
        String assignedCoursecode = student.getCourseEntity().getCourseCode();

        CourseEntity course = courseRepo.findBycourseCode(assignedCoursecode)
                .orElseThrow(() ->
                        new RuntimeException("Course not found " +assignedCoursecode));

        MarksEntity marksEntity = new MarksEntity();
        marksEntity.setInternalmarks(marksDTOS.getInternalmarks());
        marksEntity.setExternalmarks(marksDTOS.getExternalmarks());

        // 🔥 AUTO GRADE (SERVER SIDE)
        marksEntity.setDecisiongrade(
                calculateGrade(
                        marksDTOS.getInternalmarks(),
                        marksDTOS.getExternalmarks()
                )
        );

        marksEntity.setStudent(student);
        marksEntity.setCourse(course);
        marksEntity.setStudentRollNumber(student.getStudentRollNumber());

        System.out.println("Student = " + student);
        System.out.println("Student ID = " + student.getID());
        System.out.println("Roll Number = " + student.getStudentRollNumber());

        System.out.println("Course = " + course);
        System.out.println("Course Code = " + course.getCourseCode());

        System.out.println("Marks Student = " + marksEntity.getStudent());
        System.out.println("Marks Student ID = " + marksEntity.getStudent().getID());

        MarksEntity saved = marksRepo.save(marksEntity);
        return modelMapper.map(saved, MarksDTOS.class);
    }

    // ✅ GET MARKS BY STUDENT + COURSE
    public MarksDTOS getStudentMarksByID(String studentRollNumber, String courseCode) {

        studentRepo.findByStudentRollNumber(studentRollNumber)
                .orElseThrow(() ->
                        new RuntimeException("Student not found " + studentRollNumber));

        courseRepo.findBycourseCode(courseCode)
                .orElseThrow(() ->
                        new RuntimeException("Course not found " + courseCode));

        MarksEntity marksEntity =
                marksRepo.findByStudent_StudentRollNumberAndCourse_CourseCode(
                                studentRollNumber, courseCode)
                        .orElseThrow(() ->
                                new RuntimeException("Marks not found for given student and course"));

        return modelMapper.map(marksEntity, MarksDTOS.class);
    }

    // ✅ GRADE CALCULATION LOGIC
    private String calculateGrade(Integer internal, Integer external) {
        int total = internal + external;

        if (total >= 90) return "A+";
        else if (total >= 80) return "A";
        else if (total >= 70) return "B";
        else if (total >= 60) return "C";
        else if (total >= 50) return "D";
        else return "F";
    }

    public MarksDTOS updateMarks(String studentRollNumber,String Coursecode,MarksDTOS marksDTOS) {


        StudentEntity entity = studentRepo.findByStudentRollNumber(studentRollNumber).orElseThrow(
                ()->new RuntimeException("Student not found with roll number " + studentRollNumber)
        );

        CourseEntity courseEntity = courseRepo.findBycourseCode(marksDTOS.getCourseCode()).orElseThrow(
                ()->new RuntimeException("Course not found " + marksDTOS.getCourseCode())
        );
        MarksEntity marksEntity = marksRepo.findByStudent_StudentRollNumberAndCourse_CourseCode(studentRollNumber,
                Coursecode).orElseThrow(()->
                new RuntimeException("Marks are not specified for the student and course"));
        if (marksDTOS.getInternalmarks() != null) {
                marksEntity.setInternalmarks(marksDTOS.getInternalmarks());

        }
        if (marksDTOS.getExternalmarks() != null) {
            marksEntity.setExternalmarks(marksDTOS.getExternalmarks());

        }
        calculateGrade(marksEntity.getInternalmarks(), marksEntity.getExternalmarks());
        marksRepo.save(marksEntity);
        return modelMapper.map(marksEntity, MarksDTOS.class);

    }
}
