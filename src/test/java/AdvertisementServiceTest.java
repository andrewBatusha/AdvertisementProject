import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBTestConnector;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import org.junit.Assert;
import org.junit.Test;
import service.AdvertisementService;

import java.time.LocalDateTime;

public class AdvertisementServiceTest {
    Advertisement adv = new Advertisement(1, "lol", "kek", "3030", "lol", Theme.HOBBY, false, 56, LocalDateTime.now());
    AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBTestConnector()));
    @Test
    public void addAdvertisement() throws  Exception{
        Assert.assertEquals(advertisementService.addAdvertisement(adv), true);
    }

    @Test
    public void deleteAdvertisement() throws Exception{
        Assert.assertEquals(advertisementService.deleteAdvertisement(22),true);
    }

    @Test
    public void changeAdvertisementStatus() throws Exception{
        Assert.assertEquals(advertisementService.changeAdvertisementStatus(adv, Status.APPROVED),true);
    }

}