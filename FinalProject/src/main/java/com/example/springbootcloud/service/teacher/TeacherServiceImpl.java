package com.example.springbootcloud.service.teacher;

import com.example.springbootcloud.converter.TeacherConverter;
import com.example.springbootcloud.entity.Teacher;
import com.example.springbootcloud.global.GlobalVariable;
import com.example.springbootcloud.model.dto.TeacherDTO;
import com.example.springbootcloud.repositories.TeacherRepository;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherConverter teacherConverter;

    @Override
    public TeacherDTO createTeacher(Long account_id) {
        Teacher teacher = new Teacher();
        teacher.setAccount_id(account_id);
        teacher = teacherRepository.save(teacher);
        return teacherConverter.toDTO_acc_id(teacher);
    }

    @Override
    public Iterable<Teacher> getListTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTO getTeacherById(Long id){
        Teacher teacher = teacherRepository.findTeacherByTeacherId(id);
        assert teacher != null;
        return teacherConverter.toDTO(teacher);
    }

    @Override
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO){
        Teacher existingTeacher = teacherRepository.findById(teacherDTO.getTeacher_id()).orElse(null);
        assert existingTeacher != null;
        Teacher teacher = teacherConverter.toExistingEntity(existingTeacher, teacherDTO);
        teacher = teacherRepository.save(teacher);
        return teacherConverter.toDTO(teacher);
    }

    @Override
    public void deleteTeacher(Long id){
        teacherRepository.deleteById(id);
    }

    @Override
    public HashMap<String, String> updateTeacherImage(String imageURL){
        Teacher existingTeacher = teacherRepository.findById(GlobalVariable.IDuser).orElse(null);
        assert existingTeacher != null;
//        existingTeacher.setImage(imageURL);
        teacherRepository.save(existingTeacher);
        return new HashMap<>() {{put("key", "Success");}};
    }
}
