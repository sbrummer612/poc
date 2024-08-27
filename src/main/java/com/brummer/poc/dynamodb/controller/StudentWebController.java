package com.brummer.poc.dynamodb.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import com.brummer.poc.dynamodb.entity.Student;
import com.brummer.poc.dynamodb.service.StudentService;


@Controller
public class StudentWebController {

	private static final Logger logger = LoggerFactory.getLogger(StudentWebController.class);
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/test")
	public String test(Model model) {
		
		Region region = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(region)
                .build();
        listAllTables(ddb);
        ddb.close();
		
		return "index";
	}
	
	@RequestMapping("/students")
	public String index(Model model) {
		logger.info("inside of /students method");
		model.addAttribute("students", studentService.getStudents() );
		return "students";
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "addEditStudent";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student, Model model) {
		String studentId = student.getId();
		if(studentId == null || "".equals(studentId)) {
			student.setId(UUID.randomUUID().toString());
			studentService.addStudent(student);
			model.addAttribute("message", "Student Added");
		}
		else {
			studentService.updateStudent(student, studentId);
			model.addAttribute("message", "Student Updated");
		}
		return "redirect:/students";
	}
	
	@GetMapping("/showStudentForUpdate/{id}")
	public String updateStudentForm(@PathVariable(value = "id") String id, Model model ) {
		Optional<Student> student = studentService.getStudent(id);
		model.addAttribute("student", student);
		return "addEditStudent";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudentWithId(@PathVariable(value = "id") String id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
	
	public static void listAllTables(DynamoDbClient ddb) {
        boolean moreTables = true;
        String lastName = null;

        while (moreTables) {
            try {
                ListTablesResponse response = null;
                if (lastName == null) {
                    ListTablesRequest request = ListTablesRequest.builder().build();
                    response = ddb.listTables(request);
                } else {
                    ListTablesRequest request = ListTablesRequest.builder()
                            .exclusiveStartTableName(lastName).build();
                    response = ddb.listTables(request);
                }

                List<String> tableNames = response.tableNames();
                if (tableNames.size() > 0) {
                    for (String curName : tableNames) {
                        System.out.format("* %s\n", curName);
                    }
                } else {
                    System.out.println("No tables found!");
                    System.exit(0);
                }

                lastName = response.lastEvaluatedTableName();
                if (lastName == null) {
                    moreTables = false;
                }

            } catch (DynamoDbException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
        System.out.println("\nDone!");
    }
	
}
