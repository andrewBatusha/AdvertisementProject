package model;

import enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private String login;
    List<Advertisement> advertisementLinkedList = new ArrayList<>();

    public User(int idUser, String name, String surname, String email, String password, Role role, String login) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.login = login;
    }
}
