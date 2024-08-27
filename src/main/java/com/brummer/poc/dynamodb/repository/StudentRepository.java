package com.brummer.poc.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brummer.poc.dynamodb.entity.Student;


@EnableScan
@Repository
public interface StudentRepository extends CrudRepository<Student,String>{
	
}
