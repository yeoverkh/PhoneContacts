package com.yehor.phonecontacts.api.controller;

import com.yehor.phonecontacts.api.dto.DeleteRequest;
import com.yehor.phonecontacts.api.model.Contact;
import com.yehor.phonecontacts.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.findAll();
    }

    @PostMapping
    public void addContact(@Valid @RequestBody Contact contact) {
        contactService.save(contact);
    }

    @PutMapping
    public void editContact(@Valid @RequestBody Contact contact) {
        contactService.edit(contact);
    }

    @DeleteMapping
    public void deleteContact(@RequestBody DeleteRequest name) {
        contactService.delete(name.getName());
    }
}
