package com.ensaa.constructionapp.service;

import com.ensaa.constructionapp.model.Contact;
import com.ensaa.constructionapp.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final String CONTACTALREADYEXIST="Message Already exist - ID : ";
    private final String CONTACNOTFOUND="Message Already exist - ID : ";

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public void create(Contact contact){

        if(contactRepository.existsById(contact.getContactId()))
            throw new EntityNotFoundException(CONTACTALREADYEXIST+contact.getContactId());
        contactRepository.save(contact);
    }

    public void update(Contact contact){
        if(!contactRepository.existsById(contact.getContactId()))
            throw new EntityNotFoundException(CONTACNOTFOUND+contact.getContactId());
        contactRepository.save(contact);
    }

    public void delete(int id){
        if(!contactRepository.existsById(id))
            throw new EntityNotFoundException(CONTACNOTFOUND+id);
        contactRepository.deleteById(id);
    }

    public Optional<Contact> find(int id) {
        if(!contactRepository.existsById(id))
            throw new EntityNotFoundException(CONTACNOTFOUND+id);
        return contactRepository.findById(id);
    }
}
