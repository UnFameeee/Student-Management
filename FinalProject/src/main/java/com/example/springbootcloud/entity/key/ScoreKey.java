package com.example.springbootcloud.entity.key;

import com.example.springbootcloud.entity.Account;
import com.example.springbootcloud.entity.Course;
import com.example.springbootcloud.entity.Student;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ScoreKey implements Serializable {

    @Column(name = "student_id")
    private Long student_id;

    @Column(name = "course_id")
    private Long course_id;

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
}
