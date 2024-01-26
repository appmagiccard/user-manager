package com.magicauction.usermanager.processor;

import com.magicauction.usermanager.entity.Email;
import com.magicauction.usermanager.entity.PhoneNumber;
import com.magicauction.usermanager.entity.User;
import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.entity.exceptions.UserNotFoundException;
import com.magicauction.usermanager.entity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProcessorTest {

    private UserProcessor processor;
    @Mock private UserRepository repository;

    @BeforeEach
    void init(){
        processor = new UserProcessor(repository);
    }

    @Test void getAllUsers_Ok() throws PhoneNumberNotValidException, EmailNotValidException {
        when(repository.findAll()).thenReturn(Arrays.asList(user(1L), user(2L), user(3L)));
        List<UserDto> expected = Arrays.asList(userDto(1L), userDto(2L), userDto(3L));
        List<UserDto> actual = processor.getAllUsers();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    @Test void getAllUsers_Empty(){
        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<UserDto> actual = processor.getAllUsers();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }
    @Test void getOneUserById_Ok() throws PhoneNumberNotValidException, EmailNotValidException, UserNotFoundException {
        UserDto expected = userDto(1L);
        when(repository.findById(any())).thenReturn(Optional.of(user(1L)));
        UserDto actual = processor.getOneUserById(1L);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    @Test void getOneUserById_NotFound(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            processor.getOneUserById(1L);
        });

        String expectedMessage = "User with id 1 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test void createNewUser_Ok() throws PhoneNumberNotValidException, EmailNotValidException {
        when(repository.save(any())).thenReturn(user(1L));
        UserDto expected = userDto(1L);
        UserDto actual = processor.createNewUser(userDto());
        assertNotNull(actual);
        assertEquals(expected, actual);

    }

    @Test void createNewUser_PhoneNumberNotValidException(){}
    @Test void createNewUser_EmailNotValidException(){}



    @Test void updateUser_Ok() throws UserNotFoundException, PhoneNumberNotValidException, EmailNotValidException {
        long id = 100L;
        when(repository.findById(any())).thenReturn(Optional.of(user(id)));
        when(repository.save(any())).thenReturn(user(id));
        UserDto expected = userDto(id);
        UserDto actual = processor.updateUser(id, userDto(id));
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test void updateUser_UserNotFoundException(){}
    @Test void updateUser_PhoneNumberNotValidException(){}
    @Test void updateUser_EmailNotValidException(){}

    @Test void deleteUser_Ok() throws UserNotFoundException, PhoneNumberNotValidException, EmailNotValidException {
        Long expected = 1L;
        when(repository.findById(any())).thenReturn(Optional.of(user(1L)));
        Long actual = processor.deleteOneUserById(1L);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }



    @Test void getAllInactive_Ok() throws PhoneNumberNotValidException, EmailNotValidException {
        List<User> users = Arrays.asList(user(1L), user(2L), user(3L), inactiveUser(10L), inactiveUser(20L), inactiveUser(30L));
        when(repository.findAll()).thenReturn(users);
        List<UserDto> expected = Arrays.asList(userDto(10L), userDto(20L), userDto(30L));
        List<UserDto> actual = processor.getAllInactiveUsers();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    private User inactiveUser(long l) throws PhoneNumberNotValidException, EmailNotValidException {
        User u = user(l);
        u.setToInactive();
        return u;
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

    private UserDto userDto() {
        return new UserDto(
                "name @@",
                "pass @@",
                "trade area @@",
                "powerituasd@hauiitao.com",
                "+000000000000",
                "fName @@",
                "lName @@"
        );
    }
}