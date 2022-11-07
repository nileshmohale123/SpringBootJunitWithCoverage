package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

/**
 * @author nilesh.mohale
 *
 */
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { EmployeeService_Test.class })
public class EmployeeService_Test {

	@Mock
	EmployeeRepository employeerepository;

	@InjectMocks
	EmployeeService empService;

	@Test
	@Order(1)
	public void test_addEmployee() {
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Lokesh");
		emp.setDesignation("Developer");

		when(employeerepository.save(emp)).thenReturn(emp);
		assertThat(empService.addEmployee(emp)).isEqualTo(emp);
	}

	@Test
	@Order(2)
	public void test_getAllEmployee() {
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Lokesh");
		emp.setDesignation("Developer");

		Employee emp1 = new Employee();
		emp1.setEmpId(2);
		emp1.setEmpName("Shubham");
		emp1.setDesignation("Tester");

		employeeList.add(emp);
		employeeList.add(emp1);

		when(employeerepository.findAll()).thenReturn(employeeList);
		assertThat(empService.getAllEmployee()).isEqualTo(employeeList);
	}

	@Test
	@Order(3)
	public void test_updateEmployee() {
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Lokesh");
		emp.setDesignation("Developer");

		when(employeerepository.save(emp)).thenReturn(emp);
		assertThat(empService.updateEmployee(emp)).isEqualTo(emp);
	}

	@Test
	@Order(4)
	public void test_deleteEmployee() {
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Lokesh");
		emp.setDesignation("Developer");

		int empid = 1;

		empService.deleteEmployee(empid);
		verify(employeerepository, times(1)).deleteById(empid);
	}
}
