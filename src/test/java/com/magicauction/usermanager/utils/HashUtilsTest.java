package com.magicauction.usermanager.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HashUtilsTest {

    private String name = "7n0mbr3";
    private String password = "P4SS";


    @Test void encrypt_whenIsOk(){

        String expected = name+":"+password;
        String actual = HashUtils.encrypt(name, password);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test void decrypt_whenIsOk(){
        String pss = name+":"+password;
        String actual = HashUtils.decrypt(pss);
        assertNotNull(actual);
        assertEquals(password, actual);
    }

    @Test void decrypt_whenExceptionIsThrown(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HashUtils.decrypt(password);
        });

        String expectedMessage = "Password is not valid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}