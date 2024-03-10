# Employee Maven Springboot API

An api made with springboot and maven.

## API Documentation

### Entry Level

1. **Add Employee**

   - Method: POST
   - Endpoint: `http://localhost:8080/employees`
   - Request Body:
     ```json
     {
       "employeeName": "Arun",
       "phoneNumber": "1234567890",
       "email": "arun@gmail.com",
       "reportsTo": "managerId",
       "profileImage": "https://example.com/profile.jpg"
     }
     ```
   - Response:

     ```json
     {
       "id": "generatedId",
       "employeeName": "Arun",
       "phoneNumber": "1234567890",
       "email": "arun@gmail.com",
       "reportsTo": "managerId",
       "profileImage": "https://example.com/profile.jpg"
     }
     ```

     Note: Use the generated ID of previous employees as the manager ID for adding new employees.

2. **Get All Employees**

   - Method: GET
   - Endpoint: `http://localhost:8080/employees`
   - Response: List of all employees.

3. **Delete Employee by ID**

   - Method: DELETE
   - Endpoint: `http://localhost:8080/employees/{employeeId}`

4. **Update Employee by ID**
   - Method: PUT
   - Endpoint: `http://localhost:8080/employees/{employeeId}`
   - Request Body: Same as Add Employee

### Intermediate

5. **Get nth Level Manager of an Employee**

   - Method: GET
   - Endpoint: `http://localhost:8080/employees/managers/{employeeId}?level={n}`

6. **Get Employees with Pagination and Sorting**
   - Method: GET
   - Endpoint: `http://localhost:8080/employees/paginate?page={page}&size={size}&sort={field}`

### Advanced

7. **Send Email to Level 1 Manager on New Employee Addition**
   - Method: Implemented automatically on new employee addition.
   - Email Content: "<EmployeeName> will now work under you. Mobile number is <PhoneNumber> and email is <EmailId>".

## Additional Features

- Validations and error handling are implemented for every API route.
- Email notifications are sent to the level 1 manager when a new employee is added.
- Pagination and sorting options are available for retrieving employees.

## Authors

- [@Sour-abh-Raj](https://www.github.com/Sour-abh-Raj)

## License

[MIT](https://choosealicense.com/licenses/mit/)

## Run Locally

Get Maven and add it to your environment variables.
https://maven.apache.org/download.cgi

Go to the project directory

```bash
  mvn spring-boot:run
```
