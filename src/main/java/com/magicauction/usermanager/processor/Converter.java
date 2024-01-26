package com.magicauction.usermanager.processor;

import com.magicauction.usermanager.entity.Email;
import com.magicauction.usermanager.entity.PhoneNumber;
import com.magicauction.usermanager.entity.User;
import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.utils.HashUtils;

public abstract class Converter {
    public static UserDto toDto(User u){
        return new UserDto(
                u.getUserId(),
                u.getName(),
                HashUtils.decrypt(u.getEncryptedPassword()),
                u.getTradeArea(),
                u.getEmail() == null ? "" : u.getEmail().stringValue(),
                u.getPhoneNumber() == null ? "" : u.getPhoneNumber().stringValue(),
                u.getFirstName(),
                u.getLastName()
        );
    }

    public static User toEntity(UserDto user) throws EmailNotValidException, PhoneNumberNotValidException {
        User u = new User();
        u.setFirstName(user.firstName());
        u.setLastName(user.lastName());
        u.setEncryptedPassword(HashUtils.encrypt(user.name(),user.password()));
        u.setName(user.name());
        u.setTradeArea(user.tradeArea());
        u.setPhoneNumber(PhoneNumber.fromString(user.phoneNumber(), u));
        u.setEmail(Email.fromString(user.email(), u));
        return u;
    }

    public static User toEntity(Long id, UserDto user) throws EmailNotValidException, PhoneNumberNotValidException {
        User u = new User();
        u.setUserId(id);
        u.setFirstName(user.firstName());
        u.setLastName(user.lastName());
        u.setEncryptedPassword(HashUtils.encrypt(user.name(),user.password()));
        u.setName(user.name());
        u.setTradeArea(user.tradeArea());
        u.setPhoneNumber(PhoneNumber.fromString(user.phoneNumber()));
        u.setEmail(Email.fromString(user.email()));
        return u;
    }
}
