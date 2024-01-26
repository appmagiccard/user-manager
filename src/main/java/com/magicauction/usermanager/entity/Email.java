package com.magicauction.usermanager.entity;

import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@Entity
@Table(name="EMAILS")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address; //lacalamuchita_89
    private String domain;  //@gmail.com

    @OneToOne
    private User user;

    public static boolean isValid(String input){
        //TODO: COMPLETE THIS!!
        return input.contains("@");
    }
    public static Email fromString(String input) throws EmailNotValidException {
        if (!isValid(input)){
            throw new EmailNotValidException(input);
        }
        String[] split = input.split("@");
        Email e = new Email();
        e.setAddress(split[0]);
        e.setDomain(split[1]);
        return e;
    }
    public static Email fromString(String input, User u) throws EmailNotValidException {
        Email e = fromString(input);
        e.setUser(u);
        return e;
    }

    public String stringValue(){
        return String.format("%s@%s", address, domain);
    }

    public Email() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return address.equals(email.address) && domain.equals(email.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, domain, user);
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Email(Long id, String address, String domain, User user) {
        this.id = id;
        this.address = address;
        this.domain = domain;
        this.user = user;
    }

    public Email(String address, String domain, User user) {
        this.address = address;
        this.domain = domain;
        this.user = user;
    }
}
