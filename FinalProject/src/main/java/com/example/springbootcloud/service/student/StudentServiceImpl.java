package com.example.springbootcloud.service.student;

import com.example.springbootcloud.converter.StudentConverter;
import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.global.GlobalVariable;
import com.example.springbootcloud.model.dto.StudentDTO;
import com.example.springbootcloud.repositories.StudentRepository;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    @Override
    public StudentDTO createStudent(Long account_id) {
        Student student = new Student();
        student.setAccount_id(account_id);
        student = studentRepository.save(student);
        return studentConverter.toDTO_acc_id(student);
    }

    @Override
    public Iterable<Student> getListStudent(){return studentRepository.findAll();};

    @Override
    public StudentDTO getStudentByID(Long id){
        Student student = studentRepository.findStudentByStudentId(id);
        assert student != null;
        return studentConverter.toDTO(student);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO){
        Student existingStudent = studentRepository.findById(studentDTO.getStudent_id()).orElse(null);
        assert existingStudent != null;
        Student student = studentConverter.toExistingEntity(existingStudent, studentDTO);
        student = studentRepository.save(student);
        return studentConverter.toDTO(student);
    }

    @Override
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    @Override
    public HashMap<String, String> updateStudentImage(String imageURL){
        Student existingStudent = studentRepository.findById(GlobalVariable.IDuser).orElse(null);
        assert existingStudent != null;
//        existingStudent.setImage(imageURL);
        studentRepository.save(existingStudent);
        return new HashMap<>() {{put("key", "Success");}};
    }
}
