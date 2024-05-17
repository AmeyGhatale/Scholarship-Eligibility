package com.natwest.scholarshipEligibility.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.natwest.scholarshipEligibility.DTO.StudentRequest;
import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StudentServiceTest {
    @MockBean
    private EligibilityRepository eligibilityRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;



    @Test
    void testCheckEligibility() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(95);
        eligibilityCriteria.setEnglishMarks(75);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(90);
        eligibilityCriteria.setScienceMarks(85);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).findAll();
    }


    @Test
    void testCheckEligibility2() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(95);
        eligibilityCriteria.setEnglishMarks(75);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(90);
        eligibilityCriteria.setScienceMarks(85);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(45);
        student.setEligibility("No");
        student.setEnglish(75);
        student.setMath(85);
        student.setName("Amey");
        student.setRollNumber(1L);
        student.setScience(65);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Student student2 = new Student();
        student2.setComputer(96);
        student2.setEligibility("Eligibility");
        student2.setEnglish(96);
        student2.setMath(96);
        student2.setName("Rohan");
        student2.setRollNumber(4L);
        student2.setScience(98);

        Student student3 = new Student();
        student3.setComputer(65);
        student3.setEligibility("Eligibility");
        student3.setEnglish(96);
        student3.setMath(76);
        student3.setName("Shyam");
        student3.setRollNumber(3L);
        student3.setScience(68);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();

    }



    @Test
    void testCheckEligibility3() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(-1);
        when(eligibilityCriteria.getScienceMarks()).thenReturn(1);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Student student2 = new Student();
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).getScienceMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }



    @Test
    void testCheckEligibility4() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(1);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Student student2 = mock(Student.class);
        when(student2.getComputer()).thenReturn(1);
        doNothing().when(student2).setComputer(Mockito.<Integer>any());
        doNothing().when(student2).setEligibility(Mockito.<String>any());
        doNothing().when(student2).setEnglish(Mockito.<Integer>any());
        doNothing().when(student2).setMath(Mockito.<Integer>any());
        doNothing().when(student2).setName(Mockito.<String>any());
        doNothing().when(student2).setRollNumber(Mockito.<Long>any());
        doNothing().when(student2).setScience(Mockito.<Integer>any());
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(student2).getComputer();
        verify(student2).setComputer(eq(1));
        verify(student2, atLeast(1)).setEligibility(Mockito.<String>any());
        verify(student2).setEnglish(eq(1));
        verify(student2).setMath(eq(1));
        verify(student2).setName(eq("Name"));
        verify(student2).setRollNumber(eq(1L));
        verify(student2).setScience(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#checkEligibility()}
     */
    @Test
    void testCheckEligibility5() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(0);
        when(eligibilityCriteria.getScienceMarks()).thenReturn(1);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Student student2 = mock(Student.class);
        when(student2.getScience()).thenReturn(1);
        when(student2.getComputer()).thenReturn(1);
        doNothing().when(student2).setComputer(Mockito.<Integer>any());
        doNothing().when(student2).setEligibility(Mockito.<String>any());
        doNothing().when(student2).setEnglish(Mockito.<Integer>any());
        doNothing().when(student2).setMath(Mockito.<Integer>any());
        doNothing().when(student2).setName(Mockito.<String>any());
        doNothing().when(student2).setRollNumber(Mockito.<Long>any());
        doNothing().when(student2).setScience(Mockito.<Integer>any());
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).getScienceMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(student2).getComputer();
        verify(student2).getScience();
        verify(student2).setComputer(eq(1));
        verify(student2, atLeast(1)).setEligibility(Mockito.<String>any());
        verify(student2).setEnglish(eq(1));
        verify(student2).setMath(eq(1));
        verify(student2).setName(eq("Name"));
        verify(student2).setRollNumber(eq(1L));
        verify(student2).setScience(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#checkEligibility()}
     */
    @Test
    void testCheckEligibility6() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(0);
        when(eligibilityCriteria.getMathMarks()).thenReturn(1);
        when(eligibilityCriteria.getScienceMarks()).thenReturn(0);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Student student2 = mock(Student.class);
        when(student2.getMath()).thenReturn(1);
        when(student2.getScience()).thenReturn(1);
        when(student2.getComputer()).thenReturn(1);
        doNothing().when(student2).setComputer(Mockito.<Integer>any());
        doNothing().when(student2).setEligibility(Mockito.<String>any());
        doNothing().when(student2).setEnglish(Mockito.<Integer>any());
        doNothing().when(student2).setMath(Mockito.<Integer>any());
        doNothing().when(student2).setName(Mockito.<String>any());
        doNothing().when(student2).setRollNumber(Mockito.<Long>any());
        doNothing().when(student2).setScience(Mockito.<Integer>any());
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).getMathMarks();
        verify(eligibilityCriteria).getScienceMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(student2).getComputer();
        verify(student2).getMath();
        verify(student2).getScience();
        verify(student2).setComputer(eq(1));
        verify(student2, atLeast(1)).setEligibility(Mockito.<String>any());
        verify(student2).setEnglish(eq(1));
        verify(student2).setMath(eq(1));
        verify(student2).setName(eq("Name"));
        verify(student2).setRollNumber(eq(1L));
        verify(student2).setScience(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#checkEligibility()}
     */
    @Test
    void testCheckEligibility7() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(0);
        when(eligibilityCriteria.getEnglishMarks()).thenReturn(1);
        when(eligibilityCriteria.getMathMarks()).thenReturn(0);
        when(eligibilityCriteria.getScienceMarks()).thenReturn(0);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Student student2 = mock(Student.class);
        when(student2.getEnglish()).thenReturn(1);
        when(student2.getMath()).thenReturn(1);
        when(student2.getScience()).thenReturn(1);
        when(student2.getComputer()).thenReturn(1);
        doNothing().when(student2).setComputer(Mockito.<Integer>any());
        doNothing().when(student2).setEligibility(Mockito.<String>any());
        doNothing().when(student2).setEnglish(Mockito.<Integer>any());
        doNothing().when(student2).setMath(Mockito.<Integer>any());
        doNothing().when(student2).setName(Mockito.<String>any());
        doNothing().when(student2).setRollNumber(Mockito.<Long>any());
        doNothing().when(student2).setScience(Mockito.<Integer>any());
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).getEnglishMarks();
        verify(eligibilityCriteria).getMathMarks();
        verify(eligibilityCriteria).getScienceMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(student2).getComputer();
        verify(student2).getEnglish();
        verify(student2).getMath();
        verify(student2).getScience();
        verify(student2).setComputer(eq(1));
        verify(student2, atLeast(1)).setEligibility(Mockito.<String>any());
        verify(student2).setEnglish(eq(1));
        verify(student2).setMath(eq(1));
        verify(student2).setName(eq("Name"));
        verify(student2).setRollNumber(eq(1L));
        verify(student2).setScience(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#checkEligibility()}
     */
    @Test
    void testCheckEligibility8() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        when(eligibilityCriteria.getComputerMarks()).thenReturn(0);
        when(eligibilityCriteria.getEnglishMarks()).thenReturn(0);
        when(eligibilityCriteria.getMathMarks()).thenReturn(0);
        when(eligibilityCriteria.getScienceMarks()).thenReturn(0);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Student student = new Student();
        student.setComputer(1);
        student.setEligibility("Eligibility");
        student.setEnglish(1);
        student.setMath(1);
        student.setName("Name");
        student.setRollNumber(1L);
        student.setScience(1);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Student student2 = mock(Student.class);
        when(student2.getEnglish()).thenReturn(1);
        when(student2.getMath()).thenReturn(1);
        when(student2.getScience()).thenReturn(1);
        when(student2.getComputer()).thenReturn(1);
        doNothing().when(student2).setComputer(Mockito.<Integer>any());
        doNothing().when(student2).setEligibility(Mockito.<String>any());
        doNothing().when(student2).setEnglish(Mockito.<Integer>any());
        doNothing().when(student2).setMath(Mockito.<Integer>any());
        doNothing().when(student2).setName(Mockito.<String>any());
        doNothing().when(student2).setRollNumber(Mockito.<Long>any());
        doNothing().when(student2).setScience(Mockito.<Integer>any());
        student2.setComputer(1);
        student2.setEligibility("Eligibility");
        student2.setEnglish(1);
        student2.setMath(1);
        student2.setName("Name");
        student2.setRollNumber(1L);
        student2.setScience(1);

        Student student3 = new Student();
        student3.setComputer(1);
        student3.setEligibility("Eligibility");
        student3.setEnglish(1);
        student3.setMath(1);
        student3.setName("Name");
        student3.setRollNumber(1L);
        student3.setScience(1);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student3);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        studentService.checkEligibility();

        // Assert that nothing has changed
        verify(eligibilityCriteria).getComputerMarks();
        verify(eligibilityCriteria).getEnglishMarks();
        verify(eligibilityCriteria).getMathMarks();
        verify(eligibilityCriteria).getScienceMarks();
        verify(eligibilityCriteria).setComputerMarks(eq(1));
        verify(eligibilityCriteria).setEnglishMarks(eq(1));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria).setMathMarks(eq(1));
        verify(eligibilityCriteria).setScienceMarks(eq(1));
        verify(student2).getComputer();
        verify(student2).getEnglish();
        verify(student2).getMath();
        verify(student2).getScience();
        verify(student2).setComputer(eq(1));
        verify(student2, atLeast(1)).setEligibility(Mockito.<String>any());
        verify(student2).setEnglish(eq(1));
        verify(student2).setMath(eq(1));
        verify(student2).setName(eq("Name"));
        verify(student2).setRollNumber(eq(1L));
        verify(student2).setScience(eq(1));
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).save(isA(Student.class));
        verify(studentRepository).findAll();
    }



    @Test
    void testAddStudent() {
        // Arrange
        Student student = new Student();
        student.setComputer(78);
        student.setEligibility("Eligibility");
        student.setEnglish(89);
        student.setMath(85);
        student.setName("Rohan");
        student.setRollNumber(1L);
        student.setScience(32);
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setComputer(78);
        studentRequest.setEligibility("Eligibility");
        studentRequest.setEnglish(89);
        studentRequest.setMath(85);
        studentRequest.setName("Amey");
        studentRequest.setRollNumber(2L);
        studentRequest.setScience(32);

        // Act
        studentService.addStudent(studentRequest);

        // Assert
        verify(studentRepository).save(isA(Student.class));
    }



    @Test
    void testGetStudent() {
        // Arrange
        Student student = new Student();
        student.setComputer(85);
        student.setEligibility("Eligibility");
        student.setEnglish(45);
        student.setMath(75);
        student.setName("Amey");
        student.setRollNumber(1L);
        student.setScience(84);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student);

        // Act
        String actualStudent = studentService.getStudent(1L);

        // Assert
        verify(studentRepository).findStudentByRollNumber(eq(1L));
        assertEquals("Eligibility", actualStudent);
    }



    @Test
    void testGetStudent2() {
        // Arrange
        Student student = mock(Student.class);
        when(student.getEligibility()).thenReturn("Eligibility");
        doNothing().when(student).setComputer(Mockito.<Integer>any());
        doNothing().when(student).setEligibility(Mockito.<String>any());
        doNothing().when(student).setEnglish(Mockito.<Integer>any());
        doNothing().when(student).setMath(Mockito.<Integer>any());
        doNothing().when(student).setName(Mockito.<String>any());
        doNothing().when(student).setRollNumber(Mockito.<Long>any());
        doNothing().when(student).setScience(Mockito.<Integer>any());
        student.setComputer(58);
        student.setEligibility("Eligibility");
        student.setEnglish(78);
        student.setMath(69);
        student.setName("Rohan");
        student.setRollNumber(2L);
        student.setScience(96);
        when(studentRepository.findStudentByRollNumber(Mockito.<Long>any())).thenReturn(student);

        // Act
        String actualStudent = studentService.getStudent(2L);

        // Assert
        verify(student).getEligibility();
        verify(student).setComputer(eq(58));
        verify(student).setEligibility(eq("Eligibility"));
        verify(student).setEnglish(eq(78));
        verify(student).setMath(eq(69));
        verify(student).setName(eq("Rohan"));
        verify(student).setRollNumber(eq(2L));
        verify(student).setScience(eq(96));
        verify(studentRepository).findStudentByRollNumber(eq(2L));
        assertEquals("Eligibility", actualStudent);
    }
}
