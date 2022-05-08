package com.example.springbootcloud.repositories;

import com.example.springbootcloud.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("SELECT u FROM account u WHERE u.username = ?1 and u.password = ?2")
    public Account findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM account u WHERE u.account_id = ?1")
    public Account findAccountById(Long account_id);

    @Query("SELECT a, b FROM account a, student b WHERE a.account_id = b.account_id")
    List<Object[]> getStudentAccountList();

    @Query("SELECT a, b FROM account a, teacher b WHERE a.account_id = b.account_id")
    List<Object[]> getTeacherAccountList();
}
