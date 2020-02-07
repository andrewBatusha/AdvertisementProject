package model;

import enums.Role;


import java.util.List;


public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private String login;
    List<Advertisement> advertisements;

    public User() {
    }

    ;

    public User(int id, String name, String surname, String email, String password, Role role, String login, List<Advertisement> advertisements) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.login = login;
        this.advertisements = advertisements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) throws NullPointerException {
        if (advertisements == null) {
            this.advertisements = advertisements;
        }else {
            throw new NullPointerException();
        }
    }
}
