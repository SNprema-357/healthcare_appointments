# healthcare_appointments
CORE API FEATURES:
1.USER MANAGEMENT:
### i. Signup :-
**URL**:http://localhost:8080/api/auth/signup
**METHOD**:'POST'
**BODY**(row JSON):
'''json
{
"name":"Jhon Doe",
"email":"doe12@example.com",
"password":"password123",
"phone":"1233211233"
}
**RESPONCE**
json
"user register successfully"
ii.**LOGIN**
**URL** http://localhost:8080/api/auth/login
**METHOD**:POST
**BODY** (row.JSON):
json
{
"email":"doe12@example.com",
"password":"password123"
}
**RESPONCE**
json
"Profile updated successfully"

iii.**UPDATE PROFILE**
**URL**:http://localhost:8080/api/auth/profile?userId=1
**Method**:PUT
BODY (row,JSON):
**JSON**
{
"name":"updated name",
"phone":"1221124421"
}
**RESPONCE**
json
"Profile updated successfully"

2.**DOCTOR MANAGEMENT**
I.**URL**:http://localhost:8080/api/doctor/register?availability=Full-Time&experience=2&name=Dr.y&specialization==c
**METHOD**:POST
**RESPONCE**:
json
{
"message":"Doctor registeredsuccessfully with ID:4"

ii.**FETCH DOCTOR LIST**
**URL**
.http://localhost:8080/api/doctors/specialization?=Cardiology
.http://localhost:8080/api/doctor/list
**METHOD**:GET
**RESPONCE**
json
{
{
"id":1,
"name""Dr.Jhon Doe",
"specialization":"Cardiology",
"availability":"MON-FRI,9 AM-5 PM"
},
{
"id":2,
"name":"Dr.Jane Smith",
"specialization":"Dermatology",
"availability":"MON-FRI,10 AM-4 PM"
}
}

##3.**APPOINTMENT BOOKING**:
i.**CREATE APPOINTMENT**:
**URL**:http://localhost:8080/api/appointments/book
**METHOD**:POST
BODY (row,JSON);
json:
{
"userId":1,
"doctorId":2,
"appointmentDate":"2024-11-20T14:30:00"
}

**RESPONCE**

json:
{
"id":"1,
"doctorName":"dr.Jane Doe",
"userId":1,
"userName":"jane doe",
"appointmentDate":"2024-11-20T14:30"
}

ii.**VIEW APPOINTMENT**:
**URL**:httml://localhost:8080/api/appointments/user/1
**METHOD**:GET
**CONTENT-TYPE**:application/json
**RESPONCE**:

json:
{
{
"id":1,
"userId":1,
"doctorId":2,
"appointmentDate":"2024-11-20T14:30:00",
"status":"Dr.Jhon Doe",
"userName":"John Smith"
},
{
"id":2,
"userId":1,
"doctorId":3,
"appointmentDate":"2024-11-22T10:00:00",
"status":"scheduled",
"doctorName":"Dr.Jane doe",
"userName":"John Smith"
}

4.**DATABASE CONFIGURATION (MYSQL)**:
**Ensure your application.properties is configured with the following setting for mysql**:
**Properties**:
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_appointment
spring.datasource.username=root
spring.datasource.password=root

5.**POSTMAN COLLECTION**
**HOW TO IMPORT THE POSTMAN COLLECTION**:
1.download the postman collection file.
2.open postman.
3.click on the import button (top left corner).
4.select file and choose the downloadcollection file.
5.the collection will appear in your postman worker and you can starttesting the API endpoint. 























