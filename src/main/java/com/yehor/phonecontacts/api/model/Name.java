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
public class Name {

    @Id
    @GeneratedValue
    private Long id;
    private String value;

    public Name(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
