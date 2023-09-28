package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.model.Employer;
import com.EnsaA.ConstructionApp.repository.EmployerRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Page<Employer> showAllEmplyers(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return employerRepository.findAll(pageable);
    }

    public void create(Employer account) {
        if (employerRepository.existsById(account.getEmployerId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+account.getEmployerId()) {};
        employerRepository.save(account);
    }

    public void delete(int id) {
        if (!employerRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employerRepository.deleteById(id);
    }

    public void update(Employer account) {
        if (!employerRepository.existsById(account.getEmployerId()))
            throw new EntityNotFoundException("Employer not found - ID : "+account.getEmployerId()) {};
        employerRepository.save(account);
    }

    public Employer find(int id) {
        return employerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
    }

    public long count() {
        return employerRepository.count();
    }
}
