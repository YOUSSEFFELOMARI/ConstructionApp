package com.ensaa.constructionapp.repository;

import com.ensaa.constructionapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    Employee getEmployeeByNameAndLastName(String name, String lastname);

}
