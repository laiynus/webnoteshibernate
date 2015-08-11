package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;

import java.util.List;

public interface NotesDAO {
    void delete(Note note);
    void insert(Note note);
    void update(Note note);
    List<Note> getAllNotes();
    Note getNote(int id);
    List<Note> getLastUserNotes(String login);
    List<Note> getUserNotes(String login);
}
