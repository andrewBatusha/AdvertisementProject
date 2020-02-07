package model;


import enums.Status;
import enums.Theme;

public class Advertisement {
    private int idAdvertisement;
    private String headline;
    private String description;
    private String mail;
    private String number;
    private Theme theme;
    private Status status = Status.WAITING;

    public Advertisement() {
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "idAdvertisement=" + idAdvertisement +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", mail='" + mail + '\'' +
                ", number='" + number + '\'' +
                ", theme=" + theme +
                ", status=" + status +
                ", visibility=" + visibility +
                ", idUser=" + idUser +
                '}';
    }

    public Advertisement(int idAdvertisement, String headline, String description, String mail, String number, Theme theme, Status status, boolean visibility, int idUser) {
        this.idAdvertisement = idAdvertisement;
        this.headline = headline;
        this.description = description;
        this.mail = mail;
        this.number = number;
        this.theme = theme;
        this.status = status;
        this.visibility = visibility;
        this.idUser = idUser;
    }

    public int getIdAdvertisement() {
        return idAdvertisement;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private boolean visibility = false;
    private int idUser;
}
