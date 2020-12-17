package com.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// expose "/employees" endpoint and return list of employees
	@GetMapping("/employees")
	@ResponseStatus (HttpStatus.OK)
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	@ResponseStatus (HttpStatus.OK)
	public Employee getEmployee(@PathVariable int employeeId) {

		return this.employeeService.findById(employeeId);
//
//		Employee theEmployee = employeeService.findById(employeeId);
//
//		if (theEmployee == null) {
//			throw new RuntimeException("Employee id not found - " + employeeId);
//		}
//
//		return theEmployee;
	}
	
	//add mapping for POST /employees
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item instead of update
		
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//add a mapping for PUT /employees
	
	@PutMapping("/employees")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		employeeService.save(employee);
		
		return employee;
	}
	
	//add a mapping for DELETE /employees
	
	@DeleteMapping("/employees/{employeeId}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public String deleteEmployee(@PathVariable int employeeId) {
		
//		Employee tempEmployee = employeeService.findById(employeeId);
		
//		if (tempEmployee == null) {
//			throw new RuntimeException("Employee id not found - " + employeeId);
//		}
		
		employeeService.deleteByInt(employeeId);
		
		return "Deleted employee id - " + employeeId;
	}
	
}
