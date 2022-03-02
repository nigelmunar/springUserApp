package com.nigelxiaido.nigeliaidoperson.controllers;

import com.nigelxiaido.nigeliaidoperson.entities.User;
import com.nigelxiaido.nigeliaidoperson.exceptions.BadRequestException;
import com.nigelxiaido.nigeliaidoperson.exceptions.PersonNotFoundException;
import com.nigelxiaido.nigeliaidoperson.repository.UserRepository;
import com.nigelxiaido.nigeliaidoperson.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        try {
            Page<User> userList;
            if(name != null && age != null) {
                userList  = userRepository.findUserByNameAndAge(name, age, PageRequest.of(page, size));
            }
            else if(name != null) {
                userList = userRepository.findUserByName(name, PageRequest.of(page, size));
            }
            else if(age != null) {
                userList = userRepository.findUserByAge(age, PageRequest.of(page, size));
            } else {
                userList = userRepository.findAll(PageRequest.of(page, size));
            }

            if(userList.isEmpty()) {
                return new ResponseEntity<Page<User>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<User>>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Page<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws PersonNotFoundException {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }

        throw new PersonNotFoundException("User with " + id + " was not found!");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) throws BadRequestException {
        try {
            return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Record not found");
        }
    }

}
