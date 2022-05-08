package com.example.springbootcloud.converter;

import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setStudent_id(dto.getStudent_id());
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
//        student.setImage(dto.getImage());
        student.setEmail(dto.getEmail());
        student.setBirth(dto.getBirth());
        student.setPhone(dto.getPhone());
        student.setGender(dto.getGender());
        student.setAccount_id(dto.getAccount_id());
        return student;
    }

    public StudentDTO toDTO(Student entity){
        StudentDTO dto = new StudentDTO();
        dto.setStudent_id(entity.getStudent_id());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
//        dto.setImage(entity.getImage());
        dto.setEmail(entity.getEmail());
        dto.setBirth(entity.getBirth());
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setAccount_id(entity.getAccount_id());
        return dto;
    }

    public StudentDTO toDTO_acc_id(Student entity){
        StudentDTO dto = new StudentDTO();
        dto.setAccount_id(entity.getAccount_id());
        return dto;
    }

    public Student toExistingEntity(Student entity, StudentDTO dto){
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
//        entity.setImage(dto.getImage());
        entity.setEmail(dto.getEmail());
        entity.setBirth(dto.getBirth());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        return entity;
    }
}
