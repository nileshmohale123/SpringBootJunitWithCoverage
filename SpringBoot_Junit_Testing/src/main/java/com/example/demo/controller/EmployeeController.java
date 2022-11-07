package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
/**
 * @author nilesh.mohale
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeservice;
	
	@PostMapping("/save")
	public Employee addEmployee(@RequestBody Employee emp)
	{
		Employee empl=employeeservice.addEmployee(emp);
		
		return empl;
	}
	
	@GetMapping("/get")
	public List<Employee> getAllEmployee()
	{
		List<Employee>elist=employeeservice.getAllEmployee();
		return elist;
	}
	
	@PutMapping("update")
	public Employee updateEmployee(@RequestBody Employee emp)
	{
		Employee empl=employeeservice.updateEmployee(emp);
		return empl;
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		employeeservice.deleteEmployee(id);
		return "delete data";
	}
}


