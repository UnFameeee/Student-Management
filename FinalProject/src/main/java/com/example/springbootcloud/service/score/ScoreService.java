package com.example.springbootcloud.service.score;

import com.example.springbootcloud.model.dto.ScoreDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public interface ScoreService {
    ScoreDTO createScore(ScoreDTO scoreDTO);
    void deleteScore(ScoreDTO scoreDTO);
    ArrayList<HashMap<String, String>> getCourseRegistered(Long student_id);
    String checkRegister(ScoreDTO scoreDTO);
    ArrayList<HashMap<String, String>> getListStudentByCourseId(Long course_id);
    void deleteAllScoreByCourseId(Long id);
    void updateScore(ArrayList<HashMap<String, String>> list);
    void deleteAllScoreByStudent_id(Long student_id);
}
