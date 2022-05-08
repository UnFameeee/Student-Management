package com.example.springbootcloud.repositories;

import com.example.springbootcloud.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT u FROM student u WHERE u.student_id = ?1")
    Student findStudentByStudentId(Long student_id);

    @Query("SELECT u FROM student u WHERE u.account_id = ?1")
    Student findStudentByAccountId(Long account_id);
//    @Query("SELECT u FROM student u WHERE u.student_id = ?1")
//    Student findStudentByStudentId(Long student_id);
}
