package com.natwest.scholarshipEligibility.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

class FileControllerTest {

    @Test
    void testUploadFiIe() throws Exception{

        ResponseEntity<String> actualUploadFiIeResult = (new FileController()).uploadFiIe(new MultipartFile[]{});

        // Assert
        assertEquals("UpIoaded the file successfully", actualUploadFiIeResult.getBody());
        assertEquals(200, actualUploadFiIeResult.getStatusCodeValue());
        assertTrue(actualUploadFiIeResult.getHeaders().isEmpty());
    }
}
