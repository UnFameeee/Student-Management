package com.example.springbootcloud.service.course;

import com.example.springbootcloud.entity.Course;
import com.example.springbootcloud.model.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);
    ArrayList<HashMap<String, String>> getListCourse(String command, Long id);
    CourseDTO updateCourse(CourseDTO courseDTO);
    List<CourseDTO> selectCourseByTeacherId(Long teacher_id);
    void deleteAllCourseByTeacherId(Long teacher_id);
    void deleteCourseByCourseId(Long course_id);
}
