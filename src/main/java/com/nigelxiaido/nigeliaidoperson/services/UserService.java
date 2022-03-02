package com.nigelxiaido.nigeliaidoperson.services;

import com.nigelxiaido.nigeliaidoperson.entities.User;
import com.nigelxiaido.nigeliaidoperson.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(User user, Long id) throws Exception {

        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            User existingUser = result.get();
            existingUser.setAge(user.getAge());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setRoles(user.getRoles());
            existingUser.setSurname(user.getSurname());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }
        throw new Exception("User not found");
    }

    public HttpStatus delete(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return HttpStatus.NO_CONTENT;
        }
        throw new Exception("User not found");
    }

}
