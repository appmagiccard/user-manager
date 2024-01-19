package com.magicauction.usermanager.entity.exceptions;

public class UserNotFoundException extends UserManagerException {
    private static String MESSAGE = "User with id %s not found";
    public UserNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
