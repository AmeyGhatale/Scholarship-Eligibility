package com.natwest.scholarshipEligibility.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private Long rollNumber;

    private String name;

    private Integer science;

    private Integer math;

    private Integer english;

    private Integer computer;

    private String Eligibility;


}
