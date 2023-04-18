package org.example.utility;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "transactions")
public class Transaction {

    @Column(name = "customer_id")
    private int customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "transaction_kind")
    private String transactionKind;

    public Transaction() {
    }

    public Transaction(int customerId, int transactionAmount, LocalDateTime transactionDate,String transactionKind) {
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionKind = transactionKind;
    }

    public String localDateTime(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }




    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionKind() {
        return transactionKind;
    }

    public void setTransactionKind(String transactionKind) {
        this.transactionKind = transactionKind;
    }

    @Override
    public String toString() {
        return
                "Id transakcji: " + transactionId + ", " +
                "Kwota transakcji: " + transactionAmount +", "+
                "Typ transakcji: " + transactionKind +", "+
                "Data transakcji: "+localDateTime() + "\n";
    }


}
