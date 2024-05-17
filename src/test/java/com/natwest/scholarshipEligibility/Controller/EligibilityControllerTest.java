package com.natwest.scholarshipEligibility.Controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.scholarshipEligibility.DTO.UpdateCriteriaRequest;
import com.natwest.scholarshipEligibility.Service.EligibilityService;
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

@ContextConfiguration(classes = {EligibilityController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EligibilityControllerTest {
    @Autowired
    private EligibilityController eligibilityController;

    @MockBean
    private EligibilityService eligibilityService;


    @Test
    void testUpdateCriteria() throws Exception {
        // Arrange
        when(eligibilityService.updateCriteria(Mockito.<UpdateCriteriaRequest>any())).thenReturn("2020-03-01");

        UpdateCriteriaRequest updateCriteriaRequest = new UpdateCriteriaRequest();
        updateCriteriaRequest.setComputerMarks(85);
        updateCriteriaRequest.setEnglishMarks(90);
        updateCriteriaRequest.setMathMarks(78);
        updateCriteriaRequest.setScienceMarks(65);
        String content = (new ObjectMapper()).writeValueAsString(updateCriteriaRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/criteria/updateCriteria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(eligibilityController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    @Test
    void testAddCriteria() throws Exception {
        // Arrange
        when(eligibilityService.addCriteria(Mockito.<UpdateCriteriaRequest>any())).thenReturn("Add Criteria");

        UpdateCriteriaRequest updateCriteriaRequest = new UpdateCriteriaRequest();
        updateCriteriaRequest.setComputerMarks(90);
        updateCriteriaRequest.setEnglishMarks(95);
        updateCriteriaRequest.setMathMarks(96);
        updateCriteriaRequest.setScienceMarks(99);
        String content = (new ObjectMapper()).writeValueAsString(updateCriteriaRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/criteria/addCriteria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(eligibilityController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Criteria"));
    }
}
