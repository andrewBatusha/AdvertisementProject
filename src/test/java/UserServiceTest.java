//import DAO.impl.DBTestConnector;
//import DAO.impl.UserJdbcDao;
//import enums.Role;
//import model.User;
//import org.junit.Assert;
//import org.junit.Test;
//import UserService;
//
//public class UserServiceTest {
//    User user = new User(32, "andrew", "batuiev", "@gmail.com", "password", Role.ADMIN);
//
//    UserService userService = new UserService(new UserJdbcDao(new DBTestConnector()));
//
//    @Test
//    public void addUser() throws Exception {
//        Assert.assertEquals(userService.addUser(user), true);
//    }
//
//    @Test
//    public void deleteUser() throws Exception {
//        Assert.assertEquals(userService.deleteUser(32), true);
//    }
//
//
//    @Test
//    public void changeUserRole() throws Exception {
//        userService.addUser(user);
//        userService.changeUserRole(user, Role.USER);
//        Assert.assertEquals(userService.changeUserRole(user, Role.USER), true);
//    }
//}