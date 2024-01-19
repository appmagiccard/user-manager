package com.magicauction.usermanager.controller;

import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.entity.exceptions.UserNotFoundException;
import com.magicauction.usermanager.processor.IUserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserProcessor processor;

    @Autowired
    public UserController(IUserProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(processor.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUserById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(processor.getOneUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createOneUser(@RequestBody UserDto user) throws EmailNotValidException, PhoneNumberNotValidException {
        return ResponseEntity.ok(processor.createNewUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateOneUserById(@PathVariable Long id, @RequestBody UserDto user) throws UserNotFoundException, EmailNotValidException, PhoneNumberNotValidException {
        return ResponseEntity.ok(processor.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOneUserById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(processor.deleteOneUserById(id));
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<UserDto>> getAllInactiveUsers(){
        return ResponseEntity.ok(processor.getAllInactiveUsers());
    }

}
