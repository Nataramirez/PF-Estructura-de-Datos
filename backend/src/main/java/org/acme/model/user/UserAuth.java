package org.acme.model.user;

import lombok.Data;

@Data
public class UserAuth {
    private String user;
    private String password;
}
