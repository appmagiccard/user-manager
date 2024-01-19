package com.magicauction.usermanager.entity;

import com.magicauction.usermanager.entity.exceptions.EmailNotValidException;
import com.magicauction.usermanager.entity.exceptions.PhoneNumberNotValidException;
import com.magicauction.usermanager.utils.HashUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="USERS")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String name;

    //user:password encrypt hasheado?
    private String encryptedPassword;

    private String tradeArea;

    @OneToOne(cascade = CascadeType.ALL)
    private Email email;

    @OneToOne(cascade = CascadeType.ALL)
    private PhoneNumber phoneNumber;

    private String firstName;
    private String lastName;
    private boolean isActive;


    //Publisher View
    //Publis que hice
    @OneToMany(mappedBy = "publisher")
    private List<Publication> myPublications;

    //Ofertas que me hicieron
    @OneToMany(mappedBy = "publisher")
    private List<Offer> myPublisherOffers;

    //Buyer View
    //Ofertas que hice
    @OneToMany(mappedBy = "buyer")
    private List<Offer> myBuyerOffers;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradeArea() {
        return tradeArea;
    }

    public void setTradeArea(String tradeArea) {
        this.tradeArea = tradeArea;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Publication> getMyPublications() {
        return myPublications;
    }

    public void setMyPublications(List<Publication> myPublications) {
        this.myPublications = myPublications;
    }

    public List<Offer> getMyPublisherOffers() {
        return myPublisherOffers;
    }

    public void setMyPublisherOffers(List<Offer> myPublisherOffers) {
        this.myPublisherOffers = myPublisherOffers;
    }

    public List<Offer> getMyBuyerOffers() {
        return myBuyerOffers;
    }

    public void setMyBuyerOffers(List<Offer> myBuyerOffers) {
        this.myBuyerOffers = myBuyerOffers;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setToInactive(){
        this.isActive = false;
    }

    public User(Long userId, String name, String encryptedPassword, String tradeArea, Email email, PhoneNumber phoneNumber, String firstName, String lastName, List<Publication> myPublications, List<Offer> myPublisherOffers, List<Offer> myBuyerOffers) {
        this.userId = userId;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
        this.tradeArea = tradeArea;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.myPublications = myPublications;
        this.myPublisherOffers = myPublisherOffers;
        this.myBuyerOffers = myBuyerOffers;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", isActive='" + isActive + '\'' +
                ", name='" + name + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", tradeArea='" + tradeArea + '\'' +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", myPublications=" + myPublications +
                ", myPublisherOffers=" + myPublisherOffers +
                ", myBuyerOffers=" + myBuyerOffers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(encryptedPassword, user.encryptedPassword) && Objects.equals(tradeArea, user.tradeArea) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(myPublications, user.myPublications) && Objects.equals(myPublisherOffers, user.myPublisherOffers) && Objects.equals(myBuyerOffers, user.myBuyerOffers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, encryptedPassword, tradeArea, email, phoneNumber, firstName, lastName, myPublications, myPublisherOffers, myBuyerOffers);
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public User(String name, String tradeArea, String email, String phoneNumber, String firstName, String lastName, String password) throws EmailNotValidException, PhoneNumberNotValidException {
        this.name = name;
        this.tradeArea = tradeArea;
        this.email = Email.fromString(email);
        this.phoneNumber = PhoneNumber.fromString(phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.encryptedPassword = HashUtils.encrypt(name, password);
    }

    public User() {
        this.isActive = true;
    }
}
