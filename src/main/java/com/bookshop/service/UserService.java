package com.bookshop.service;

import com.bookshop.to.User;
import com.bookshop.repository.UserRepository;

import java.util.List;
public class UserService {
    static UserRepository repository = new UserRepository();
    public void register(User user) {
        repository.save(user);
    }
    public User login (String username, String password) {
        return repository.find(username, password);
    }

    public static List<User> findAll() {

        return repository.findAll();
    }
    public void delete( String username) {
        repository.delete(username);
    }
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
    public void update(User user) {
        repository.update(user);
    }
    public void block(String username) {
        repository.block(username);

    }

    public void unblock(String username) {
        repository.unblock(username);
    }
}

