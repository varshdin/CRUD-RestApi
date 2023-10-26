package com.codemyth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemyth.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	

}
