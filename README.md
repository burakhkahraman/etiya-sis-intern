# Etiya Internship- Student Information System
This project encompasses Etiya's student automation system, designed to manage student courses, grades, and personal details.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

These instructions will guide you through the process of setting up and running the project on your local machine.

### Prerequisites

The technologies you need before starting:

```
Java 11
Maven 3.6+
PostgreSQL 12+
...
```

### Installation

Step-by-step project setup:

1. Clone the repository.
```bash
git clone [(https://github.com/burakhkahraman/etiya-sis-intern.git)]
```
2. Download dependencies and build the project using Maven.
```bash
mvn clean install
```
3. Navigate to the project directory and run the application.
```bash
cd etiya-student-info-system
./mvnw spring-boot:run
```

## Usage

Basic instructions on how to use the project once it's up and running.

## Database Schema





## API Endpoints

### **Student**

- `GET /api/students`: Fetches a list of all students.
- `GET /api/students/{id}`: Fetches a specific student by their ID.
- `POST /api/students`: Creates a new student.
- `PUT /api/students/{id}`: Updates a student's details by their ID.
- `DELETE /api/students/{id}`: Deletes a specific student.

### **TakenCourses**

- `GET /api/takencourses`: Fetches all taken courses.
- `GET /api/takencourses/{id}`: Retrieves a specific taken course record.
- `POST /api/takencourses`: Creates a new taken course record.
- `PUT /api/takencourses/{id}`: Updates details of a taken course.
- `DELETE /api/takencourses/{id}`: Deletes a taken course record.

### **Course**

- `GET /api/courses`: Fetches all courses.
- `GET /api/courses/{id}`: Retrieves details of a specific course.
- `POST /api/courses`: Adds a new course.
- `PUT /api/courses/{id}`: Updates a course's details.
- `DELETE /api/courses/{id}`: Deletes a specific course.

### **Teacher**

- `GET /api/teachers`: Fetches all teachers.
- `GET /api/teachers/{id}`: Retrieves a specific teacher.
- `POST /api/teachers`: Adds a new teacher.
- `PUT /api/teachers/{id}`: Updates a teacher's details.
- `DELETE /api/teachers/{id}`: Deletes a specific teacher.

### **Grade**

- `GET /api/grades`: Fetches all grades.
- `GET /api/grades/{id}`: Retrieves a specific grade.
- `POST /api/grades`: Creates a new grade.
- `PUT /api/grades/{id}`: Updates grade details.
- `DELETE /api/grades/{id}`: Deletes a specific grade.

### **Class_rel**

- `GET /api/class_rels`: Fetches all class relations.
- `GET /api/class_rels/{id}`: Retrieves a specific class relation.
- `POST /api/class_rels`: Creates a new class relation.
- `PUT /api/class_rels/{id}`: Updates class relation details.
- `DELETE /api/class_rels/{id}`: Deletes a class relation.

### **ResultOfExam**

- `GET /api/results`: Fetches all exam results.
- `GET /api/results/{id}`: Retrieves a specific exam result.
- `POST /api/results`: Records a new exam result.
- `PUT /api/results/{id}`: Updates an exam result's details.
- `DELETE /api/results/{id}`: Deletes a specific exam result.

### **ExamType**

- `GET /api/examtypes`: Fetches all exam types.
- `GET /api/examtypes/{id}`: Retrieves details of a specific exam type.
- `POST /api/examtypes`: Creates a new exam type.
- `PUT /api/examtypes/{id}`: Updates exam type details.
- `DELETE /api/examtypes/{id}`: Deletes a specific exam type.

### **Class**

- `GET /api/classes`: Fetches all classes.
- `GET /api/classes/{id}`: Retrieves details of a specific class.
- `POST /api/classes`: Adds a new class.
- `PUT /api/classes/{id}`: Updates a class's details.
- `DELETE /api/classes/{id}`: Deletes a specific class.

### **Absenteeism**

- `GET /api/absentees`: Fetches all absenteeism records.
- `GET /api/absentees/{id}`: Retrieves a specific absenteeism record.
- `POST /api/absentees`: Records a new absenteeism.
- `PUT /api/absentees/{id}`: Updates an absenteeism record.
- `DELETE /api/absentees/{id}`: Deletes a specific absenteeism record.

### **Users**

- `GET /api/users`: Fetches all users.
- `GET /api/users/{id}`: Retrieves details of a specific user.
- `POST /api/users`: Registers a new user.
- `PUT /api/users/{id}`: Updates a user's details.
- `DELETE /api/users/{id}`: Deletes a user.
- `POST /api/users/login`: Authenticate and login a user.

### **Role**

- `GET /api/roles`: Fetches all roles.
- `GET /api/roles/{id}`: Retrieves details of a specific role.
- `POST /api/roles`: Adds a new role.
- `PUT /api/roles/{id}`: Updates a role's details.
- `DELETE /api/roles/{id}`: Deletes a specific role.

---

Bu end-point listesi, genel bir yapı sunmaktadır. Özel işlevsellikler veya daha fazla detay eklemek isterseniz lütfen belirtin.

## Contributing

If you wish to contribute to this project:

1. Fork it.
2. Create your feature or bug fix branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Create a new Pull Request.

## License

This project is licensed under the [MIT](https://choosealicense.com/licenses/mit/) License.


