package model;


import enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Advertisement {
    private int idAdvertisement;
    private String headline;
    private String description;
    private String mail;
    private String number;
    private Status status;
    private boolean visibility;
}
