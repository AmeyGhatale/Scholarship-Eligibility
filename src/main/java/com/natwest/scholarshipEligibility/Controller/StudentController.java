package com.natwest.scholarshipEligibility.Controller;

import com.natwest.scholarshipEligibility.DTO.StudentRequest;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;
import com.natwest.scholarshipEligibility.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;


    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentService.addStudent(studentRequest);
        return new ResponseEntity<>("New student has been added with roll number : "+student.getRollNumber(), HttpStatus.OK);
    }


    @GetMapping("/getStudentEligibility/{rollNumber}")
    public ResponseEntity<String> getStudent(@PathVariable("rollNumber") Long rollNumber)
    {
            String eligibility = studentService.getStudent(rollNumber);
            return new ResponseEntity<>(eligibility, HttpStatus.OK);
    }


    @GetMapping(value = "/checkAllStudentsEligibility", produces = "application/json")
    public  ResponseEntity<List<Student>> getUsers(){
        CompletableFuture<List<Student>> student1=studentService.checkEligibility();
        CompletableFuture<List<Student>> student2=studentService.checkEligibility();
        CompletableFuture<List<Student>> student3=studentService.checkEligibility();
        CompletableFuture<List<Student>> student4=studentService.checkEligibility();
        CompletableFuture<List<Student>> student5=studentService.checkEligibility();
        CompletableFuture<List<Student>> student6=studentService.checkEligibility();
        CompletableFuture<List<Student>> student7=studentService.checkEligibility();
        CompletableFuture<List<Student>> student8=studentService.checkEligibility();
        CompletableFuture.allOf(student1,student2,student3, student4,student5,student6,student7,student8).join();

        return  new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }
}
