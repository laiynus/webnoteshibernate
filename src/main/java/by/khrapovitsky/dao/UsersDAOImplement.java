package by.khrapovitsky.dao;

import by.khrapovitsky.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UsersDAOImplement implements UsersDAO {

  public int delete(User user) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("DELETE FROM users WHERE login LIKE ?;");
            ps.setString(1, user.getLogin());
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

    public int insert(User user) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("INSERT users (login,password) VALUE (?,?);");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
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

   public int update(User user) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ConnectionDB connection = new ConnectionDB();
            ps = connection.createConnection().prepareStatement("UPDATE users SET  password = ? WHERE login LIKE ?;");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getLogin());
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

    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM users;");
            rs = st.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2));
                users.add(user);
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
        return users;
    }

   public User getUser(String login) {
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;
        try {
            ConnectionDB connection = new ConnectionDB();
            st = connection.createConnection().prepareStatement("SELECT * FROM users WHERE login LIKE ?;");
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException | ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        }
        finally {
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
        return user;
    }
}
