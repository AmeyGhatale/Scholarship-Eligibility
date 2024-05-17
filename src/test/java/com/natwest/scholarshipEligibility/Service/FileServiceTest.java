package com.natwest.scholarshipEligibility.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {FileService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class FileServiceTest {
    @MockBean
    private EligibilityRepository eligibilityRepository;

    @Autowired
    private FileService fileService;

    @MockBean
    private StudentRepository studentRepository;


    @Test
    void testHasCsvFormat() throws IOException {
        // Arrange, Act and Assert
        assertFalse(fileService
                .hasCsvFormat(new MockMultipartFile("Student", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
    }


    @Test
    void testHasCsvFormat2() throws IOException {
        // Arrange
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();

        // Act
        boolean actualHasCsvFormatResult = fileService.hasCsvFormat(new MockMultipartFile("Students", contentStream));

        // Assert
        verify(contentStream).close();
        verify(contentStream).readAllBytes();
        assertFalse(actualHasCsvFormatResult);
    }


    @Test
    void testProcessAndSaveData() throws Exception {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(89);
        eligibilityCriteria.setEnglishMarks(85);
        eligibilityCriteria.setId(5);
        eligibilityCriteria.setMathMarks(45);
        eligibilityCriteria.setScienceMarks(78);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        when(studentRepository.saveAll(Mockito.<Iterable<Student>>any())).thenReturn(new ArrayList<>());

        // Act
        fileService
                .processAndSaveData(new MockMultipartFile("Students", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).findAll();
        verify(studentRepository).saveAll(isA(Iterable.class));
    }



    @Test
    void testProcessAndSaveData2() throws Exception {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(48);
        eligibilityCriteria.setEnglishMarks(85);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(59);
        eligibilityCriteria.setScienceMarks(96);
        when(eligibilityRepository.save(Mockito.<EligibilityCriteria>any())).thenReturn(eligibilityCriteria);
        Optional<EligibilityCriteria> emptyResult = Optional.empty();
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        when(studentRepository.saveAll(Mockito.<Iterable<Student>>any())).thenReturn(new ArrayList<>());

        // Act
        fileService
                .processAndSaveData(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(eligibilityRepository).findById(eq(1));
        verify(eligibilityRepository).save(isA(EligibilityCriteria.class));
        verify(studentRepository).findAll();
        verify(studentRepository).saveAll(isA(Iterable.class));
        EligibilityCriteria eligibilityCriteria2 = fileService.eligibilityCriteria;
        assertEquals(1, eligibilityCriteria2.getId().intValue());
        assertEquals(75, eligibilityCriteria2.getEnglishMarks().intValue());
        assertEquals(85, eligibilityCriteria2.getScienceMarks().intValue());
        assertEquals(90, eligibilityCriteria2.getMathMarks().intValue());
        assertEquals(95, eligibilityCriteria2.getComputerMarks().intValue());
    }

    /**
     * Method under test: {@link FileService#processAndSaveData(MultipartFile)}
     */
    @Test
    void testProcessAndSaveData3() throws Exception {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(1);
        eligibilityCriteria.setEnglishMarks(1);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(1);
        eligibilityCriteria.setScienceMarks(1);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        when(studentRepository.saveAll(Mockito.<Iterable<Student>>any())).thenReturn(new ArrayList<>());

        // Act
        fileService.processAndSaveData(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));

        // Assert
        verify(eligibilityRepository).findById(eq(1));
        verify(studentRepository).findAll();
        verify(studentRepository).saveAll(isA(Iterable.class));
    }


    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        List<Student> actualFindAllResult = fileService.findAll();

        // Assert
        verify(studentRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(studentList, actualFindAllResult);
    }
}
