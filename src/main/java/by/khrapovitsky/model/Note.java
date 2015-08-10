package by.khrapovitsky.model;

import java.sql.Date;

public class Note {

    int id;
    String login;
    String note;
    Date dateTimeCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(Date dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public Note(int id, String login, String note,Date dateTimeCreate) {
        this.id = id;
        this.login = login;
        this.note = note;
        this.dateTimeCreate = dateTimeCreate;
    }

    public Note(String login, String note,Date dateTimeCreate) {
        this.id = id;
        this.login = login;
        this.note = note;
        this.dateTimeCreate = dateTimeCreate;
    }
}
