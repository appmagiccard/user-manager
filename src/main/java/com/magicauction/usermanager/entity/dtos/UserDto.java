package com.magicauction.usermanager.entity.dtos;

public record UserDto(
        Long id,
        String name,
        String password,
        String tradeArea,
        String email,
        String phoneNumber,
        String firstName,
        String lastName
) {
}
