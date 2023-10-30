package com.ensaa.constructionapp.service;

import com.ensaa.constructionapp.model.ConstructionSiteName;
import com.ensaa.constructionapp.repository.ConstructionSiteNameRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConstructionSiteNameService {
    private final ConstructionSiteNameRepository constructionSiteNameRepository;

    private final String CSNAMENOTFOUND="ConstructionSiteName not found - ID : ";

    public Page<ConstructionSiteName> showAllConstructionSiteNames(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return constructionSiteNameRepository.findAll(pageable);
    }

    public ConstructionSiteName getConstructionSiteName(int id) {
        return constructionSiteNameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(CSNAMENOTFOUND+id) {});
    }

    public List<ConstructionSiteName> getAllConstruction() {
        return constructionSiteNameRepository.findAll();
    }

    public ConstructionSiteName create(ConstructionSiteName ConstructionSiteName) {
        if (constructionSiteNameRepository.existsById(ConstructionSiteName.getConstructionSiteNameId()))
            throw new EntityExistsException("ConstructionSiteName already stored in database - ID : "+ConstructionSiteName.getConstructionSiteNameId()) {};
        constructionSiteNameRepository.save(ConstructionSiteName);
        return ConstructionSiteName;
    }



    public void delete(int id) {

        if (!constructionSiteNameRepository.existsById(id))
            throw new EntityNotFoundException(CSNAMENOTFOUND+id) {};
        constructionSiteNameRepository.deleteById(id);
    }

    public void update(ConstructionSiteName constructionSiteName){
        if (!constructionSiteNameRepository.existsById(constructionSiteName.getConstructionSiteNameId()))
            throw new EntityNotFoundException(CSNAMENOTFOUND+constructionSiteName.getConstructionSiteNameId()) {};
        constructionSiteNameRepository.save(constructionSiteName);
    }

    public ConstructionSiteName find(int id) {
        return constructionSiteNameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(CSNAMENOTFOUND+id) {});
    }

    public long count() {
        return constructionSiteNameRepository.count();
    }

}
