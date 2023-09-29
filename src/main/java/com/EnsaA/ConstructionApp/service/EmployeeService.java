package com.EnsaA.ConstructionApp.service;

//import com.EnsaA.ConstructionApp.dto.EmployeeDto;
//import com.EnsaA.ConstructionApp.mapper.EmployeeMapper;
import com.EnsaA.ConstructionApp.dto.EmployeeDto;
import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeDto employeeDto;

    public Page<Employee> showAllEmplyers(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }

    public void create(Employee account) {
        if (employeeRepository.existsById(account.getEmployeeId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+account.getEmployeeId()) {};
        employeeRepository.save(account);
    }

    public void delete(int id) {
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employeeRepository.deleteById(id);
    }

    public void update(Employee account) {
        if (!employeeRepository.existsById(account.getEmployeeId()))
            throw new EntityNotFoundException("Employer not found - ID : "+account.getEmployeeId()) {};
        employeeRepository.save(account);
    }

    public Employee find(int id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
    }

    public long count() {
        return employeeRepository.count();
    }
}
