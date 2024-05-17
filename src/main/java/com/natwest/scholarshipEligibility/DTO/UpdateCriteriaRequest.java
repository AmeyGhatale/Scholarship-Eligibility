package com.natwest.scholarshipEligibility.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCriteriaRequest {
    private Integer scienceMarks;

    private Integer mathMarks;

    private Integer englishMarks;

    private Integer computerMarks;
}
