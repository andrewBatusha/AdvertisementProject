package model;

import enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public User(int id, String name, String surname, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
