package in.nareshit.raghu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
