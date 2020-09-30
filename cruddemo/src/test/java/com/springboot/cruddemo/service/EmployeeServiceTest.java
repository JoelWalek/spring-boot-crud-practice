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
}
