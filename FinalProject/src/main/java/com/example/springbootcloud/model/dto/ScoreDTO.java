package com.example.springbootcloud.model.dto;

import org.springframework.stereotype.Component;

@Component
public class ScoreDTO {
    private Long student_id;
    private Long course_id;
    private String scores;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
