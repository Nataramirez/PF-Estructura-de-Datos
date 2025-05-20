package org.acme.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserState {

    _0_ENABLED("ENABLED"),
    _1_DISABLED("DISABLED");

    private String value;
}
