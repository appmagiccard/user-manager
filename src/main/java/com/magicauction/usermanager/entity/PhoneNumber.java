package com.magicauction.usermanager.entity;

import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="PHONE_NUMBERS")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prefix;  //+
    private String country; //54
    private String city;    //11
    private String number;  //12345678
    private String type;    //Nullable, LandLine, Mobile, Company, Fax-only

    @OneToOne
    private User user;

    public static boolean isValid(String input){
        //TODO: COMPLETE THIS!!
        return input.startsWith("+");
    }
    public static PhoneNumber fromString(String input) throws PhoneNumberNotValidException {
        //TODO: COMPLETE THIS!!
        if(!isValid(input)){
            throw new PhoneNumberNotValidException(input);
        }
        PhoneNumber p = new PhoneNumber();
        p.prefix = input.substring(0,1);   //+
        p.country = input.substring(1,3);  //54
        p.city = input.substring(3,5);       //11
        p.number = input.substring(5);     //12345678
        p.type = "Private";
        return p;
    }
    public static PhoneNumber fromString(String input, User u) throws PhoneNumberNotValidException {
        PhoneNumber p = fromString(input);
        p.setUser(u);
        return p;
    }

    public String stringValue(){
        return ""+prefix+country+city+number;
    }
    public PhoneNumber() {
    }

    public PhoneNumber(Long id, String prefix, String country, String city, String number, String type, User user) {
        this.id = id;
        this.prefix = prefix;
        this.country = country;
        this.city = city;
        this.number = number;
        this.type = type;
        this.user = user;
    }

    public PhoneNumber(String prefix, String country, String city, String number, String type, User user) {
        this.prefix = prefix;
        this.country = country;
        this.city = city;
        this.number = number;
        this.type = type;
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(prefix, that.prefix) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(number, that.number) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prefix, country, city, number, type, user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
