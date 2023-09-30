package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.MonthRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MonthService {
    private final MonthRepository monthRepository;
    private final MonthDto monthDto;

    public Page<MonthDto> showAllMonths(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return monthRepository.findAll(pageable).map(monthDto::toDto);
    }

    public List<MonthDto> getAllMonths(int id) {
        List<Month> months=monthRepository.getMonthByEmployeeEmployeeId(id);
        return months.stream().map(monthDto::toDto).collect(Collectors.toList());
    }

    public MonthDto create(MonthDto monthdto) throws ParseException {
        Month month=monthDto.toEntity(monthdto);
        if (monthRepository.existsById(month.getMonthId()))
            throw new EntityExistsException("Month already stored in database - ID : "+month.getMonthId()) {};
        createMonth(month);
        return monthdto;
    }

    public void createMonth(Month month) throws ParseException {
        if (monthRepository.existsById(month.getMonthId()))
            throw new EntityExistsException("Month already stored in database - ID : "+month.getMonthId()) {};
        monthRepository.save(month);
    }

    public void delete(int id) {
        if (!monthRepository.existsById(id))
            throw new EntityNotFoundException("Month not found - ID : "+id) {};
        monthRepository.deleteById(id);
    }

    public void update(MonthDto monthdto) throws ParseException{
    Month month=monthDto.toEntity(monthdto);
        if (!monthRepository.existsById(month.getMonthId()))
            throw new EntityNotFoundException("Month not found - ID : "+month.getMonthId()) {};
        monthRepository.save(month);
    }

    public MonthDto find(int id) {
        Month month=monthRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Month not found - ID : "+id) {});
        return monthDto.toDto(month);
    }

    public long count() {
        return monthRepository.count();
    }
}
