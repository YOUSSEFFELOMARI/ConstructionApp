package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin getAdminByUserName(String string);
}
