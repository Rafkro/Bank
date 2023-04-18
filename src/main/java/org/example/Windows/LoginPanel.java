package org.example.Windows;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.example.GUI.RegisterGUI;

import javax.swing.*;

import java.awt.*;

import static org.example.utility.UserDAOImpl.getEntityManager;


public class LoginPanel extends JFrame {


    JPanel panel;
    JButton loginButton, cancelButton, registerButton, helpButton;
    JTextField idField, passwordField;
    JLabel loginLabel, passwordLabel;


    public LoginPanel() {


        loginLabel = new JLabel("ID");
        loginLabel.setFont(new Font("Arial",Font.BOLD,14));
        loginLabel.setBounds(20, 20, 100, 30);


        idField = new JTextField();
        idField.setBounds(100, 22, 80, 25);
        idField.setFont(new Font("Arial",Font.PLAIN,14));

        passwordLabel = new JLabel("Hasło");
        passwordLabel.setFont(new Font("Arial",Font.BOLD,14));
        passwordLabel.setBounds(20, 60, 100, 30);

        passwordField = new JTextField();
        passwordField.setBounds(100, 62, 80, 25);
        passwordField.setFont(new Font("Arial",Font.PLAIN,14));

        loginButton = new JButton("Zaloguj");
        loginButton.setBounds(30, 130, 80, 30);
        loginButton.setBackground(Color.red);
        loginButton.setForeground(Color.white);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> {

            Query queryId = getEntityManager().createQuery("SELECT customerId FROM Customer WHERE customerId=" + idField.getText());
            Query queryPassword = getEntityManager().createQuery("SELECT loginPassword FROM Customer WHERE loginPassword=" + passwordField.getText());

            try {
                System.out.println("Id podane przez uzytkownika: " + queryId.getSingleResult());
                System.out.println("Podane haslo przez uzytkownika: " + queryPassword.getSingleResult());

                String userIdFromField = idField.getText();
                String queryIdStr = String.valueOf(queryId.getSingleResult());

                String userPasswordFromField = passwordField.getText();
                String queryPasswordStr = String.valueOf(queryPassword.getSingleResult());

                if ((queryIdStr.equals(userIdFromField) && queryPasswordStr.equals(userPasswordFromField))) {
                    System.out.println("Id i haslo jest poprawne");
                    String value = idField.getText();
                    this.dispose();
                    new MainMenu(value);


                } else if (queryIdStr.isEmpty()) {
                    System.out.println("Pole z Id jest puste");
                }

            } catch (NoResultException noResultException) {
                System.out.println("Blad logowania zle haslo lub brak id w bazie");
                JOptionPane.showMessageDialog(null, "Zle dane logowania", "Błąd", JOptionPane.INFORMATION_MESSAGE);
            }

        });


        registerButton = new JButton("Rejestracja");
        registerButton.setBounds(155, 130, 100, 30);
        registerButton.setFocusable(false);
        registerButton.addActionListener(e -> {new RegisterGUI();
        this.dispose();});

        Icon exitIcon = new ImageIcon("Images/Exit.png");

        cancelButton = new JButton();
        cancelButton.setIcon(exitIcon);
        cancelButton.setSize( 25, 25);
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setMargin(new Insets(0, 0, 0, 0));
        cancelButton.setLocation(255,10);
        cancelButton.addActionListener(e -> System.exit(0));

        Icon helpIcon = new ImageIcon("Images/Informacje.png");

        helpButton = new JButton();
        helpButton.setIcon(helpIcon);
        helpButton.setSize( 35, 35);
        helpButton.setBorderPainted(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setFocusPainted(false);
        helpButton.setMargin(new Insets(0,0,0,0));
        helpButton.setLocation(258,185);
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "W celu uzyskania pomocy z logowaniem należy skonstakować się z biurem obsługi kilenta pod numerem: 123-456-789",
                "Pomoc", JOptionPane.INFORMATION_MESSAGE));


        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);

        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.add(idField);
        panel.add(loginLabel);
        panel.add(passwordField);
        panel.add(passwordLabel);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(cancelButton);
        panel.add(helpButton);


        this.add(panel);

        this.setSize(300, 250);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);

    }
}
