package com.yehor.phonecontacts.repository;

import com.yehor.phonecontacts.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByNameIdAndUserId(Long nameId, Long userId);
}
