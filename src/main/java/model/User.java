package model;

import enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password = "";
    private Role role = Role.USER;
    private boolean banStatus = false;
    private String token;
    private boolean activated = false;
}
