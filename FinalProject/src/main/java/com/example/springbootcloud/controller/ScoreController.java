package com.example.springbootcloud.controller;

import com.example.springbootcloud.model.dto.ScoreDTO;
import com.example.springbootcloud.service.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    //Khi student đăng kí môn học nào đó thì sẽ vào sroce với tham số điểm = null
    @PostMapping("")
    public ResponseEntity<?> createScore(@RequestBody ScoreDTO req){
        ScoreDTO result = scoreService.createScore(req);
        return ResponseEntity.ok(result);
    }

    //Nhập điểm cho student của 1 course xác đinh.
    @PutMapping("")
    public ResponseEntity<?> updateScore(@RequestBody ArrayList<HashMap<String, String>> req){
        scoreService.updateScore(req);
        HashMap<String, String> result = new HashMap<>();
        result.put("key", "Success");
        return ResponseEntity.ok(result);
    }

    //Check xem student đã đăng kí môn học đó chưa
    @PostMapping("/check")
    public String checkRegister(@RequestBody ScoreDTO req){
        return scoreService.checkRegister(req);
    }

    //Lấy ra tất cả các khóa học student đó đã đăng kí
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseRegistered(@PathVariable("id") Long id){
        ArrayList<HashMap<String, String>> result = scoreService.getCourseRegistered(id);
        return ResponseEntity.ok(result);
    }

    //student hủy đăng kí khóa học
    @DeleteMapping("")
    public ResponseEntity<?> deleteScore(@RequestBody ScoreDTO req){
        scoreService.deleteScore(req);
        HashMap<String, String> result = new HashMap<>();
        result.put("key", "Success");
        return ResponseEntity.ok(result);
    }

    //in ra danh sách student của môn học đó
    @GetMapping("/course/{id}")
    public ResponseEntity<?> getListStudentByCourse(@PathVariable("id") Long id){
        ArrayList<HashMap<String, String>> result = scoreService.getListStudentByCourseId(id);
        return ResponseEntity.ok(result);
    }
}
