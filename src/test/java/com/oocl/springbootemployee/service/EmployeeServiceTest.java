package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.EmployeeAgeNotValidException;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.creat(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_return_null_when_create_given_employee_age_less_than_18_age() {

        //Given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);

        Employee kitty = new Employee(1, "Kitty", 15, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(kitty);

        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //When

        //Then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(kitty));
        verify(mockedEmployeeRepository, never()).addEmployee(any());

    }

    @Test
    void should_return_null_when_create_given_employee_age_more_than_65_age() {

        //Given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);

        Employee kitty = new Employee(1, "Kitty", 68, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(kitty);

        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //When

        //Then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(kitty));
        verify(mockedEmployeeRepository, never()).addEmployee(any());

    }
}
