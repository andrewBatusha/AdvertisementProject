package service;

import enums.Role;
import model.User;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {
    User user = new User(32,"andrew","batuiev","@gmail.com","password", Role.ADMIN,null);
    @Test
    public void addUser()  throws Exception {
        Assert.assertEquals(UserService.addUser(user), true);
    }

    @Test
    public void deleteUser() throws Exception {
        Assert.assertEquals(UserService.deleteUser(32),true);
    }


    @Test
    public void changeUserRole() throws Exception {
        UserService.addUser(user);
        UserService.changeUserRole(user, Role.USER);
        Assert.assertEquals(UserService.changeUserRole(user, Role.USER), true);
    }
}