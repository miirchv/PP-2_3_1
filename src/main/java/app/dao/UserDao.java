package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(long id);

    void saveUser(User user);

    void updateUser(long id, User user);

    void deleteUser(long id);
}
