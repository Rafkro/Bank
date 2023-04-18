package org.example.utility;

public interface UserDAO {

    void addCustomer (Customer customer);
    void uptadeName(Customer customer);
    void uptadeLastName(Customer customer);
    void uptadeEmail(Customer customer);
    void uptadePassword(Customer customer);
    void deleteCustomer(Customer customer);
    void depositMoney(Customer customer);
    void withdrawMoney(Customer customer);
    void transferMoney(Customer customer);

    void printTransaction(Transaction transaction);

}
