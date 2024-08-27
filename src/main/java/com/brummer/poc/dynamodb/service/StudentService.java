package com.brummer.poc.dynamodb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brummer.poc.dynamodb.entity.Student;
import com.brummer.poc.dynamodb.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;

	public Iterable<Student> getStudents() {
		return studentRepo.findAll();
	}

	public Optional<Student> getStudent(String id) {
		Optional<Student> student = studentRepo.findById(id);
		return student;
	}

	public Student updateStudent(Student student, String id) {
//		boolean exists = studentRepo.existsById(id);
		student.setId(id);
		return studentRepo.save(student);
	}

	public void deleteStudent(String id) {
//		boolean exists = studentRepo.existsById(id);
		studentRepo.deleteById(id);	
	}
	
	public Student addStudent(Student student) {		
		return studentRepo.save(student);	
	}
}