package org.example.GUI.UpdateGUI;

import org.example.utility.Customer;
import org.example.utility.UserDAOImpl;

public class Email {
    public Email(String value) {

        UserDAOImpl userDAO = new UserDAOImpl();
        Customer customer = new Customer();

        customer.setCustomerId(Integer.parseInt(value));

        userDAO.uptadeEmail(customer);
    }
}
