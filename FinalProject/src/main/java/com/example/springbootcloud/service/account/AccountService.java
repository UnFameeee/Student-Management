package com.example.springbootcloud.service.account;

import com.example.springbootcloud.entity.Account;
import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.model.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    Iterable<Account> getListAccount();
    void deleteAccount(AccountDTO accountDTO);
    HashMap<String, String> checkLogin(AccountDTO accountDTO);
    void updateAccountById(AccountDTO accountDTO);
    AccountDTO getAccountById(Long account_id);
    String checkOldPassword(AccountDTO accountDTO);
    ArrayList<HashMap<String, String>> getStudentAccountList();
    ArrayList<HashMap<String, String>> getTeacherAccountList();
    void deleteStudentAccount(Long account_id, Long student_id);
    void deleteTeacherAccount(Long account_id, Long teacher_id);
}
