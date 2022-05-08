package com.example.springbootcloud.service.course;

import com.example.springbootcloud.converter.CourseConverter;
import com.example.springbootcloud.entity.Course;
import com.example.springbootcloud.entity.Teacher;
import com.example.springbootcloud.model.dto.CourseDTO;
import com.example.springbootcloud.repositories.CourseRepository;
import com.example.springbootcloud.service.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseConverter courseConverter;

    @Autowired
    private ScoreService scoreService;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseConverter.toEntity(courseDTO);
        course = courseRepository.save(course);
        return courseConverter.toDTO(course);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO){
        Course existingCourse = courseRepository.findCourseByCourse_idAndTeacher_id(courseDTO.getCourse_id(), courseDTO.getTeacher_id());
        assert existingCourse != null;
        Course course = courseConverter.toExistingEntity(existingCourse, courseDTO);
        course = courseRepository.save(course);
        return courseConverter.toDTO(course);
    }

    @Override
    public List<CourseDTO> selectCourseByTeacherId(Long teacher_id){
        List<Course> courses = courseRepository.findCourseByTeacher_id(teacher_id);
        List<CourseDTO> coursesDTO = new ArrayList<CourseDTO>();
        for (Course cours : courses) {
            coursesDTO.add(courseConverter.toDTO(cours));
        }
        return coursesDTO;
    }

    @Override
    public void deleteCourseByCourseId(Long course_id){
        scoreService.deleteAllScoreByCourseId(course_id);
        Course course = courseRepository.findCourseByCourse_id(course_id);
        courseRepository.delete(course);
    }

    @Override
    public ArrayList<HashMap<String, String>> getListCourse(String command, Long id){
        List<Object[]> list = courseRepository.findAllCourseAndHaveTeacherName();
        if(Objects.equals(command, "registered")){
            list = courseRepository.findAllCourseAndHaveTeacherNameAndRegistered(id);
        }
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            HashMap<String, String> map = new HashMap<>();
            Course course = (Course) list.get(i)[0];
            map.put("course_id", Long.toString(course.getCourse_id()));
            map.put("name", course.getName());

            Teacher teacher = (Teacher) list.get(i)[1];
            map.put("teacher_name", teacher.getFirstname() + " " + teacher.getLastname());

            result.add(map);
        }
        return result;
    }

    @Override
    public void deleteAllCourseByTeacherId(Long teacher_id) {
        List<Course> courses = courseRepository.findCourseByTeacher_id(teacher_id);
        if(courses != null){
            for(int i = 0 ;i < courses.size(); ++i) {
                scoreService.deleteAllScoreByCourseId(courses.get(i).getCourse_id());
                Course course = courseRepository.findCourseByCourse_id(courses.get(i).getCourse_id());
                courseRepository.delete(course);
            }
        }
    }
}
