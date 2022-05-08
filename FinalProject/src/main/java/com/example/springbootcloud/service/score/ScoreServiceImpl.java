package com.example.springbootcloud.service.score;

import com.example.springbootcloud.converter.ScoreConverter;
import com.example.springbootcloud.entity.Course;
import com.example.springbootcloud.entity.Score;
import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.entity.key.ScoreKey;
import com.example.springbootcloud.model.dto.CourseDTO;
import com.example.springbootcloud.model.dto.ScoreDTO;
import com.example.springbootcloud.repositories.ScoreRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreConverter scoreConverter;

    @Override
    public ScoreDTO createScore(ScoreDTO scoreDTO){
        Score score = scoreConverter.toEntity(scoreDTO);
        score = scoreRepository.save(score);
        return scoreConverter.toDTO(score);
    }

    //Check xem là course đó student đã đăng kí hay chưa - Student
    @Override
    public String checkRegister(ScoreDTO scoreDTO){
        Score existingScore = scoreRepository.findScoreByCourseIdAndStudentId(scoreDTO.getCourse_id(), scoreDTO.getStudent_id());
        if(existingScore != null){
            return "Registered";
        }else{
            return "Unregistered";
        }
    }

    @Override
    public void deleteScore(ScoreDTO scoreDTO){
        Score score = scoreConverter.toEntity(scoreDTO);
        scoreRepository.delete(score);
    }

    //Xóa tất cả các score của student đó
    @Override
    public void deleteAllScoreByStudent_id(Long student_id){
        List<Score> scores = scoreRepository.findScoreByStudentId(student_id);
        if(scores != null){
            for(int i = 0 ;i < scores.size(); ++i){
                ScoreDTO scoreDTO = scoreConverter.toDTO(scores.get(i));
                deleteScore(scoreDTO);
            }
        }
    }

    //Khi xóa môn học đi thì tất cả score của student trong môn học đó cũng mất.
    @Override
    public void deleteAllScoreByCourseId(Long id){
        List<Score> scores = scoreRepository.findScoreByCourseId(id);
        if(scores != null){
            for(int i = 0 ;i < scores.size(); ++i){
                ScoreDTO scoreDTO = scoreConverter.toDTO(scores.get(i));
                deleteScore(scoreDTO);
            }
        }
    }

    //In ra danh sách tất cả các course của 1 student - Student
    @Override
    public ArrayList<HashMap<String, String>> getCourseRegistered(Long student_id){
        List<Object[]> list = scoreRepository.selectCourseRegistered(student_id);
        ArrayList<HashMap<String, String>> temp = new ArrayList<>();

        for(int i = 0; i < list.size(); ++i){
            HashMap<String, String> map = new HashMap<>();
            Course course = (Course) list.get(i)[0];
            map.put("course_id", Long.toString(course.getCourse_id()));
            map.put("name", course.getName());
            Score score = (Score) list.get(i)[1];
            map.put("scores", score.getScores());
            temp.add(map);
        }
        return temp;
    }

    //In ra danh sách student của 1 course - Teacher
    @Override
    public ArrayList<HashMap<String, String>> getListStudentByCourseId(Long course_id){
        List<Object[]> list = scoreRepository.selectStudentListByCourseId(course_id);
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i){
            HashMap<String, String> map = new HashMap<>();

            Score score = (Score) list.get(i)[0];
            map.put("scores", score.getScores());

            Student student = (Student) list.get(i)[1];
            map.put("student_id", Long.toString(student.getStudent_id()));
            map.put("firstname", student.getFirstname());
            map.put("lastname", student.getLastname());
//            map.put("image", student.getImage());
            map.put("email", student.getEmail());
            map.put("phone", student.getPhone());
            map.put("birth", student.getBirth());
            map.put("gender", student.getGender());

            result.add(map);
        }
        return result;
    }

    @Override
    public void updateScore(ArrayList<HashMap<String, String>> list){
        for(int i = 0; i < list.size(); ++i){
            Score score = new Score();
            ScoreKey scoreKey = new ScoreKey();
            scoreKey.setCourse_id(Long.parseLong(list.get(i).get("course_id")));
            scoreKey.setStudent_id(Long.parseLong(list.get(i).get("student_id")));
            score.setId(scoreKey);
            score.setScores(list.get(i).get("scores"));
            scoreRepository.save(score);
        }
    }
}
