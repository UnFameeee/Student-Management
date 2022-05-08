package com.example.springbootcloud.entity;

import com.example.springbootcloud.entity.key.ScoreKey;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "score")
@Table(name = "score")
public class Score implements java.io.Serializable {

    @EmbeddedId
    private ScoreKey id;

    @Column(name = "scores")
    private String scores;

    public ScoreKey getId() {
        return id;
    }

    public void setId(ScoreKey id) {
        this.id = id;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
