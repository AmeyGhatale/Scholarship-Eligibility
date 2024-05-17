package com.natwest.scholarshipEligibility.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.natwest.scholarshipEligibility.DTO.UpdateCriteriaRequest;
import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EligibilityService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EligibilityServiceTest {
    @MockBean
    private EligibilityRepository eligibilityRepository;

    @Autowired
    private EligibilityService eligibilityService;


    @Test
    void testUpdateCriteria() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(80);
        eligibilityCriteria.setEnglishMarks(89);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(85);
        eligibilityCriteria.setScienceMarks(68);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);

        EligibilityCriteria eligibilityCriteria2 = new EligibilityCriteria();
        eligibilityCriteria2.setComputerMarks(78);
        eligibilityCriteria2.setEnglishMarks(89);
        eligibilityCriteria2.setId(2);
        eligibilityCriteria2.setMathMarks(95);
        eligibilityCriteria2.setScienceMarks(86);
        when(eligibilityRepository.save(Mockito.<EligibilityCriteria>any())).thenReturn(eligibilityCriteria2);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        UpdateCriteriaRequest updateCriteria = new UpdateCriteriaRequest();
        updateCriteria.setComputerMarks(80);
        updateCriteria.setEnglishMarks(69);
        updateCriteria.setMathMarks(86);
        updateCriteria.setScienceMarks(78);

        // Act
        String actualUpdateCriteriaResult = eligibilityService.updateCriteria(updateCriteria);

        // Assert
        verify(eligibilityRepository).findById(eq(1));
        verify(eligibilityRepository).save(isA(EligibilityCriteria.class));
        assertEquals("Eligibility Criteria has been updated", actualUpdateCriteriaResult);
    }


    @Test
    void testUpdateCriteria2() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = mock(EligibilityCriteria.class);
        doNothing().when(eligibilityCriteria).setComputerMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setEnglishMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setId(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setMathMarks(Mockito.<Integer>any());
        doNothing().when(eligibilityCriteria).setScienceMarks(Mockito.<Integer>any());
        eligibilityCriteria.setComputerMarks(85);
        eligibilityCriteria.setEnglishMarks(96);
        eligibilityCriteria.setId(1);
        eligibilityCriteria.setMathMarks(75);
        eligibilityCriteria.setScienceMarks(65);
        Optional<EligibilityCriteria> ofResult = Optional.of(eligibilityCriteria);

        EligibilityCriteria eligibilityCriteria2 = new EligibilityCriteria();
        eligibilityCriteria2.setComputerMarks(86);
        eligibilityCriteria2.setEnglishMarks(69);
        eligibilityCriteria2.setId(8);
        eligibilityCriteria2.setMathMarks(45);
        eligibilityCriteria2.setScienceMarks(78);
        when(eligibilityRepository.save(Mockito.<EligibilityCriteria>any())).thenReturn(eligibilityCriteria2);
        when(eligibilityRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        UpdateCriteriaRequest updateCriteria = new UpdateCriteriaRequest();
        updateCriteria.setComputerMarks(65);
        updateCriteria.setEnglishMarks(89);
        updateCriteria.setMathMarks(78);
        updateCriteria.setScienceMarks(58);

        // Act
        String actualUpdateCriteriaResult = eligibilityService.updateCriteria(updateCriteria);

        // Assert
        verify(eligibilityCriteria, atLeast(1)).setComputerMarks(eq(85));
        verify(eligibilityCriteria, atLeast(1)).setEnglishMarks(eq(96));
        verify(eligibilityCriteria).setId(eq(1));
        verify(eligibilityCriteria, atLeast(1)).setMathMarks(eq(75));
        verify(eligibilityCriteria, atLeast(1)).setScienceMarks(eq(65));
        verify(eligibilityRepository).findById(eq(1));
        verify(eligibilityRepository).save(isA(EligibilityCriteria.class));
        assertEquals("Eligibility Criteria has been updated", actualUpdateCriteriaResult);
    }


    @Test
    void testAddCriteria() {
        // Arrange
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria();
        eligibilityCriteria.setComputerMarks(55);
        eligibilityCriteria.setEnglishMarks(88);
        eligibilityCriteria.setId(99);
        eligibilityCriteria.setMathMarks(77);
        eligibilityCriteria.setScienceMarks(66);
        when(eligibilityRepository.save(Mockito.<EligibilityCriteria>any())).thenReturn(eligibilityCriteria);

        UpdateCriteriaRequest updateCriteriaRequest = new UpdateCriteriaRequest();
        updateCriteriaRequest.setComputerMarks(78);
        updateCriteriaRequest.setEnglishMarks(98);
        updateCriteriaRequest.setMathMarks(58);
        updateCriteriaRequest.setScienceMarks(78);

        // Act
        String actualAddCriteriaResult = eligibilityService.addCriteria(updateCriteriaRequest);

        // Assert
        verify(eligibilityRepository).save(isA(EligibilityCriteria.class));
        assertEquals("New Criteria has been added", actualAddCriteriaResult);
    }
}
