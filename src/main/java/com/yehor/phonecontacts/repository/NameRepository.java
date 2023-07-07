package com.yehor.phonecontacts.repository;

import com.yehor.phonecontacts.api.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    Optional<Name> findByValue(String value);

    void deleteByValue(String value);
}
