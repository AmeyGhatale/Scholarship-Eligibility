package com.natwest.scholarshipEligibility.Controller;

import com.natwest.scholarshipEligibility.Model.Student;
import com.natwest.scholarshipEligibility.Repository.EligibilityRepository;
import com.natwest.scholarshipEligibility.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private EligibilityRepository eligibilityRepository;

    private Logger logger= LoggerFactory.getLogger(FileController.class);
    @PostMapping(value = "/uploadCSV", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<String> uploadFiIe (@RequestParam("file") MultipartFile[] files)throws Exception {
        boolean flag = true;
        for(MultipartFile file : files) {
            if (fileService.hasCsvFormat(file))
                fileService.processAndSaveData(file);
            else
                flag = false;
        }
        if (flag)
            return ResponseEntity.status(HttpStatus.OK).body("UpIoaded the file successfully" );

        return ResponseEntity.status (HttpStatus.EXPECTATION_FAILED) . body ("Please upload CSV file");

    }

    @GetMapping("/downloadCSV")
    public void exportCSV(HttpServletResponse response) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        String fileName = "student-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "");

        StatefulBeanToCsv<Student> writer = new StatefulBeanToCsvBuilder<Student>(response.getWriter())
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();

        writer.write(fileService.findAll());
    }
}
