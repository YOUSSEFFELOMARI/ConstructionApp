package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.ConstructionSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite,Integer> {

    ConstructionSite getConstructionSiteByName(String name);
}
