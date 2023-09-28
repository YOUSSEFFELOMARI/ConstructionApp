package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRepository extends JpaRepository<Month,Integer> {
}
