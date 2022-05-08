package com.example.springbootcloud.controller;

import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.entity.Teacher;
import com.example.springbootcloud.model.dto.TeacherDTO;
import com.example.springbootcloud.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<?> getListTeacher(){
        return ResponseEntity.ok(teacherService.getListTeacher());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@RequestBody TeacherDTO req, @PathVariable("id") Long id){
        req.setTeacher_id(id);
        TeacherDTO result = teacherService.updateTeacher(req);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> updateTeacher(@RequestBody String imageURL){
        return ResponseEntity.ok(teacherService.updateTeacherImage(imageURL));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable("id") Long id) {
        TeacherDTO result = teacherService.getTeacherById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping ("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return "Success";
    }
}
