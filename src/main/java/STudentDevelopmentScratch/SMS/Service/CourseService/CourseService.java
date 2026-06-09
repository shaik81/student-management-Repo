    package STudentDevelopmentScratch.SMS.Service.CourseService;

    import STudentDevelopmentScratch.SMS.DTOs.CourseDTOS;
    import STudentDevelopmentScratch.SMS.DTOs.SearchCourse;
    import STudentDevelopmentScratch.SMS.DTOs.SearchStudent;
    import STudentDevelopmentScratch.SMS.Entity.CourseEntity;
    import STudentDevelopmentScratch.SMS.Entity.StudentEntity;
    import STudentDevelopmentScratch.SMS.Repositry.CourseRepo;
    import lombok.RequiredArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

    @Service
    @RequiredArgsConstructor

    public class  CourseService implements CourseofStudent {
        private final CourseRepo courseRepo;
        private final ModelMapper modelMapper;

        @Transactional
        public List<CourseDTOS> createCourse(List<CourseDTOS> courseDTOS) {
            List<CourseEntity> courseEntities = courseDTOS.stream()
                    .map(DTOS -> modelMapper.map(DTOS, CourseEntity.class))
                    .toList();
            List<CourseEntity> savedCourse = courseRepo.saveAll(courseEntities);

            return savedCourse.stream()
                    .map(entity -> modelMapper.map(entity, CourseDTOS.class))
                    .toList();
        }

        public List<CourseDTOS> getAllcourse() {
            List<CourseEntity> getCourse = courseRepo.findAll();
            return getCourse.stream()
                    .map(courseEntity -> modelMapper.map(courseEntity, CourseDTOS.class))
                    .toList();
        }

        public CourseDTOS getBuIDCourse(String courseCode) {
            CourseEntity courseEntity = courseRepo.findBycourseCode(courseCode).orElseThrow(() -> new RuntimeException("Enter a Valid Course Code : " + courseCode));
            return modelMapper.map(courseEntity, CourseDTOS.class);
        }

//        @Transactional
//        public CourseDTOS deleteBYID(String courseCode) {
//            CourseEntity courseEntity = courseRepo.findBycourseCode(courseCode).orElseThrow(() -> new RuntimeException(
//
//                    "Enter a Valid Course Code " + courseCode));
//            courseRepo.delete(courseEntity);
//
//            return modelMapper.map(courseEntity, CourseDTOS.class);
//        }

        public CourseDTOS updateStudent(String courseCode, CourseDTOS courseDTOS) {
            CourseEntity entity = courseRepo.findBycourseCode(courseCode).orElseThrow(() -> new RuntimeException("" +
                    "Enter a Valid Course code"));
            if (courseDTOS.getCourseName() != null && !courseDTOS.getCourseName().isBlank()) {
                entity.setCourseName(courseDTOS.getCourseName());
            }
            if (courseDTOS.getCourseDept() != null && !courseDTOS.getCourseDept().isBlank()) {
                entity.setCourseDept(courseDTOS.getCourseDept());

            }
            if (courseDTOS.getCourseCredits() != null && courseDTOS.getCourseCredits() > 0) {
                entity.setCourseCredits(courseDTOS.getCourseCredits());
            }
            CourseEntity courseEntity = courseRepo.save(entity);

            CourseDTOS dtos = modelMapper.map(courseEntity, CourseDTOS.class);
            return dtos;
        }

        public CourseDTOS deletecourse(String courseCode, CourseDTOS courseDTOS) {
            CourseEntity entity = courseRepo.findBycourseCode(courseCode).orElseThrow(() -> new RuntimeException("" +
                    "Enter a Valid Code of Course" + courseCode));
            courseRepo.delete(entity);
            CourseDTOS dtos = modelMapper.map(entity, CourseDTOS.class);
            return dtos;
        }

        public List<SearchCourse> searchByCourse(String keyword) {
            List<CourseEntity> entities = courseRepo.searchCourse(keyword);

            if (entities.isEmpty()) {
                throw new RuntimeException("Course Not found");

            }
            return entities.stream()
                    .map(mapping->modelMapper.map(mapping,SearchCourse.class))
                    .toList();
        }
    }

