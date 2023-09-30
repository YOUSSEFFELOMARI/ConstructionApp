package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.dto.ConstructionSiteDto;
import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.ConstructionSiteRepository;
import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConstructionSiteService {
    private final ConstructionSiteRepository constructionSiteRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    private ConstructionSiteDto constructionSiteDto;
    public Page<ConstructionSiteDto> showAllConstructionSites(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return constructionSiteRepository.findAll(pageable).map(constructionSiteDto::toDto);
    }

    public ConstructionSiteDto getCSitesByEmployee(int id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
        return constructionSiteDto.toDto(employee.getConstructionSite());
    }


    public void create(ConstructionSiteDto constructionSitedto) throws ParseException {
        ConstructionSite constructionSite= constructionSiteDto.toEntity(constructionSitedto);
        if (constructionSiteRepository.existsById(constructionSite.getConstructionSiteId()))
            throw new EntityExistsException("ConstructionSite already stored in database - ID : "+constructionSite.getConstructionSiteId()) {};
        constructionSiteRepository.save(constructionSite);
    }

    public void delete(int id) {

        if (!constructionSiteRepository.existsById(id))
            throw new EntityNotFoundException("ConstructionSite not found - ID : "+id) {};
        constructionSiteRepository.deleteById(id);
    }

    public void update(ConstructionSiteDto constructionSitedto) throws ParseException {
        ConstructionSite constructionSite= constructionSiteDto.toEntity(constructionSitedto);
        if (!constructionSiteRepository.existsById(constructionSite.getConstructionSiteId()))
            throw new EntityNotFoundException("ConstructionSite not found - ID : "+constructionSite.getConstructionSiteId()) {};
        constructionSiteRepository.save(constructionSite);
    }

    public ConstructionSite find(int id) {
        return constructionSiteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("ConstructionSite not found - ID : "+id) {});
    }

    public long count() {
        return constructionSiteRepository.count();
    }


}
