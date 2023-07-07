package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.model.Contact;
import com.yehor.phonecontacts.api.model.User;
import com.yehor.phonecontacts.repository.ContactRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final EmailService emailService;
    private final PhoneService phoneService;
    private final ContactRepository contactRepository;
    private final UserService userService;
    private final NameService nameService;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public void save(Contact contact) {
        User foundUser = userService.getCurrentUser();

        Long nameId = nameService.findNameIdByValue(contact.getName().getValue());

        throwExceptionIfContactAlreadyExists(foundUser.getId(), nameId);

        contact.setUser(foundUser);
        emailService.saveEmailsInContact(contact);
        phoneService.savePhonesInContact(contact);
        nameService.saveName(contact);
        contactRepository.save(contact);
    }

    private void throwExceptionIfContactAlreadyExists(Long userId, Long nameId) {
        if (isContactExists(userId, nameId)) {
            throw new EntityExistsException("This contact is already exists");
        }
    }

    private boolean isContactExists(Long userId, Long nameId) {
        Optional<Contact> foundContact = contactRepository.findByNameIdAndUserId(nameId, userId);
        return foundContact.isPresent();
    }

    public void edit(Contact contact) {
        Optional<Contact> foundContact = findContact(contact.getName().getValue());

        if (foundContact.isEmpty()) {
            throw new NoSuchElementException("Contact with this parameters not exists");
        }

        delete(contact.getName().getValue());
        save(contact);

    }

    private Optional<Contact> findContact(String name) {
        User foundUser = userService.getCurrentUser();

        Long nameId = nameService.findNameIdByValue(name);

        return contactRepository.findByNameIdAndUserId(nameId, foundUser.getId());
    }

    @Transactional
    public void delete(String name) {
        Optional<Contact> foundContact = findContact(name);

        if (foundContact.isEmpty()) {
            throw new NoSuchElementException("There is no contact with this name");
        }

        Contact contact = foundContact.get();

        removeDependencies(contact);
        contactRepository.save(contact);
        contactRepository.delete(contact);
    }

    private void removeDependencies(Contact contact) {
        contact.setPhones(null);
        contact.setEmails(null);
    }

    // idea: decompose 'findUserContactByNameId'
}
