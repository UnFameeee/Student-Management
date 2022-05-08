package com.example.springbootcloud.converter;

import com.example.springbootcloud.entity.Teacher;
import com.example.springbootcloud.model.dto.TeacherDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {
    public Teacher toEntity(TeacherDTO dto){
        Teacher teacher = new Teacher();
        teacher.setTeacher_id(dto.getTeacher_id());
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
//        teacher.setImage(dto.getImage());
        teacher.setEmail(dto.getEmail());
        teacher.setBirth(dto.getBirth());
        teacher.setPhone(dto.getPhone());
        teacher.setGender(dto.getGender());
        teacher.setAccount_id(dto.getAccount_id());
        return teacher;
    }

    public TeacherDTO toDTO(Teacher entity){
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacher_id(entity.getTeacher_id());
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
    public TeacherDTO toDTO_acc_id(Teacher entity){
        TeacherDTO dto = new TeacherDTO();
        dto.setAccount_id(entity.getAccount_id());
        return dto;
    }

    public Teacher toExistingEntity(Teacher entity, TeacherDTO dto){
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
