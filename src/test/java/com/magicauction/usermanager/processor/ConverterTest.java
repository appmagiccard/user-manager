package com.magicauction.usermanager.processor;

import com.magicauction.usermanager.entity.Email;
import com.magicauction.usermanager.entity.PhoneNumber;
import com.magicauction.usermanager.entity.User;
import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConverterTest {

    @Test
    void toDto_whenIsOK() throws PhoneNumberNotValidException, EmailNotValidException {
        UserDto expected = userDto(1L);
        UserDto actual = Converter.toDto(user(1L));
        assertNotNull(actual);
        assertEquals(expected, actual);

    }

    @Test void toEntity_whenIsOk() throws PhoneNumberNotValidException, EmailNotValidException {
        User expected = user();
        User actual = Converter.toEntity(userDto(10L));
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test void toEntity2_whenIsOk() throws PhoneNumberNotValidException, EmailNotValidException {
        long id = 10L;
        User expected = user(id);
        User actual = Converter.toEntity(id, userDto(id));
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    private User user() throws PhoneNumberNotValidException, EmailNotValidException {
        User user = user(10L);
        user.setUserId(null);
        return user;
    }

    private UserDto userDto(long l) {
        return new UserDto(
                l,
                "name "+l,
                "pass "+l,
                "trade area "+l,
                "powerituasd@hauiitao.com",
                "+000000000000",
                "fName "+l,
                "lName "+l
        );
    }

    private User user(long l) throws PhoneNumberNotValidException, EmailNotValidException {
        User u = new User();
        u.setUserId(l);
        u.setName("name "+l);
        u.setPhoneNumber(PhoneNumber.fromString("+000000000000"));
        u.setEmail(Email.fromString("powerituasd@hauiitao.com"));
        u.setTradeArea("trade area "+l);
        u.setEncryptedPassword("name "+l+":"+"pass "+l);
        u.setFirstName("fName "+l);
        u.setLastName("lName "+l);
        return u;
    }


}