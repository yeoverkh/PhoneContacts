package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.model.Contact;
import com.yehor.phonecontacts.api.model.Email;
import com.yehor.phonecontacts.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    public void saveEmailsInContact(Contact contact) {
        Map<Email, Email> replacingEmails = new HashMap<>();
        for (Email email : contact.getEmails()) {
            Optional<Email> foundEmail = emailRepository.findByAddress(email.getAddress());
            if (foundEmail.isPresent()) {
                replacingEmails.put(email, foundEmail.get());
                continue;
            }
            emailRepository.save(email);
        }
        replaceEmails(contact, replacingEmails);
    }

    private void replaceEmails(Contact contact, Map<Email, Email> replacingEmails) {
        Set<Email> emails = contact.getEmails();
        for (Map.Entry<Email, Email> entry : replacingEmails.entrySet()) {
            emails.remove(entry.getKey());
            emails.add(entry.getValue());
        }
    }
}
