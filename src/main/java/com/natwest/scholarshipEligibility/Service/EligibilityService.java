package com.natwest.scholarshipEligibility.Service;

import com.natwest.scholarshipEligibility.DTO.UpdateCriteriaRequest;
import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EligibilityService {


    @Autowired
    private EligibilityRepository eligibilityRepository;

    public String updateCriteria(UpdateCriteriaRequest updateCriteria)
    {
        EligibilityCriteria eligibilityCriteria = eligibilityRepository.findById(1).get();

        eligibilityCriteria.setComputerMarks(updateCriteria.getComputerMarks());
        eligibilityCriteria.setEnglishMarks(updateCriteria.getEnglishMarks());
        eligibilityCriteria.setMathMarks(updateCriteria.getMathMarks());
        eligibilityCriteria.setScienceMarks(updateCriteria.getScienceMarks());

        eligibilityRepository.save(eligibilityCriteria);

        return "Eligibility Criteria has been updated";
    }

    public String addCriteria(UpdateCriteriaRequest updateCriteriaRequest)
    {
        EligibilityCriteria eligibilityCriteria = EligibilityCriteria.builder()
                .id(1)
                .scienceMarks(updateCriteriaRequest.getScienceMarks())
                .mathMarks(updateCriteriaRequest.getMathMarks())
                .englishMarks(updateCriteriaRequest.getEnglishMarks())
                .computerMarks(updateCriteriaRequest.getComputerMarks())
                .build();

        eligibilityRepository.save(eligibilityCriteria);
        return "New Criteria has been added";
    }
}
