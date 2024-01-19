package com.magicauction.usermanager.entity.exceptions;

public class EmailNotValidException extends UserManagerException {
    public EmailNotValidException(String s) {
        super(String.format("Input String not valid: %s", s));
    }

}
