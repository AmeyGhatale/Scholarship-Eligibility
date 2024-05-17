package com.natwest.scholarshipEligibility.Repository;

import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends JpaRepository<EligibilityCriteria, Integer> {
}
