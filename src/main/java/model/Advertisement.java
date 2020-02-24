package model;


import enums.Status;
import enums.Theme;

import java.time.LocalDateTime;

public class Advertisement {
    private int idAdvertisement;
    private String headline;
    private String description;
    private String number;
    private Theme theme;
    private Status status = Status.WAITING;
    private int idUser;
    private LocalDateTime dateOfPublished;


    public Advertisement() {
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "idAdvertisement=" + idAdvertisement +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", number='" + number + '\'' +
                ", theme=" + theme +
                ", status=" + status +
                ", idUser=" + idUser +
                ", dateOfPublished=" + dateOfPublished +
                '}';
    }

    public Advertisement(int idAdvertisement, String headline, String description, String number, Theme theme, int idUser, LocalDateTime dateOfPublished) {
        this.idAdvertisement = idAdvertisement;
        this.headline = headline;
        this.description = description;
        this.number = number;
        this.theme = theme;
        this.idUser = idUser;
        this.dateOfPublished = dateOfPublished;
    }

    public int getIdAdvertisement() {
        return idAdvertisement;
    }

    public String getDateForView(){
        return String.valueOf(dateOfPublished.getDayOfWeek()) +" " + dateOfPublished.getDayOfMonth()+ " " + dateOfPublished.getMonth();

    }

    public LocalDateTime getDateOfPublished() {
        return dateOfPublished;
    }

    public void setDateOfPublished(LocalDateTime dateOfPublished) {
        this.dateOfPublished = dateOfPublished;
    }

    public void setIdAdvertisement(int idAdvertisement) {
        this.idAdvertisement = idAdvertisement;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
