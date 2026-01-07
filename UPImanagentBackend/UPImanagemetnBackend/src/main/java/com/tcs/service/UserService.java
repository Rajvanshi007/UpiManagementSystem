package com.tcs.service;

import com.tcs.model.User;
import com.tcs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) return Optional.empty();

        User user = userOpt.get();

        // ❌ FREEZE CHECK
        if (user.isFrozen()) {
            throw new RuntimeException("Account is frozen");
        }

        if (user.getPassword().equals(password)) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


}
