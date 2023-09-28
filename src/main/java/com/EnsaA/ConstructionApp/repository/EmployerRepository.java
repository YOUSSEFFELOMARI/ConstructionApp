package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Integer> {

    @Override
    Page<Employer> findAll(Pageable pageable);

}
