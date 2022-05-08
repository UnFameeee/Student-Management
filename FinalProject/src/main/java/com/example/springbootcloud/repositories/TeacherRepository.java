package com.example.springbootcloud.repositories;

import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Query("SELECT u FROM teacher u WHERE u.teacher_id = ?1")
    Teacher findTeacherByTeacherId(Long teacher_id);

    @Query("SELECT u FROM teacher u WHERE u.account_id = ?1")
    Teacher findTeacherByAccountId(Long account_id);
}
