package com.main.itmo.backend.DAO;

import com.main.itmo.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
