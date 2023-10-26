package com.codemyth.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.codemyth.Model.Employee;
import com.codemyth.Repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@PostMapping("/employee")
	public String Createnewemployee(@RequestBody Employee employee)
	{	
			employeerepository.save(employee);
			return "Database created and employee added Done!";
	}
	
	@GetMapping("/employee")
	public ResponseEntity <List<Employee>> getAllEmployee()
	{
		List<Employee> emplist=new ArrayList<>();
		employeerepository.findAll().forEach(emplist::add);
		return new ResponseEntity<List<Employee>>(emplist,HttpStatus.OK);
	}
	
	@GetMapping("employee/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long empid){
        Employee employee = employeerepository.findById(empid).get();
             
        return ResponseEntity.ok(employee);
    }
	
	@PutMapping("/employee/{empid}")
	 public ResponseEntity<Employee> updateEmployee(@PathVariable long empid,@RequestBody Employee employee) {
	        Employee updateEmployee = employeerepository.findById(empid).get();
	                
	        updateEmployee.setEmp_name(employee.getEmp_name());
	        updateEmployee.setEmp_age(employee.getEmp_age());
	        updateEmployee.setEmp_city(employee.getEmp_city());
	        updateEmployee.setEmp_salary(employee.getEmp_salary());
	       

	        employeerepository.save(updateEmployee);

	        return ResponseEntity.ok(updateEmployee);
	    }
	
	@DeleteMapping("employee/{empid}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long empid){

        Employee employee = employeerepository.findById(empid).get();
                
        employeerepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
	 
		 
	 }

