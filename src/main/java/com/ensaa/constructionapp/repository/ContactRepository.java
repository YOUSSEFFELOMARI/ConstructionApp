package com.ensaa.constructionapp.repository;

import com.ensaa.constructionapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}