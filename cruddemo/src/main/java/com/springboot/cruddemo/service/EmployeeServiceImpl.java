package com.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl (EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> employees = employeeDAO.findAll();
		if (employees == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employees not found");
		}
		return employees;
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Employee employee = employeeDAO.findById(theId);
		if(employee == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		if(theEmployee == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee object must not be null");
		employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteByInt(int theId) {
		
		//call findById to check if valid ID
		this.findById(theId);

		
		employeeDAO.deleteById(theId);

	}

}
