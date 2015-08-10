package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesDAOImplement implements NotesDAO {

    public int delete(Note note) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("DELETE FROM notes WHERE id = ?;");
            ps.setInt(1, note.getId());
            count = ps.executeUpdate();
        }  catch (SQLException e ) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int insert(Note note) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("INSERT INTO  notes (login,note,datetimecreate) VALUES (?,?,?);");
            ps.setString(1, note.getLogin());
            ps.setString(2, note.getNote());
            ps.setDate(3, (Date) note.getDateTimeCreate());
            count = ps.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int update(Note note) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("UPDATE notes SET  login = ?, note = ?, datetimecreate = ? WHERE id = ?;");
            ps.setString(1, note.getLogin());
            ps.setString(2, note.getNote());
            ps.setDate(3, (Date) note.getDateTimeCreate());
            ps.setInt(4, note.getId());
            count = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM notes;");
            rs = st.executeQuery();
            while (rs.next()) {
                Note note = new Note (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDate(4));
                notes.add(note);
            }
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

   public Note getNote(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Note note = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM notes WHERE id = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                note = new Note(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4));
            }
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return note;
    }

   public List<Note> getLastUserNotes(String login) {
        List<Note> notes = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM notes WHERE login LIKE ? ORDER BY datetimecreate DESC LIMIT 10 ;");
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                Note note = new Note (rs.getInt(1),null, rs.getString(3),rs.getDate(4));
                notes.add(note);
            }
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

    public List<Note> getUserNotes(String login) {
        List<Note> notes = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM notes WHERE login LIKE ?;");
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                Note note = new Note (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDate(4));
                notes.add(note);
            }
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }
}
