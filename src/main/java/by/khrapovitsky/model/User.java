package by.khrapovitsky.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "login")
    String login;
    @Column(name = "password")
    String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Note> notes;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public User(String login, String password, List<Note> notes) {
        this.login = login;
        this.password = password;
        this.notes = notes;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
