import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Role;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import model.User;
import service.AdvertisementService;
import service.UserService;

import java.time.LocalDateTime;

public class SetUp {
    private UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
    private AdvertisementService advService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));

    public SetUp() {
        userService.createTable();
        advService.createTable();
        generateUsers();
        generateAdv();
    }

    private void generateUsers() {
        userService.addUser(new User
                (1,
                        "userName",
                        "userSurname",
                        "user@gmail.com",
                        "user",
                        Role.USER,
                        false,
                        "b49844b1d5754ced8a034cc6af11a462",
                        true
                ));
        userService.addUser(new User
                (2,
                        "managerName",
                        "managerSurname",
                        "manager@gmail.com",
                        "manager",
                        Role.MANAGER,
                        false,
                        "4fd264b4709d4cbea58fa389aba3fdb8",
                        true
                ));
        userService.addUser(new User
                (3,
                        "adminName",
                        "adminSurname",
                        "admin@gmail.com",
                        "admin",
                        Role.ADMIN,
                        false,
                        "8a3719a144a944dd80af9118f2cff28c",
                        true
                ));
    }

    private void generateAdv() {
        advService.addAdvertisement(new Advertisement(
                1,
                "Велопробіжка",
                "Збираємося всі на пробіжку 09.03.2020 у 10:00 біля головного входу у парк Шевченка",
                "+380993396684",
                Theme.SPORT,
                Status.APPROVED,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                2,
                "Вечір читання віршів Шевченка",
                "Захід відбудеться 10.03.2020 по адресі Ольги Кобилянської 53-А о 18:00",
                "+380993396684",
                Theme.ART,
                Status.APPROVED,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                3,
                "Виставка рукотворних робіт",
                "Завітайте на виставку рукотворних робіт та майте змогу обмінятись досвідом з іншими майстрами цієї справи",
                "+380993396684",
                Theme.HOBBY,
                Status.APPROVED,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                4,
                "Шукаємо продавця на калинку",
                "Ви вмієте продавати і це у вас добре виходить, тоді вам до нас! Ми даруємо людям свято та посмішку, натомість отримуємо винагороду, котрою ми готові поділитись із вами." +
                        "Якщо зацікавлені, то приходьте на Калинівський ринок 3 сектор 4 ряд біля чебуречної за деталями",
                "+380993396684",
                Theme.WORK,
                Status.APPROVED,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                5,
                "Пиріжки",
                "Продаю пиріжки з маком, картоплею та капустою.",
                "+380993396684",
                Theme.ART,
                Status.DENIED,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                6,
                "Запрошуємо на роботу дресирувальником черепах",
                "Наш цирк переживає не кращі часи і задля збереження рентабельності і купівельної спроможності" +
                            "ми вирішили розширити нашу програму акробатичними черепахами." +
                            "За деталями телефонуйте по номеру в описі",
                "+380993396684",
                Theme.WORK,
                Status.WAITING,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                7,
                "Турнір з шахів",
                "Шахматний клуб 'Біла Тура' запрошує всіх бажаючих випробувати свої сили на міському чемпіонаті з шахів, " +
                        "що відбудеться 30.03.2020 о 13:00" +
                        "Плата за участь: 40 грн",
                "+380993396684",
                Theme.SPORT,
                Status.WAITING,
                1,
                LocalDateTime.now()
        ));
        advService.addAdvertisement(new Advertisement(
                8,
                "Марафон по Калічанці",
                "20.03.2020 о 11:00 відбудеться легендарний забіг по Калічанці для всіх бажаючих за головний приз - мікрохвильова піч.",
                "+380993396684",
                Theme.SPORT,
                Status.WAITING,
                1,
                LocalDateTime.now()
        ));
    }
}
