package com.natwest.scholarshipEligibility.Service;

import com.natwest.scholarshipEligibility.Model.EligibilityCriteria;
import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import com.natwest.scholarshipEligibility.Repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class FileService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
     private EligibilityRepository eligibilityRepository;
    EligibilityCriteria eligibilityCriteria;

    Logger logger = LoggerFactory.getLogger(FileService.class);

    public boolean hasCsvFormat(MultipartFile file) {
        String type = "text/csv";
        if (!type.equals(file.getContentType()))
            return false;
        return true;
    }


    @Async("asyncThread")
    public CompletableFuture<List<Student>> processAndSaveData(MultipartFile file) throws Exception{
//        try {
            long start = System.currentTimeMillis();

            List<Student> students = csvToStudents(file.getInputStream());
            logger.info("saving list of student of size {}", students.size(), "" + Thread.currentThread().getName());

            studentRepository.saveAll(students);

            long end = System.currentTimeMillis();
            logger.info("Total time {}", (end - start));
//
        Optional<EligibilityCriteria> op = eligibilityRepository.findById(1);
        if(!op.isPresent()) {
            eligibilityCriteria = new EligibilityCriteria();
            eligibilityRepository.save(eligibilityCriteria);
        }
        return CompletableFuture.completedFuture(students);
    }

    private List<Student> csvToStudents(InputStream inputstream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().withTrim());)
        {
                List<Student> studentList = studentRepository.findAll();
                List<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord csvRecord : records) {
                Student student = new Student(Long.parseLong(csvRecord.get("roll number")), csvRecord.get("student name"),
                        Integer.parseInt(csvRecord.get("science")), Integer.parseInt(csvRecord.get("maths")),
                        Integer.parseInt(csvRecord.get("English")), Integer.parseInt(csvRecord.get("computer")), csvRecord.get("Eligible"));

                studentList.add(student);
            }
            return studentList;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}