package com.example.springbootcloud.service.student;

import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.model.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface StudentService {
    StudentDTO createStudent(Long account_id);
    Iterable<Student> getListStudent();
    StudentDTO updateStudent(StudentDTO studentDTO);
    void deleteStudent(Long id);
    StudentDTO getStudentByID(Long id);
    HashMap<String, String> updateStudentImage(String imageURL);
}
