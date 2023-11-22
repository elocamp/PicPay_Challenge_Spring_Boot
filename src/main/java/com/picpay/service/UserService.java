package com.picpay.service;

import com.picpay.domain.User;
import com.picpay.domain.UserType;
import com.picpay.dto.UserDto;
import com.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDto user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() throws Exception {
        if (!this.repository.findAll().isEmpty()) return this.repository.findAll();
        else throw new Exception(String.valueOf(HttpStatus.NOT_FOUND) + "- No user has been created yet.");
    }

    public User getUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("User not found."));
    }

    public User updateUserById(Long id, UserDto dto) throws Exception {
        var user = this.repository.findById(id).orElseThrow(() -> new Exception(String.valueOf(HttpStatus.NOT_FOUND) + "- User ID not found. Please check again."));
        user.setName(dto.name());
        user.setDocument(dto.document());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUserType(dto.userType());
        user.setBalance(dto.balance());
        this.saveUser(user);
        return user;
    }

    public void deleteUserById(Long id) throws Exception {
        var deprecatedUser = this.repository.findById(id).orElseThrow(() -> new Exception(String.valueOf(HttpStatus.NOT_FOUND) + "- User ID not found. Please check again."));
        this.repository.delete(deprecatedUser);
    }

    public boolean validateUser(User buyer, BigDecimal amount) throws Exception {
        if (buyer.getUserType() == UserType.SELLER) throw new Exception("Sellers can not make transactions.");

        if (buyer.getBalance().compareTo(amount) < 0) throw new Exception("Balance is not enough for this transaction.");

        return true;
    }

}
