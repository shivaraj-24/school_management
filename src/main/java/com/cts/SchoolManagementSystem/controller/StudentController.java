package com.cts.SchoolManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.SchoolManagementSystem.dto.StudentDTO;
import com.cts.SchoolManagementSystem.entity.Student;
import com.cts.SchoolManagementSystem.exception.StudentNotFoundException;
import com.cts.SchoolManagementSystem.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentService studentService;

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/getStudents")
    public List<Student> findAllStudents() throws StudentNotFoundException
    {
        List<Student> students = studentService.findAllStudents();
        if(students.isEmpty())
        {
            log.error("Students list is empty.");
            throw new StudentNotFoundException("No any Student Available");
        }
        log.info("Students list fetched.");
        return students;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student>addStudent(@RequestBody @Valid StudentDTO student)
    {
        log.info(student.getFirstName()+" Added successfully.");
        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.CREATED);
    }

    @GetMapping("/findStudentById/{id}")
    public Optional<Student> findStudentById(@PathVariable Long id) throws StudentNotFoundException
    {
        Optional<Student> student =  studentService.findStudentById(id);
        if(student.isEmpty())
        {
            log.error("Student with provided id:"+id+" not found");
            throw new StudentNotFoundException("Student with provided id: "+id+" is not found");

        }
        log.info("Student with id: "+id+" is found.");
        return student;
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public void deleteById(@PathVariable Long id)
    {
        studentService.deleteById(id);
        log.info("Student with id: "+id+" Delete Successfully");
//		System.out.println("Student with id:"+id+" deleted succesfully..!!");
    }

    @GetMapping("/find-by-first-name/{firstName}")
    public List<Student> findStudentByFirstName(@PathVariable String firstName) throws StudentNotFoundException
    {
        List<Student> student= studentService.findStudentByFirstName(firstName);
        if(student.isEmpty())
        {
            log.error("Student with provided First name:"+firstName+" not found");
            throw new StudentNotFoundException("Student with provided name not found");
        }
        log.info("Student with first Name: "+firstName+" is found.");
        return student;
    }


    @GetMapping("/find-by-last-name/{lastName}")
    public List<Student> findStudentByLastName(@PathVariable String lastName) throws StudentNotFoundException
    {
        List<Student> student= studentService.findStudentByLastName(lastName);
        if(student.isEmpty())
        {
            log.error("Student with provided name:"+lastName+" not found");
            throw new StudentNotFoundException("Student with provided name not found");
        }
        log.info("Student with Last Name: "+lastName+" found.");
        return student;
    }

//	this method will update the student in the database

    @PutMapping("/updateStudent")
    public Student updateStudent(@Valid @RequestBody Student student)
    {
        return studentService.updateStudent(student);

    }

    //	This method will give the list of students whose fees is pending
    @GetMapping("/fees-pending/{minFees}")
    public List<Object[]> getStudentsWithPendingFees(@PathVariable("minFees")Long fees) throws StudentNotFoundException
    {
        List<Object[]> students = studentService.getStudentsWithPendingFees(fees);

        if(students.isEmpty())
        {
            log.info("No any Student left to pay the fees.");
            throw new StudentNotFoundException("No any student found with pending fees");
        }
        log.info("Students found with pending fees");
        return students;
    }




}