package com.natwest.scholarshipEligibility.Repository;

import com.natwest.scholarshipEligibility.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select * from students where roll_number = :rollNumber", nativeQuery = true)
    Student findStudentByRollNumber(Long rollNumber);

}
