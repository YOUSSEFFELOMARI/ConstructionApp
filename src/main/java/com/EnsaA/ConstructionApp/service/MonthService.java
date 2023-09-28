package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.MonthRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MonthService {
    private final MonthRepository monthRepository;

    public Page<Month> showAllMonths(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return monthRepository.findAll(pageable);
    }

    public void create(Month account) {
        if (monthRepository.existsById(account.getMonthId()))
            throw new EntityExistsException("Month already stored in database - ID : "+account.getMonthId()) {};
        monthRepository.save(account);
    }

    public void delete(int id) {
        if (!monthRepository.existsById(id))
            throw new EntityNotFoundException("Month not found - ID : "+id) {};
        monthRepository.deleteById(id);
    }

    public void update(Month account) {
        if (!monthRepository.existsById(account.getMonthId()))
            throw new EntityNotFoundException("Month not found - ID : "+account.getMonthId()) {};
        monthRepository.save(account);
    }

    public Month find(int id) {
        return monthRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Month not found - ID : "+id) {});
    }

    public long count() {
        return monthRepository.count();
    }
}
