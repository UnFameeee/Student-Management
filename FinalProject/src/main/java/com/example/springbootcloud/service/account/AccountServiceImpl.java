package com.example.springbootcloud.service.account;

import com.example.springbootcloud.converter.AccountConverter;
import com.example.springbootcloud.converter.StudentConverter;
import com.example.springbootcloud.converter.TeacherConverter;
import com.example.springbootcloud.entity.Account;
import com.example.springbootcloud.entity.Student;
import com.example.springbootcloud.entity.Teacher;
import com.example.springbootcloud.global.Encoding;
import com.example.springbootcloud.global.GlobalVariable;
import com.example.springbootcloud.model.dto.AccountDTO;
import com.example.springbootcloud.repositories.AccountRepository;
import com.example.springbootcloud.repositories.StudentRepository;
import com.example.springbootcloud.repositories.TeacherRepository;
import com.example.springbootcloud.service.course.CourseService;
import com.example.springbootcloud.service.score.ScoreService;
import com.example.springbootcloud.service.student.StudentService;
import com.example.springbootcloud.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    //Tạo tài khoản
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        accountDTO.setPassword(Encoding.encode(accountDTO.getPassword()));
        Account account = accountConverter.toEntity(accountDTO);
        account = accountRepository.save(account);
        return accountConverter.toDTO(account);
    }

    //Xóa tài khoản
    @Override
    public void deleteAccount(AccountDTO accountDTO){
        accountDTO.setPassword(Encoding.encode(accountDTO.getPassword()));
        Account account = accountConverter.toEntity(accountDTO);
        accountRepository.delete(account);
    }

    @Override
    public Iterable<Account> getListAccount() {
        return accountRepository.findAll();
    }

    //Lấy ra thông tin của account = id
    @Override
    public AccountDTO getAccountById(Long account_id) {
        Account account = accountRepository.findAccountById(account_id);
        account.setPassword(Encoding.decode(account.getPassword()));
        return accountConverter.toDTO(account);
    }

    //Check mk cũ có trùng khớp không
    @Override
    public String checkOldPassword(AccountDTO accountDTO){
        Account account = accountRepository.findAccountById(accountDTO.getAccount_id());
        account.setPassword(Encoding.encode(account.getPassword()));
        if(Objects.equals(account.getPassword(), accountDTO.getPassword())){
            return "Match";
        }else
            return "Not Match";
    }

    //Update thông tin account
    @Override
    public void updateAccountById(AccountDTO accountDTO){
        Account account = accountRepository.findAccountById(accountDTO.getAccount_id());
        accountDTO.setPassword(Encoding.encode(accountDTO.getPassword()));
        account.setPassword(accountDTO.getPassword());
        accountRepository.save(account);
    }

    //Vừa đăng nhập vào thì kiểm tra xem có account đó không, có thì gán global.
    @Override
    public HashMap<String, String> checkLogin(AccountDTO accountDTO){
        accountDTO.setPassword(Encoding.encode(accountDTO.getPassword()));
        Account account = accountRepository.findByUsernameAndPassword(accountDTO.getUsername(), accountDTO.getPassword());
        HashMap<String, String> result = new HashMap<String, String>();

        if(account != null){
            result.put("accid", Long.toString(account.getAccount_id()));
            GlobalVariable.IDaccount = account.getAccount_id();
            GlobalVariable.UserRole = account.getRole();
            if(Objects.equals(account.getRole(), "student")){
                Student student = studentRepository.findStudentByAccountId(account.getAccount_id());
                result.put("userid", Long.toString(student.getStudent_id()));
                GlobalVariable.IDuser = student.getStudent_id();

            }else if(Objects.equals(account.getRole(), "teacher")){
                Teacher teacher = teacherRepository.findTeacherByAccountId(account.getAccount_id());
                result.put("userid", Long.toString(teacher.getTeacher_id()));
                GlobalVariable.IDuser = teacher.getTeacher_id();
            }else if(Objects.equals(account.getRole(), "admin")){
                result.put("userid", Long.toString(0L));
                GlobalVariable.IDuser = 0L;
            }
            result.put("key", "Success");
            return result;
        };

        result.put("key", "Fail");
        return result;
    }

    //Lấy ra danh sách student + info
    @Override
    public ArrayList<HashMap<String, String>> getStudentAccountList() {
        List<Object[]> list = accountRepository.getStudentAccountList();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            HashMap<String, String> map = new HashMap<>();

            Account account = (Account) list.get(i)[0];
            map.put("account_id", Long.toString(account.getAccount_id()));
            map.put("username", account.getUsername());
            map.put("password", Encoding.decode(account.getPassword()));

            Student student = (Student) list.get(i)[1];
            map.put("user_id", Long.toString(student.getStudent_id()));
            map.put("firstname", student.getFirstname());
            map.put("lastname", student.getLastname());
            map.put("email", student.getEmail());
            map.put("phone", student.getPhone());
            map.put("birth", student.getBirth());
            map.put("gender", student.getGender());

            result.add(map);
        }
        return result;
    }

    //Lấy ra danh sách teacher + info
    @Override
    public ArrayList<HashMap<String, String>> getTeacherAccountList() {
        List<Object[]> list = accountRepository.getTeacherAccountList();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            HashMap<String, String> map = new HashMap<>();

            Account account = (Account) list.get(i)[0];
            map.put("account_id", Long.toString(account.getAccount_id()));
            map.put("username", account.getUsername());
            map.put("password", Encoding.decode(account.getPassword()));

            Teacher teacher = (Teacher) list.get(i)[1];
            map.put("user_id", Long.toString(teacher.getTeacher_id()));
            map.put("firstname", teacher.getFirstname());
            map.put("lastname", teacher.getLastname());
            map.put("email", teacher.getEmail());
            map.put("phone", teacher.getPhone());
            map.put("birth", teacher.getBirth());
            map.put("gender", teacher.getGender());

            result.add(map);
        }
        return result;
    }

    //Xóa account student
    @Override
    public void deleteStudentAccount(Long account_id, Long student_id){
        //Xóa score
        scoreService.deleteAllScoreByStudent_id(student_id);
        //Xóa student
        studentService.deleteStudent(student_id);
        //Xóa account
        AccountDTO accountDTO = getAccountById(account_id);
        deleteAccount(accountDTO);
    }

    //Xóa account teacher
    @Override
    public void deleteTeacherAccount(Long account_id, Long teacher_id){
        //Xóa course (hàm này đã có check student và xóa score)
        courseService.deleteAllCourseByTeacherId(teacher_id);
        //Xóa Teacher
        teacherService.deleteTeacher(teacher_id);
        //Xóa account
        AccountDTO accountDTO = getAccountById(account_id);
        deleteAccount(accountDTO);
    }
}
