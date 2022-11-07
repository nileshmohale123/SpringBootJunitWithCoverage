package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
@WebMvcTest
public class EmployeeControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeRepository employeeRepository;

	@MockBean
	EmployeeService employeeService;
	

    private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void addEmployeeTest() throws Exception {
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Nilesh");
		emp.setDesignation("Software Engineer");
		Mockito.when(employeeService.addEmployee(ArgumentMatchers.any())).thenReturn(emp);
		//ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(emp);
		mockMvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.empId", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.empName", Matchers.equalTo("Nilesh")))
				.andExpect(jsonPath("$.designation", Matchers.equalTo("Software Engineer")));
	}

	@Test
	public void getAllEmployeeTest() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Nilesh"); 
		emp.setDesignation("Software Engineer");
		employees.add(emp);
		Mockito.when(employeeService.getAllEmployee()).thenReturn(employees);
		mockMvc.perform(get("/get")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].empName", Matchers.equalTo("Nilesh")));
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Nilesh");
		emp.setDesignation("Software Engineer");
		emp.setDesignation("Software Engineer");

		//ObjectMapper mapper = new ObjectMapper();
		Mockito.when(employeeService.updateEmployee(ArgumentMatchers.any())).thenReturn(emp);
		String json = mapper.writeValueAsString(emp);
		mockMvc.perform(put("/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.empId", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.empName", Matchers.equalTo("Nilesh")));
	}
	

    @Test
    public void deleteEmployeeTest() throws Exception {      
        //Mockito.when(employeeService.deleteEmployee("1").thenReturn("SUCCESS");
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 1))
                .andExpect(status().isOk());
    }

	/*
	 * @Test public void deleteEmployeeTest() throws Exception {
	 * //Mockito.when(employeeService.deleteEmployee(ArgumentMatchers.anyString())).
	 * thenReturn("Employee is deleted"); MvcResult requestResult =
	 * mockMvc.perform(delete("/delete/{id}").param("id", "1"))
	 * .andExpect(status().isOk()).andExpect(status().isOk()).andReturn(); String
	 * result = requestResult.getResponse().getContentAsString();
	 * assertEquals(result, "Employee is deleted"); }
	 */

	/*
	 * @Test // @WithMockUser(roles = { "Admin", "User" }) public void
	 * getAllEmployeeTest() throws Exception {
	 * 
	 * mockMvc.perform(get("/get"))
	 * 
	 * .andExpect(status().isOk());
	 * 
	 * // .andExpect(content().json("[{}, {}]"));
	 * 
	 * }
	 */

	/*
	 * @Test // @WithMockUser(roles = "Admin") public void addEmployeeTest() throws
	 * Exception { Employee emp = new Employee(); emp.setEmpId(1);
	 * emp.setEmpName("Nilesh"); emp.setDesignation("Software Engineer");
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); this.mockMvc
	 * .perform(post("/save").contentType(MediaType.APPLICATION_JSON).accept(
	 * MediaType.APPLICATION_JSON) .content(mapper.writeValueAsString(emp)))
	 * 
	 * .andExpect(status().isOk()); }
	 */

}