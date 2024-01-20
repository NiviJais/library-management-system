package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entites.Student;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import com.example.librarymanagementsystem.RequestDtos.ModifyPhoneNumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student){
        Student savedStudent = studentRepository.save(student);

        return "The student has been saved to DB with studentId"+savedStudent.getStudentId();
    }

    public Student findStudentById(Integer studentId)throws Exception{
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        // Validation if studentId entered is correct or not
        if(optionalStudent.isEmpty()){
            throw new Exception("StudentId entered is incorrect");
        }
        Student student = optionalStudent.get();
        return student;
    }

    public String modifyPhnNo(@RequestBody ModifyPhoneNumberRequest modifyPhNo){

        Integer studentId = modifyPhNo.getStudentId();
        String newPhNo = modifyPhNo.getNewPhnNo();

        Student student = studentRepository.findById(studentId).get();
        student.setPhoneNo(newPhNo);

        studentRepository.save(student);

        return "Phone No has been Modified";
    }
}
