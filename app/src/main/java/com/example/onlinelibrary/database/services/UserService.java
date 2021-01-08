package com.example.onlinelibrary.database.services;

import com.example.onlinelibrary.database.DAOs.UserDao;
import com.example.onlinelibrary.model.User;

import java.util.List;

public class UserService implements UserDao {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(String email) {
       return  userDao.getUser(email);
    }

    @Override
    public Long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public void deleteUSer(User user) {
    userDao.deleteUSer(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }
}