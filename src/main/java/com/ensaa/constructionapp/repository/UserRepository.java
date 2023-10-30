package com.ensaa.constructionapp.repository;

import com.ensaa.constructionapp.model.Admin.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    public Optional<AppUser> findAppUserByEmail(String email);

}
