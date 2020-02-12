package service;

import enums.Status;
import enums.Theme;
import model.Advertisement;
import org.junit.Assert;
import org.junit.Test;

public class AdvertisementServiceTest {

    @Test
    public void addAdvertisement() throws  Exception{
        Advertisement adv = new Advertisement(5,"testhead","testdesc", "testMail","testNumber", Theme.ART, Status.WAITING,false,5);
        AdvertisementService.addAdvertisement(adv);
    }

    @Test
    public void deleteAdvertisement() throws Exception{
        AdvertisementService.deleteAdvertisement(22);
    }

    @Test
    public void changeAdvertisementStatus() throws Exception{
        Advertisement adv = new Advertisement(5,"testhead","testdesc", "testMail","testNumber", Theme.ART, Status.WAITING,false,5);
        //Assert.assertEquals(adv.getStatus(), Status.WAITING);
        AdvertisementService.changeAdvertisementStatus(adv, Status.APPROVED);
        Assert.assertEquals(AdvertisementService.getUser(5).getStatus(), Status.APPROVED);



    }

}