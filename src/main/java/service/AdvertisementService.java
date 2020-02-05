package service;

import model.Advertisement;
import model.User;

public class AdvertisementService {
    void addAdvertisement(User user, Advertisement advertisement){
        user.getAdvertisementLinkedList().add(advertisement);
    }

    void deleteAdvertisement(User user, Advertisement advertisement){
        user.getAdvertisementLinkedList().remove(advertisement);
    }

    void sendForVerification(){

    }

}
