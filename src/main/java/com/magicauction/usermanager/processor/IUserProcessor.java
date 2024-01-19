package com.magicauction.usermanager.processor;

import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.entity.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserProcessor {
    List<UserDto> getAllUsers();

    UserDto getOneUserById(Long id) throws UserNotFoundException;

    UserDto createNewUser(UserDto user) throws EmailNotValidException, PhoneNumberNotValidException;

    UserDto updateUser(Long id, UserDto user) throws UserNotFoundException, EmailNotValidException, PhoneNumberNotValidException;

    Long deleteOneUserById(Long id) throws UserNotFoundException;

    List<UserDto> getAllInactiveUsers();
}
