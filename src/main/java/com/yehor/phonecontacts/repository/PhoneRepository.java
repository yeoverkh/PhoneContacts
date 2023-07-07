package com.yehor.phonecontacts.repository;

import com.yehor.phonecontacts.api.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByNumber(String number);
}
