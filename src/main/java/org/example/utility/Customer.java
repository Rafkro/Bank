package org.example.utility;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {


    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "cash")
    private double cash;
    @Column(name = "login_password")
    private int loginPassword;

    @Column(name = "account_number")
    private BigInteger accountNumber;



    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, double cash, int loginPassword,BigInteger accountNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cash = cash;
        this.loginPassword = loginPassword;
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }


    public int getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(int loginPassword) {
        this.loginPassword = loginPassword;
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cash=" + cash +
                ", loginPassword=" + loginPassword +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
