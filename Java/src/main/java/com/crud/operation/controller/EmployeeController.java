package com.crud.operation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.operation.exceptions.ResourceNotFoundException;
import com.crud.operation.model.Employee;
import com.crud.operation.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Get all employees
	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	// Get Employee by Id
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee "
						+ "doesn't exist with id" + id));

		return ResponseEntity.ok(employee);
	}

	// Post employee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {

		return employeeRepository.save(employee);
	}

	// Update Existing employee
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employeeObj) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesn't "
						+ "exist with id" + id));

		employee.setFirstName(employeeObj.getFirstName());
		employee.setLastName(employeeObj.getLastName());
		employee.setEmail(employeeObj.getEmail());
		employee.setPhoneNumber(employeeObj.getPhoneNumber());

		Employee updatedEmployee = employeeRepository.save(employee);

		return ResponseEntity.ok(updatedEmployee);
	}
	
	// Delete Employee
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesn't "
						+ "exist with id" + id));

		employeeRepository.delete(employee);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}
