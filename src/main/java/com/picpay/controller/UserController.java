package com.picpay.controller;

import com.picpay.domain.User;
import com.picpay.dto.UserDto;
import com.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        User newUser = service.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        var users = this.service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value="id") Long id) throws Exception {
        var user = this.service.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable(value = "id") Long id,
                                                 @RequestBody UserDto dto) throws Exception {
        this.service.updateUserById(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(value = "id") Long id) throws Exception {
        this.service.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}
