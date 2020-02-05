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
    private Theme theme;
    private String number;
    private Status status;
    private boolean visibility;
}
