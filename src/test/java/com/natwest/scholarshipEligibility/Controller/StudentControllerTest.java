package com.natwest.scholarshipEligibility.Controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.scholarshipEligibility.DTO.StudentRequest;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;
import com.natwest.scholarshipEligibility.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentService studentService;


    @Test
    void testAddStudent() throws Exception {
        // Arrange
        Student student = new Student();
        student.setComputer(85);
        student.setEligibility("No");
        student.setEnglish(75);
        student.setMath(90);
        student.setName("Amey");
        student.setRollNumber(1L);
        student.setScience(91);
        when(studentService.addStudent(Mockito.<StudentRequest>any())).thenReturn(student);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setComputer(85);
        studentRequest.setEligibility("No");
        studentRequest.setEnglish(75);
        studentRequest.setMath(90);
        studentRequest.setName("Amey");
        studentRequest.setRollNumber(1L);
        studentRequest.setScience(91);
        String content = (new ObjectMapper()).writeValueAsString(studentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/student/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("New student has been added with roll number : 1"));
    }


    @Test
    void testGetStudent() throws Exception {
        // Arrange
        when(studentService.getStudent(Mockito.<Long>any())).thenReturn("Student");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/student/getStudentEligibility/{rollNumber}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Student"));
    }


    @Test
    void testGetStudent2() throws Exception {
        // Arrange
        when(studentService.getStudent(Mockito.<Long>any())).thenReturn("Student");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/student/getStudentEligibility/{rollNumber}", 8L);
        requestBuilder.accept("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

}
