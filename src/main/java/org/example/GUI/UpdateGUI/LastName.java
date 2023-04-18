package org.example.GUI.UpdateGUI;

import org.example.utility.Customer;
import org.example.utility.UserDAOImpl;

public class LastName {
    public LastName(String value) {

        UserDAOImpl userDAO = new UserDAOImpl();
        Customer customer = new Customer();

        customer.setCustomerId(Integer.parseInt(value));

        userDAO.uptadeLastName(customer);
    }
}
