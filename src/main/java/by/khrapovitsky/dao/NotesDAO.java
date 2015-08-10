package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;

import java.util.List;

public interface NotesDAO {
    int delete(Note note);
    int insert(Note note);
    int update(Note note);
    List<Note> getAllNotes();
    Note getNote(int id);
    List<Note> getLastUserNotes(String login);
    List<Note> getUserNotes(String login);
}
