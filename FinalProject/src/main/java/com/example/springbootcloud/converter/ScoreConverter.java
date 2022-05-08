package com.example.springbootcloud.converter;

import com.example.springbootcloud.entity.Score;
import com.example.springbootcloud.entity.key.ScoreKey;
import com.example.springbootcloud.model.dto.ScoreDTO;
import org.springframework.stereotype.Component;

@Component
public class ScoreConverter {
    public Score toEntity(ScoreDTO dto){
        Score score = new Score();
        ScoreKey id = new ScoreKey();
        id.setCourse_id(dto.getCourse_id());
        id.setStudent_id(dto.getStudent_id());
        score.setId(id);
        score.setScores(dto.getScores());
        return score;
    }

    public ScoreDTO toDTO(Score entity){
        ScoreDTO dto = new ScoreDTO();
        dto.setCourse_id(entity.getId().getCourse_id());
        dto.setStudent_id(entity.getId().getStudent_id());
        dto.setScores(entity.getScores());
        return dto;
    }

    public Score toExistingEntity(Score entity, ScoreDTO dto){
        entity.setScores(dto.getScores());
        return entity;
    }
}
