package by.khrapovitsky.bean;

import by.khrapovitsky.dao.Factory;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
public class NoteBean {

    int id;
    String login;
    String note;
    Timestamp dateTimeCreate;

    @ManagedProperty(value="#{notes.lastNote}")
    List<Note> listNotes;

    @ManagedProperty(value="#{notes.allNote}")
    List<Note> allNotes;

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

    public Timestamp getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(Timestamp dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public List<Note> getListNotes() {
        User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
        List<Note> listNote = Factory.getInstance().getNotesDAO().getLastUserNotes(user);
        return listNote;
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
    }

    public List<Note> getAllNotes() {
        User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
        List<Note> listNote = Factory.getInstance().getNotesDAO().getUserNotes(user);
        return listNote;
    }

    public void setAllNotes(List<Note> allNotes) {
        this.allNotes = allNotes;
    }

    public NoteBean() {
    }

    public void addNote(){
        User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
        Note note = new Note(user, this.note, new Timestamp(new java.util.Date().getTime()));
        Factory.getInstance().getNotesDAO().insert(note);
    }
}
