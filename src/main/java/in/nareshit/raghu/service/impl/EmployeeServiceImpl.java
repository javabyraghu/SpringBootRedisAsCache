package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.ResouceNotFoundException;
import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.repository.EmployeeRepository;
import in.nareshit.raghu.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee createEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Transactional
	@CachePut(value = "employees",key = "#empId")
	public Employee updateEmployee(Integer empId, Employee employee) {
		Employee emp = repo.findById(empId)
				.orElseThrow(() -> 
				new ResouceNotFoundException("Employee Not Found " + empId));
		emp.setEmpName(employee.getEmpName());
		emp.setEmpSal(employee.getEmpSal());
		return emp;
	}

	@CacheEvict(value = "employees", allEntries = true)
	@Transactional
	public void deleteEmployee(Integer empId) {
		Employee employee = repo.findById(empId).orElseThrow(
				() -> new ResouceNotFoundException("Employee not found" + empId));
		repo.delete(employee);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "employees",key = "#empId")
	public Employee getOneEmployee(Integer empId) {
		return repo.findById(empId)
				.orElseThrow(() -> new ResouceNotFoundException("Employee not found" + empId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

}
