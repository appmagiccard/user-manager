package com.magicauction.usermanager.processor;

import com.magicauction.usermanager.entity.Email;
import com.magicauction.usermanager.entity.PhoneNumber;
import com.magicauction.usermanager.entity.User;
import com.magicauction.usermanager.entity.dtos.UserDto;
import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.entity.exceptions.UserNotFoundException;
import com.magicauction.usermanager.entity.repository.UserRepository;
import com.magicauction.usermanager.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProcessor implements IUserProcessor{

    private final UserRepository repository;

    @Autowired
    public UserProcessor(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll().stream().filter(User::isActive).map(Converter::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getOneUserById(Long id) throws UserNotFoundException {

        return Converter.toDto(findByidOrThrow(id));
    }

    @Override
    public UserDto createNewUser(UserDto user) throws EmailNotValidException, PhoneNumberNotValidException {
        User userEntity = Converter.toEntity(user);
        User saved = repository.save(userEntity);
        return Converter.toDto(saved);
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) throws UserNotFoundException, EmailNotValidException, PhoneNumberNotValidException {
        User userFound = findByidOrThrow(id);
        //... set new props
        userFound.setFirstName(user.firstName() == null ? userFound.getFirstName() : user.firstName());
        userFound.setLastName(user.lastName() == null ? userFound.getLastName() : user.lastName());
        userFound.setEncryptedPassword(user.password() == null ? userFound.getEncryptedPassword() : HashUtils.encrypt(user.name(),user.password()));
        userFound.setName(user.name() == null ? userFound.getName() : user.name());
        userFound.setTradeArea(user.tradeArea() == null ? userFound.getTradeArea(): user.tradeArea());
        userFound.setPhoneNumber(user.phoneNumber() == null ? userFound.getPhoneNumber() : PhoneNumber.fromString(user.phoneNumber()));
        userFound.setEmail(user.email() == null ? userFound.getEmail() :Email.fromString(user.email()));
        //...
        User saved = repository.save(userFound);
        return Converter.toDto(saved);
    }

    @Override
    public Long deleteOneUserById(Long id) throws UserNotFoundException {
        User userFound = findByidOrThrow(id);
        userFound.setToInactive();
        repository.save(userFound);
        return id;
    }

    @Override
    public List<UserDto> getAllInactiveUsers() {
        return repository.findAll().stream().filter(user -> !user.isActive()).map(Converter::toDto).collect(Collectors.toList());
    }

    private User findByidOrThrow(Long id) throws UserNotFoundException {
        return repository.findById(id).filter(User::isActive).orElseThrow(() -> new UserNotFoundException(id));
    }

}
