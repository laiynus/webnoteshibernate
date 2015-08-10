package by.khrapovitsky.dao;

import by.khrapovitsky.model.User;

import java.util.List;


public interface UsersDAO {
    int delete(User user);
    int insert(User user);
    int update(User user);
    List<User> getAllUsers();
    User getUser(String login);
}
