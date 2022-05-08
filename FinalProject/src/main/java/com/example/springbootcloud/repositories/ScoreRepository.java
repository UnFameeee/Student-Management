package com.example.springbootcloud.repositories;

import com.example.springbootcloud.entity.Score;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    @Query("SELECT u FROM score u WHERE u.id.course_id = ?1 and u.id.student_id =?2")
    Score findScoreByCourseIdAndStudentId(Long course_id, Long student_id);

    @Query("SELECT b, a FROM score a, course b WHERE a.id.course_id = b.course_id and a.id.student_id = ?1")
    List<Object[]> selectCourseRegistered(Long student_id);

    @Query("SELECT a, b FROM score a, student b WHERE a.id.course_id = ?1 and a.id.student_id = b.student_id")
    List<Object[]> selectStudentListByCourseId(Long course_id);

    @Query("SELECT u FROM score u WHERE u.id.course_id = ?1")
    List<Score> findScoreByCourseId(Long course_id);

    @Query("SELECT u FROM score u WHERE u.id.student_id = ?1")
    List<Score> findScoreByStudentId(Long student_id);
}
