package service;

import enums.Role;
import model.User;

public class UserService {

    void changePassword(User user){
    }

    void changeRole(User user, Role role){
        user.setRole(role);
    }

    void authorization(User user, String login, String password){
        if(login.equals(user.getLogin()) & password.equals(user.getPassword())){
            System.out.println("authorization has completed succesfully");
        }else{
            System.out.println("Login or password invalid");
        }
    }


}
