package com.natwest.scholarshipEligibility.Controller;

import com.natwest.scholarshipEligibility.DTO.UpdateCriteriaRequest;
import com.natwest.scholarshipEligibility.Service.EligibilityService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("criteria/")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @PostMapping("updateCriteria")
    public ResponseEntity<String> updateCriteria(@RequestBody UpdateCriteriaRequest updateCriteriaRequest) {
        String response = eligibilityService.updateCriteria(updateCriteriaRequest);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("addCriteria")
    public ResponseEntity<String> addCriteria(@RequestBody UpdateCriteriaRequest updateCriteria)
    {
        String response = eligibilityService.addCriteria(updateCriteria);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
