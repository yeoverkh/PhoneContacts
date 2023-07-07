package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.model.Contact;
import com.yehor.phonecontacts.api.model.Name;
import com.yehor.phonecontacts.repository.NameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;

    public void saveName(Contact contact) {
        Name contactName = contact.getName();
        Optional<Name> foundName = nameRepository.findByValue(contactName.getValue());
        if (foundName.isPresent()) {
            contact.setName(foundName.get());
        } else {
            nameRepository.save(contactName);
        }
    }

    public Long findNameIdByValue(String value) {
        Optional<Name> foundName = nameRepository.findByValue(value);
        return foundName.map(Name::getId).orElse(null);
    }
}
