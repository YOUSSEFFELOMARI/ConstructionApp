package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.ConstructionSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite,Integer> {

    ConstructionSite getConstructionSiteByName(String name);

//    @Query(value = "SELECT name FROM construction_site WHERE employee_id = :id", nativeQuery = true)
//    ConstructionSite getCsiteByName(@Param("id") int id);
}
