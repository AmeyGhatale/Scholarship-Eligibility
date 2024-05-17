package com.natwest.scholarshipEligibility.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private Long rollNumber;

    private String name;

    private Integer science;

    private Integer math;

    private Integer english;

    private Integer computer;

    private  String Eligibility;
}
