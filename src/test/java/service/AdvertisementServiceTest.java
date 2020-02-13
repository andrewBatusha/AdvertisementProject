package service;

import enums.Status;
import enums.Theme;
import model.Advertisement;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class AdvertisementServiceTest {
    Advertisement adv = new Advertisement(1, "lol", "kek", "3030", "lol", Theme.HOBBY, Status.WAITING, false, 3, LocalDateTime.now());
    @Test
    public void addAdvertisement() throws  Exception{
        Assert.assertEquals(AdvertisementService.addAdvertisement(adv), true);
    }

    @Test
    public void deleteAdvertisement() throws Exception{
        Assert.assertEquals(AdvertisementService.deleteAdvertisement(22),true);
    }

    @Test
    public void changeAdvertisementStatus() throws Exception{
        Assert.assertEquals(AdvertisementService.changeAdvertisementStatus(adv, Status.APPROVED),true);
    }

}