package org.example.Windows;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.GUI.UpdateGUI.*;
import javax.swing.*;
import java.awt.*;

import static org.example.utility.UserDAOImpl.getEntityManager;

public class AccountManage extends JFrame {

    JButton changeName, changeLastName, changeEmail, changePassword, backButton, deleteButton, emptyButton, refreshButton;
    JPanel mainPanel, buttonAndLabelPanel, eastPanel, northPanel;
    JTextArea userData;

    String firstName, lastName, email, password, accountNumber;


    public AccountManage(String value) {

        EntityManager entityManager = getEntityManager();

        // Zapytanie do bazy danych o imię
        Query queryName = entityManager.createQuery("SELECT firstName FROM Customer WHERE customerId=:id");
        queryName.setParameter("id", value);
        firstName = String.valueOf(queryName.getSingleResult());

        // Zapytanie do bazy danych o  nazwisko
        Query queryLastName = entityManager.createQuery("SELECT lastName FROM Customer WHERE customerId=:id");
        queryLastName.setParameter("id", value);
        lastName = String.valueOf(queryLastName.getSingleResult());

        // Zapytanie do bazy danych o adres email
        Query queryEmail = entityManager.createQuery("SELECT email FROM Customer WHERE customerId=:id");
        queryEmail.setParameter("id", value);
        email = String.valueOf(queryEmail.getSingleResult());

        // Zapytanie do bazy danych o hasło
        Query queryPassword = entityManager.createQuery("SELECT loginPassword FROM Customer WHERE customerId=:id");
        queryPassword.setParameter("id", value);
        password = String.valueOf(queryPassword.getSingleResult());

        // Zapytanie do bazy danych o numer konta
        Query queryAccountNumber = entityManager.createQuery("SELECT accountNumber FROM Customer WHERE customerId=:id");
        queryAccountNumber.setParameter("id", value);
        accountNumber = String.valueOf(queryAccountNumber.getSingleResult());

        // Wyświetlenie informacji o użytkowniku (ułatwienie)
        userData = new JTextArea();
        userData.setFont(new Font("Calibri", Font.BOLD, 16));
        userData.setEditable(false);
        userData.setText("\n" + "Numer id użytkownika: " + value + "\n" + "\n" +
                "Imię: " + firstName + "\n" + "\n" +
                "Nazwisko: " + lastName + "\n" + "\n" +
                "Adres e-mail: " + email + "\n" + "\n" +
                "Hasło: " + password + "\n" + "\n" +
                "Numer konta: " + "\n" + accountNumber);


        // Przycisk odswieżenia panelu
        refreshButton = new JButton("Odśwież");
        refreshButton.setSize(80, 30);
        refreshButton.addActionListener(e -> {
            this.dispose();
            new AccountManage(value);

        });


        // Przycisk powrotu do głównego panelu
        backButton = new JButton("Powrót");
        backButton.setSize(80, 50);
        backButton.addActionListener(e -> {

            this.dispose();

            new MainMenu(value);
        });

        // Ikona przypisana do przycisków

        ImageIcon rightArrowIcon = new ImageIcon("Images/right-arrow.png");

        // Przycisk zmiany imienia
        changeName = new JButton();
        changeName.setIcon(rightArrowIcon);
        changeName.setText("Zmień imię");
        changeName.setBorderPainted(false);
        changeName.setContentAreaFilled(false);
        changeName.setFocusPainted(false);
        changeName.setMargin(new Insets(0, 0, 0, 0));
        changeName.addActionListener(e -> new FirstName(value));

        // Przycisk zmiany nazwiska
        changeLastName = new JButton(rightArrowIcon);
        changeLastName.setText("Zmień nazwisko");
        changeLastName.setBorderPainted(false);
        changeLastName.setContentAreaFilled(false);
        changeLastName.setFocusPainted(false);
        changeLastName.setMargin(new Insets(0, 0, 0, 0));
        changeLastName.addActionListener(e -> new LastName(value));

        // Przycisk zmiany adresu email
        changeEmail = new JButton(rightArrowIcon);
        changeEmail.setText("Zmień adres e-mail");
        changeEmail.setBorderPainted(false);
        changeEmail.setContentAreaFilled(false);
        changeEmail.setFocusPainted(false);
        changeEmail.setMargin(new Insets(0, 0, 0, 0));
        changeEmail.addActionListener(e -> new Email(value));

        // Przycisk zmiany hasła
        changePassword = new JButton(rightArrowIcon);
        changePassword.setText("Zmień swoje hasło");
        changePassword.setBorderPainted(false);
        changePassword.setContentAreaFilled(false);
        changePassword.setFocusPainted(false);
        changePassword.setMargin(new Insets(0, 0, 0, 0));
        changePassword.addActionListener(e -> new Password(value));

        // Usunięcie konta
        deleteButton = new JButton(rightArrowIcon);
        deleteButton.setText("Usuń konto");
        deleteButton.setFocusable(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setMargin(new Insets(0, 0, 0, 0));
        deleteButton.addActionListener(e -> {
            new DeleteAccount(value);
            this.dispose();
            new LoginPanel();
        });

        // Pusty przycisk do wyrównania poniższego panelu
        emptyButton = new JButton();
        emptyButton.setBorderPainted(false);
        emptyButton.setContentAreaFilled(false);
        emptyButton.setFocusPainted(false);
        emptyButton.setMargin(new Insets(0, 0, 0, 0));

        // Panel z przyciskami i labelami
        buttonAndLabelPanel = new JPanel();
        buttonAndLabelPanel.setBackground(Color.white);
        buttonAndLabelPanel.setLayout(new GridLayout(6, 1));
        buttonAndLabelPanel.add(changeName);
        buttonAndLabelPanel.add(changeLastName);
        buttonAndLabelPanel.add(changeEmail);
        buttonAndLabelPanel.add(changePassword);
        buttonAndLabelPanel.add(emptyButton);
        buttonAndLabelPanel.add(deleteButton);

        // Północny panel
        northPanel = new JPanel();
        northPanel.setBackground(Color.white);
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        northPanel.add(backButton);

        // Wschodni panel
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(Color.white);
        eastPanel.add(userData);
        eastPanel.add(Box.createRigidArea(new Dimension(0, 5))); // dodajemy sztywną pustą przestrzeń pomiędzy komponentami
        eastPanel.add(refreshButton);

        // Główny panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.add(buttonAndLabelPanel, BorderLayout.WEST);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        this.add(mainPanel);

        this.setSize(450, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
    }
}

