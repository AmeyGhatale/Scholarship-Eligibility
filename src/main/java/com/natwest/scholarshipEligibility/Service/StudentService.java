package com.natwest.scholarshipEligibility.Service;

import com.natwest.scholarshipEligibility.DTO.StudentRequest;
import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Data
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
     private EligibilityRepository eligibilityRepository;


@Async("asyncThread")
    public CompletableFuture<List<Student>> checkEligibility(){
        EligibilityCriteria eligibilityCriteria = eligibilityRepository.findById(1).get();
        List<Student> studentList = studentRepository.findAll();

        for(Student ListStudent : studentList)
        {
            Student student = studentRepository.findStudentByRollNumber(ListStudent.getRollNumber());

            if(student.getComputer() > eligibilityCriteria.getComputerMarks()  &&
                    student.getScience() > eligibilityCriteria.getScienceMarks()  &&
                    student.getMath() > eligibilityCriteria.getMathMarks()  &&
                    student.getEnglish() > eligibilityCriteria.getEnglishMarks())
            {
                student.setEligibility("YES");
            }
            else
                student.setEligibility("NO");

            studentRepository.save(student);
        }
        return CompletableFuture.completedFuture(studentList);
    }

    public Student addStudent(StudentRequest studentRequest){

        Student student = Student.builder()
                .rollNumber(studentRequest.getRollNumber())
                .name(studentRequest.getName())
                .science(studentRequest.getScience())
                .math(studentRequest.getMath())
                .english(studentRequest.getEnglish())
                .computer(studentRequest.getComputer())
                .Eligibility("ToBeChecked")
                .build();

        student = studentRepository.save(student);
        return student;
    }

    public String getStudent(Long rollNumber)
    {
        Student student = studentRepository.findStudentByRollNumber(rollNumber);

        if(student==null)
            return "NA";
        else
            return student.getEligibility();
    }

}
