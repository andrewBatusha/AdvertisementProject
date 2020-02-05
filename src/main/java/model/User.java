package model;

import enums.Role;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int idUser;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    List<Advertisement> advertisementLinkedList = new LinkedList<>();
}
