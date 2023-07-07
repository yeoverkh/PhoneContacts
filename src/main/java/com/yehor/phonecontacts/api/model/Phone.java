package com.yehor.phonecontacts.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp="^\\+\\d{1,3}\\d{9}$", message="Invalid phone number format")
    private String number;

    public Phone(String number) {
        this.number = number;
    }

    @JsonValue
    public String getNumber() {
        return number;
    }
}
