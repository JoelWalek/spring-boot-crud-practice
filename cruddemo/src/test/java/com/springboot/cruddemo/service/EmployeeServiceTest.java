package com.springboot.cruddemo.service;

import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    //given specific input -> Receive specific response
    //Testing in isolation -- Any dependencies that my class has will be mocked out
    //Mocking with a library Mockito -- Take a class definition and fake the implementation
    //Dependency = any class that it depends on -- @Autowired
    //Looking for behavior NOT functionality
    //Unit testing tests for positive (happy path) and negative (potential errors) in our code

    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDAO employeeDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImpl(employeeDao);
    }
    
    //findAll - accepts no input param
    //returns List of Employees if found
    //throws exception if not found
    
    @Test
    public void givenNoEmployees_whenFindAllIsCalled_thenThrowsException() {
    	
    	//arrange
    	when(employeeDao.findAll()).thenReturn(null);
    	
    	//act
    	assertThrows(ResponseStatusException.class, () -> employeeService.findAll());
    	
    	//assert
    	verify(employeeDao).findAll();
    }
    
    
    @Test
    public void givenEmployeesExist_whenFindAllIsCalled_thenReturnListOfEmployees() {
    	
    	//arrange
    	//not sure why this doesn't work
//    	List<Employee> employees = new ArrayList<Employee>();
//    	when(employeeDao.findAll().thenReturn(employees));
    	
    	//act
    	List<Employee> result = employeeService.findAll();
    	
    	//assert
    	assertNotNull(result);
    	
    }

    // FindById -> Accepts an integer id as input parameter
    //Returns employee if found
    //If not found, throws exception

    @Test
    public void givenInvalidEmployeeId_whenFindByIdIsCalled_thenThrowsException() {
        //arrange
        int id = 7000;
        when(employeeDao.findById(id)).thenReturn(null);

        //act
        assertThrows(ResponseStatusException.class, () -> employeeService.findById(id));
        //assert
        verify(employeeDao).findById(id);
    }

    @Test
    public void givenValidEmployeeId_whenFindByIdIsCalled_thenReturnEmployee() {
        //arrange
        int id = 1;
        Employee employee = new Employee();
        when(employeeDao.findById(id)).thenReturn(employee);
        //act
        Employee result = employeeService.findById(id);

        //assert
        assertNotNull(result);
    }

    @Test
    public void givenValidEmployeeId_whenFindByIdIsCalled_thenCallsEmployeeDao() {
        //arrange
        int id = 1;
        Employee employee = new Employee();
        when(employeeDao.findById(id)).thenReturn(employee);

        //act
        Employee result = employeeService.findById(id);

        //assert
        verify(employeeDao).findById(id);

    }
    
    //save - accepts Employee object as param
    //returns void
    //throws exception if not found
    
    @Test
    public void givenInvalidEmployee_whenSaveIsCalled_thenThrowsException() {
    	
    	//arrange
    	Employee employee = null;
    	//not sure how to handle void function
//    	doNothing().when(employeeDao.save(employee));
    	
    	//act
    	assertThrows(ResponseStatusException.class, () -> employeeService.save(employee));
    	
    	//assert
    	verify(employeeDao).save(employee);
    	
    	
    }
    
//    @Test
//    public void givenValidEmployee_whenSaveIsCalled_thenNotSure() {
//    	
//    	//arrange
//    	Employee employee = null;
//    	doNothing().when(employeeDao.save(employee));
//    	
//    	//act
//    	
//    	//assert
//    	verify(employeeDao).save(employee);
//    	
//    	
//    }
    
    
    
    //deleteById - accepts integer id as param
    //returns void
    //throws exception if not found
    
    @Test
    public void givenInvalidEmployeeID_whenDeleteByIdIsCalled_thenThrowsException() {
    	
    	//arrange
    	int id = 99999;
    	//not sure how to handle void function
//    	doNothing().when(employeeDao.deleteById(id));
    	
    	//act
    	assertThrows(ResponseStatusException.class, () -> employeeService.deleteByInt(id));
    	
    	//assert
    	verify(employeeDao).deleteById(id);
    	
    	
    }
    
//    @Test
//    public void givenValidEmployeeID_whenDeleteByIdIsCalled_thenNotSure() {
//    	
//    	//arrange
//    	int id = 1;
//    	doNothing().when(employeeDao.deleteById(id));
//    	
//    	//act
//
//    	
//    	//assert
//    	verify(employeeDao).deleteById(id);
//    	
//    	
//    }
}
