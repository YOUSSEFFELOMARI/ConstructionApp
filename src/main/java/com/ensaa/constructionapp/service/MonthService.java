package com.ensaa.constructionapp.service;

import com.ensaa.constructionapp.dto.MonthDto;
import com.ensaa.constructionapp.mapper.MonthMapper;
import com.ensaa.constructionapp.model.Month;
import com.ensaa.constructionapp.repository.EmployeeRepository;
import com.ensaa.constructionapp.repository.MonthRepository;
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
    private final MonthMapper monthMapper;
    private final EmployeeRepository employeeRepository;

    private final String MNTNOTFOUND="Month not found - ID : ";
    private final String MNTEXIST="Month already exist - ID : ";

    public Page<MonthDto> showAllMonths(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return monthRepository.findAll(pageable).map(monthMapper::toDto);
    }

    public List<MonthDto> getAllMonthsDto(int id) {
        List<Month> months=monthRepository.getMonthByEmployeeEmployeeId(id);
        return months.stream().map(monthMapper::toDto).toList();
    }

    public List<Month> getAllMonthsByEmployeeId(int id){
        List<Month> months=monthRepository.getMonthByEmployeeEmployeeId(id);
        return months;
    }

    public Month create(MonthDto monthdto) throws ParseException {
        Month month=monthMapper.toEntity(monthdto);
        if (monthRepository.existsById(month.getMonthId()))
            throw new EntityExistsException("Month already stored in database - ID : "+month.getMonthId()) {};
        if (employeeRepository.getEmployeeByNameAndLastName(monthdto.getEmployeeName(),monthdto.getEmployeeLastName()) == null){
            throw new EntityNotFoundException("Employee Not Found"+monthdto.getEmployeeName() +" - LastName : "+
                    monthdto.getEmployeeLastName()) {};
        }
        createMonth(month);
        return month;
    }

    public void createMonth(Month month) throws ParseException {
        if (monthRepository.existsById(month.getMonthId()))
            throw new EntityExistsException(MNTEXIST+month.getMonthId()) {};
        monthRepository.save(month);
    }

    public void delete(int id) {
        if (!monthRepository.existsById(id))
            throw new EntityNotFoundException(MNTNOTFOUND+id) {};
        monthRepository.deleteById(id);
    }

    public void update(MonthDto monthdto) throws ParseException{
    Month month=monthMapper.toEntity(monthdto);
        if (!monthRepository.existsById(month.getMonthId()))
            throw new EntityNotFoundException(MNTNOTFOUND+month.getMonthId()) {};
        monthRepository.save(month);
    }

    public MonthDto find(int id) {
        Month month=monthRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MNTNOTFOUND+id) {});
        return monthMapper.toDto(month);
    }

    public long count() {
        return monthRepository.count();
    }

    public Set<MonthDto> getAllMonthsDtoForPage(int employerId) {
        List<Month> months=monthRepository.getMonthByEmployeeEmployeeId(employerId);
        return months.stream().map(monthMapper::toDto).collect(Collectors.toSet());
    }
}
