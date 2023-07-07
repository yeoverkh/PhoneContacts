package com.yehor.phonecontacts.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "name_id")
    private Name name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contact_emails",
            joinColumns = {@JoinColumn(name = "contact_id")},
            inverseJoinColumns = {@JoinColumn(name = "email_id")}
    )
    private Set<Email> emails = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contact_phones",
            joinColumns = {@JoinColumn(name = "contact_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id")}
    )
    private Set<Phone> phones = new HashSet<>();

    @JsonValue
    public Map<String, Object> toJson() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name.getValue());
        jsonMap.put("emails", emails.stream().map(Email::getAddress).collect(Collectors.toList()));
        jsonMap.put("phones", phones.stream().map(Phone::getNumber).collect(Collectors.toList()));
        return jsonMap;
    }
}
