package model;


import enums.Status;
import enums.Theme;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    private int idAdvertisement;
    private String headline;
    private String description;
    private String mail;
    private String number;
    private Theme theme;
    private Status status;
    private boolean visibility;
    private int idUser;
}
