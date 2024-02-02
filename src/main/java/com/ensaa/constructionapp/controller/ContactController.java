package com.ensaa.constructionapp.controller;

import com.ensaa.constructionapp.model.Contact;
import com.ensaa.constructionapp.model.Response;
import com.ensaa.constructionapp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ensaa.constructionapp.controller.CustomResponse.response;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @GetMapping("/list")
    public ResponseEntity<List<Contact>> getAllContact(){
        return ResponseEntity.ok().body(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> getContact(@PathVariable(name = "id") int id){
        return ResponseEntity.ok().body(contactService.find(id));
    }

    @PostMapping
    public ResponseEntity<Response> saveContact(@RequestBody Contact contact){
        contactService.create(contact);
        return response("Message Sent Successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteContact(@PathVariable int id){
        contactService.delete(id);
        return response("Message deleted correctly", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateContact(@RequestBody Contact contact){
        contactService.update(contact);
        return response("Message successfully updated",HttpStatus.OK);
    }
}
