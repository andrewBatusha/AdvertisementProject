package model;


import enums.Status;
import enums.Theme;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class Advertisement {
    private int idAdvertisement;
    private String headline;
    private String description;
    private String number;
    private Theme theme;
    private Status status = Status.WAITING;
    private int idUser;
    private LocalDateTime dateOfPublished;

    public String getDateForView(){
        return dateOfPublished.getDayOfWeek() +" " + dateOfPublished.getDayOfMonth()+ " " + dateOfPublished.getMonth();

    }
    public Advertisement(int idAdvertisement, String headline, String description, String number, Theme theme, Status status, int idUser, LocalDateTime dateOfPublished) {
        this.idAdvertisement = idAdvertisement;
        this.headline = headline;
        this.description = description;
        this.number = number;
        this.theme = theme;
        this.status = status;
        this.idUser = idUser;
        this.dateOfPublished = dateOfPublished;
    }
}
