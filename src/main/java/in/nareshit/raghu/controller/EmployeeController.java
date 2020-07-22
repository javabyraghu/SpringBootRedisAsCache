package in.nareshit.raghu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.service.IEmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	@PostMapping("/save")
	public Employee createEmployee(
			@RequestBody Employee employee) 
	{
		return service.createEmployee(employee);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	@GetMapping("/one/{empId}")
	public Employee getOneEmployee(
			@PathVariable(value = "empId") Integer empId)
	{
		
		return service.getOneEmployee(empId);

	}

	@PutMapping("/modify/{empId}")
	public Employee updateEmployee(
			@PathVariable(value = "empId") Integer empId,
			@RequestBody Employee employee) {

		Employee updatedEmployee = service.updateEmployee(empId,employee);
		return updatedEmployee;

	}

	@DeleteMapping("/remove/{empId}")
	public void deleteEmployee(
			@PathVariable(value = "empId") Integer empId) 
	{
		service.deleteEmployee(empId);
	}
}
