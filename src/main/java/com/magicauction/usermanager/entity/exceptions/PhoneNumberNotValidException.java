package com.magicauction.usermanager.entity.exceptions;

public class PhoneNumberNotValidException extends UserManagerException {
    public PhoneNumberNotValidException(String input) {
        super(String.format("Phone number not valid: %s", input));
    }
}
