package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    Employee getEmployeeByNameAndLastName(String name, String lastname);

}
