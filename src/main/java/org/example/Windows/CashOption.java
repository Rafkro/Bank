package org.example.Windows;

import org.example.utility.Customer;
import org.example.utility.UserDAOImpl;

import javax.swing.*;
import java.awt.*;

public class CashOption extends JFrame {


    JButton depositButton, withdrawButton, transferButton,backButton;
    JPanel optionPanel,mainPanel,backPanel;

    public CashOption(String value) {
        UserDAOImpl userDAO = new UserDAOImpl();
        Customer customer = new Customer();

        Icon rightArrowIcon = new ImageIcon("Images/arrow-square-right.png");


        depositButton = new JButton("Wpłata");
        depositButton.setIcon(rightArrowIcon);
        depositButton.setHorizontalTextPosition(JButton.RIGHT);
        depositButton.setIconTextGap(200);
        depositButton.setBackground(Color.red);
        depositButton.setForeground(Color.white);
        depositButton.setFocusable(false);
        depositButton.addActionListener(e -> {

            customer.setCustomerId(Integer.parseInt(value));

            userDAO.depositMoney(customer);
        });


        withdrawButton = new JButton("Wypłata");
        withdrawButton.setIcon(rightArrowIcon);
        withdrawButton.setFocusable(false);
        withdrawButton.setBackground(Color.red);
        withdrawButton.setForeground(Color.white);
        withdrawButton.setHorizontalTextPosition(JButton.RIGHT);
        withdrawButton.setIconTextGap(200);
        withdrawButton.addActionListener(e -> {
            customer.setCustomerId(Integer.parseInt(value));

            userDAO.withdrawMoney(customer);


        });

        transferButton = new JButton("Przelewy");
        transferButton.setIcon(rightArrowIcon);
        transferButton.setBackground(Color.red);
        transferButton.setForeground(Color.white);
        transferButton.setFocusable(false);
        transferButton.setHorizontalTextPosition(JButton.RIGHT);
        transferButton.setIconTextGap(200);
        transferButton.addActionListener(e -> {
            customer.setCustomerId(Integer.parseInt(value));
            userDAO.transferMoney(customer);
        });



        backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu(value);
        });


        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(6,1,0,20));
        optionPanel.add(Box.createRigidArea(new Dimension(0, 250)));
        optionPanel.setBackground(Color.white);
        optionPanel.add(depositButton);
        optionPanel.add(withdrawButton);
        optionPanel.add(transferButton);

        backPanel = new JPanel();
        backPanel.setBackground(Color.white);
        backPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(backButton);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(optionPanel,BorderLayout.CENTER);
        mainPanel.add(backPanel,BorderLayout.EAST);

        this.add(mainPanel);

        this.setSize(450, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
    }
}
