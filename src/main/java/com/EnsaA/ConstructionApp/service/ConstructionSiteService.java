package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.repository.ConstructionSiteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConstructionSiteService {
    private final ConstructionSiteRepository constructionSiteRepository;

    public Page<ConstructionSite> showAllConstructionSites(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return constructionSiteRepository.findAll(pageable);
    }

    public void create(ConstructionSite constructionSite) {
        if (constructionSiteRepository.existsById(constructionSite.getConstructionSiteId()))
            throw new EntityExistsException("ConstructionSite already stored in database - ID : "+constructionSite.getConstructionSiteId()) {};
        constructionSiteRepository.save(constructionSite);
    }

    public void delete(int id) {
        if (!constructionSiteRepository.existsById(id))
            throw new EntityNotFoundException("ConstructionSite not found - ID : "+id) {};
        constructionSiteRepository.deleteById(id);
    }

    public void update(ConstructionSite constructionSite) {
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
