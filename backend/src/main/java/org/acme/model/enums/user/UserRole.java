package org.acme.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER");

    private String value;

}
