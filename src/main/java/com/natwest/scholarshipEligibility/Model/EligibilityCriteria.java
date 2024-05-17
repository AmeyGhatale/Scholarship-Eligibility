package com.natwest.scholarshipEligibility.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class EligibilityCriteria {

    @Id
    private Integer id;

    private Integer scienceMarks;

    private Integer mathMarks;

    private Integer englishMarks;

    private Integer computerMarks;


    public EligibilityCriteria() {
        this.id = 1;
        this.scienceMarks = 85;
        this.mathMarks = 90;
        this.englishMarks = 75;
        this.computerMarks = 95;
    }
}
