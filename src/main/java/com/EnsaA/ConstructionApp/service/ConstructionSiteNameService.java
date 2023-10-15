package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.model.ConstructionSiteName;
import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.ConstructionSiteName;
import com.EnsaA.ConstructionApp.repository.ConstructionSiteNameRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConstructionSiteNameService {
    private final ConstructionSiteNameRepository constructionSiteNameRepository;

    public Page<ConstructionSiteName> showAllConstructionSiteNames(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return constructionSiteNameRepository.findAll(pageable);
    }

    public ConstructionSiteName getConstructionSiteName(int id) {
        return constructionSiteNameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("ConstructionSiteName not found - ID : "+id) {});
    }

    public List<ConstructionSiteName> getAllConstruction() {
        return constructionSiteNameRepository.findAll();
    }

    public ConstructionSiteName create(ConstructionSiteName ConstructionSiteName) {
        if (constructionSiteNameRepository.existsById(ConstructionSiteName.getConstructionSiteNameId()))
            throw new EntityExistsException("ConstructionSite already stored in database - ID : "+ConstructionSiteName.getConstructionSiteNameId()) {};
        constructionSiteNameRepository.save(ConstructionSiteName);
        return ConstructionSiteName;
    }



    public void delete(int id) {

        if (!constructionSiteNameRepository.existsById(id))
            throw new EntityNotFoundException("ConstructionSite not found - ID : "+id) {};
        constructionSiteNameRepository.deleteById(id);
    }

    public void update(ConstructionSiteName ConstructionSiteName){
        if (!constructionSiteNameRepository.existsById(ConstructionSiteName.getConstructionSiteNameId()))
            throw new EntityNotFoundException("ConstructionSite not found - ID : "+ConstructionSiteName.getConstructionSiteNameId()) {};
        constructionSiteNameRepository.save(ConstructionSiteName);
    }

    public ConstructionSiteName find(int id) {
        return constructionSiteNameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("ConstructionSite not found - ID : "+id) {});
    }

    public long count() {
        return constructionSiteNameRepository.count();
    }

}
