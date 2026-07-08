package STudentDevelopmentScratch.SMS.Service.MarksService;


import STudentDevelopmentScratch.SMS.DTOs.CourseDTOS;
import STudentDevelopmentScratch.SMS.DTOs.MarksDTOS;

import java.util.List;

public interface MarksImpl {
    MarksDTOS postmarks(String studentRollNumber, MarksDTOS marksDTOS);
    MarksDTOS getStudentMarksByID(String studentRollNumebr,String coursecode);
    MarksDTOS updateMarks(String studentRollNumber, String Coursecode,MarksDTOS marksDTOS);
}
