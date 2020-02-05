package model;

import enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class User {
    private int idUser;
    private String mail;
    private String password;
    private String login;
    private Role role;
    List<Advertisement> advertisementLinkedList = new LinkedList<>();
}
