package com.yehor.phonecontacts.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    @jakarta.validation.constraints.Email(message = "Invalid email format")
    private String address;

    public Email(String address) {
        this.address = address;
    }

    @JsonValue
    public String getAddress() {
        return address;
    }
}
