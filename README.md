# University
RestApp for students and teachers in University

## HOW TO RUN APP LOCALLY:

### 1. Clone the project

```bash
  git clone https://github.com/kbledzki/University.git
```
### 2. Build project

* Open terminal in project directory
* Type:
  `mvn clean install`

### 3. Start application

Create docker image and run project with IntelliJ:

```bash
docker run -p 3306:3306 --name universityApp -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=univeristy --rm -d mysql
```

If need start date can use "startdatascript.sql" in main resources.
Also can check examples of request in "test-http-methods.http" in main resources


### Api supports operation - CRUD for Students, CRUD for Teachers and also entering and checking rating
   ### Student Operations:
  * GET /api/v1/students
  * GET /api/v1/student/{id}
  * POST /api/v1/student
  * DELETE /api/v1/student/{id}
  * PUT /api/v1/student/{id}

   ### Teacher Operations:
  * GET /api/v1/teachers
  * GET /api/v1/teacher/{id}
  * POST /api/v1/teacher
  * DELETE /api/v1/teacher/{id}
  * PUT /api/v1/teacher/{id}

    ### Grades Operations:
  * GET /api/v1/grade/student?id={id}
  * GET /api/v1/grade/teacher?id={id}
  * GET /api/v1/grades
  * GET /api/v1/grade/{id}
  * POST /api/v1/grade?teacherId={id}&studentId={id}
  * DELETE /api/v1/grade/{id}

### 4. Check documentation of application
* Check in json format:

```bash
  http://localhost:8080/v3/api-docs
```
* Check with ui:
  
```bash
 http://localhost:8080/swagger-ui/index.html
```
   
<a href="https://ibb.co/nQZQMQG"><img src="https://i.ibb.co/KVRVxVP/Screenshot-from-2023-07-31-00-01-17.png" alt="Screenshot-from-2023-07-31-00-01-17" border="0"></a>

### 5. Examples features to do:
* Assigning school subjects to students and teachers, as well as to grades
* Creation of classes and specializations
* Adding a calendar
* Checking attendance
* More statistics
* Sort and pagination


        

          


