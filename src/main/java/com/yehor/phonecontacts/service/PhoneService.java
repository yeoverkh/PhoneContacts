package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.model.Contact;
import com.yehor.phonecontacts.api.model.Phone;
import com.yehor.phonecontacts.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public void savePhonesInContact(Contact contact) {
        Map<Phone, Phone> replacingPhones = new HashMap<>();
        for (Phone phone : contact.getPhones()) {
            Optional<Phone> foundPhone = phoneRepository.findByNumber(phone.getNumber());
            if (foundPhone.isPresent()) {
                replacingPhones.put(phone, foundPhone.get());
                continue;
            }
            phoneRepository.save(phone);
        }
        replacePhones(contact, replacingPhones);
    }

    private void replacePhones(Contact contact, Map<Phone, Phone> replacingPhones) {
        Set<Phone> phones = contact.getPhones();
        for (Map.Entry<Phone, Phone> entry : replacingPhones.entrySet()) {
            phones.remove(entry.getKey());
            phones.add(entry.getValue());
        }
    }
}
