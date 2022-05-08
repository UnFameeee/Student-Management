package com.example.springbootcloud.converter;

import com.example.springbootcloud.entity.Course;
import com.example.springbootcloud.model.dto.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public Course toEntity(CourseDTO dto){
        Course course = new Course();
        course.setCourse_id(dto.getCourse_id());
        course.setName(dto.getName());
        course.setTeacher_id(dto.getTeacher_id());
        return course;
    }

    public CourseDTO toDTO(Course entity){
        CourseDTO dto = new CourseDTO();
        dto.setCourse_id(entity.getCourse_id());
        dto.setName(entity.getName());
        dto.setTeacher_id(entity.getTeacher_id());
        return dto;
    }

    public Course toExistingEntity(Course entity, CourseDTO dto){
        entity.setName(dto.getName());
        return entity;
    }
}
