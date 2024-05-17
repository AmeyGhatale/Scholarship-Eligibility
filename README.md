# Scholarship-Eligibility

Note: 
I have implemented all functionalities which was mention in the assignment. But in swagger there is a error in the upload file function "files/uploadCSV", 
it is not working in swagger properly. So, I request you to please upload the file using Postman with API("http://localhost:8080/files/uploadCSV"). It will get uploaded and can upload multiple files. 
Else all the functionalities are working properly in the swagger including multithreading.  


Functionalies Developed:

STUDENTS API:
1. Add Student (Path = POST "http://localhost:8080/student/addStudent")
2. Get Student Eligibility By Roll Number (Path = GET "http://localhost:8080/student/getStudentE1igibi1ity/{rollNumber}") 
3. Check All Student Eligibility (Path = GET "http://localhost:8080/student/checkAllStudentsEligibility")

FILE API:
1. Upload CSV File Student (Path = POST "http://localhost:8080/files/uploadCSV") 
2. Download CSV File (Path = GET "http://localhost:8080/files/downloadCSV")

Eligibility API:
1. Update Criteria  (Path = POST "http://localhost:8080/criteria/updateCriteria")
2. Add Criteria  (Path = POST "http://localhost:8080/criteria/addCriteria")
