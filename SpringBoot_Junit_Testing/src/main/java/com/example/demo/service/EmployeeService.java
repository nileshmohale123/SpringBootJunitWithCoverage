package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
/**
 * @author nilesh.mohale
 *
 */
@Service
public class EmployeeService {
	
	@Autowired 
	EmployeeRepository employeerepository;

	public Employee addEmployee(Employee emp) {
		
		return employeerepository.save(emp);
	}

	public List<Employee> getAllEmployee() {

		List<Employee> elist = employeerepository.findAll();
		
		return elist;
	}

	public Employee updateEmployee(Employee emp) {

		return employeerepository.save(emp);
	}

	public void deleteEmployee(int id) {

		employeerepository.deleteById(id);
	}
}
