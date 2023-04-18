package org.example.Windows;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.utility.Transaction;
import org.example.utility.UserDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import static org.example.utility.UserDAOImpl.getEntityManager;

public class MainMenu extends JFrame {


    JPanel mainPanel, northPanel, middlePanel, southPanel, northPanel2, southPanel2;
    JButton  southButton1, southButton2, southButton3, southButton4;
    JLabel accontFunctionLabel, cashLabel, historyLabel, bankIconLabel;

    JTextArea leftText, rightText;



    public MainMenu(String value) {

        EntityManager entityManager = getEntityManager();
        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        // Przycisk otwierający menu edycji profilu
        accontFunctionLabel = new JLabel("Profil");
        accontFunctionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        accontFunctionLabel.setForeground(Color.red);
        accontFunctionLabel.setFocusable(true);
        accontFunctionLabel.setBounds(150, 50, 200, 30);
        accontFunctionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                offFrame();
                new AccountManage(value);
            }
        });

        // Przycisk otwierajacy menu płatności
        cashLabel = new JLabel("Płatności");
        cashLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cashLabel.setForeground(Color.red);
        cashLabel.setFocusable(true);
        cashLabel.setBounds(150, 100, 100, 30);
        cashLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                offFrame(); // Zamykanie okna
                new CashOption(value);
            }
        });

        // Przycisk otwierający okno z historią płatności
        historyLabel = new JLabel("Historia");
        historyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        historyLabel.setForeground(Color.red);
        historyLabel.setFocusable(true);
        historyLabel.setBounds(250, 100, 80, 30);
        historyLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

               transaction.setCustomerId(Integer.parseInt(value));
                userDAO.printTransaction(transaction);
            }
        });

        // Pólnocny panel pomocniczy
        northPanel = new JPanel();
        northPanel.setBackground(Color.white);
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        northPanel.add(accontFunctionLabel);
        northPanel.add(cashLabel);
        northPanel.add(historyLabel);

        // Label z logiem banku
        Icon bankIcon = new ImageIcon("Images/bankIcon.png");
        bankIconLabel = new JLabel();
        bankIconLabel.setIcon(bankIcon);
        bankIconLabel.setFont(new Font("Ink Free", Font.ITALIC + Font.BOLD, 20));
        bankIconLabel.setText("Bank dużych możliwości !");

        // Panel północy główny
        northPanel2 = new JPanel();
        northPanel2.setBackground(Color.white);
        northPanel2.setLayout(new BorderLayout());
        northPanel2.add(northPanel, BorderLayout.SOUTH);
        northPanel2.add(bankIconLabel, BorderLayout.CENTER);

        // Zapytanie do bazy danych (uzupełnienie danych w lewym tekscie)
        Query textQuery = entityManager.createQuery("SELECT accountNumber FROM Customer WHERE customerId=:id");
        textQuery.setParameter("id", value);
        String accountNumber = String.valueOf(textQuery.getSingleResult());

        // Lewy tekst (numer konta)
        leftText = new JTextArea("ID Użytkownika:" + value + "\n" + "Numer konta: " + " **** " + accountNumber.substring(22, 26));
        leftText.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        leftText.setEditable(false);
        leftText.setBounds(0, 10, 180, 60);
        leftText.setBackground(Color.white);

        // Zapytanie do bazy danych o stan konta (prawy tekst)
        Query cashQuery = entityManager.createQuery("SELECT cash FROM Customer WHERE customerId=:id");
        cashQuery.setParameter("id", value);
        String cash = String.valueOf(cashQuery.getSingleResult());

        // Wyświetlenie stanu konta
        rightText = new JTextArea("Stan konta \n" + cash + " zł");
        rightText.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        rightText.setEditable(false);
        rightText.setBounds(340, 10, 90, 60);
        rightText.setBackground(Color.white);

        // Środkowy panel
        middlePanel = new JPanel();
        middlePanel.setBackground(Color.white);
        middlePanel.setLayout(null);
        middlePanel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
        middlePanel.add(leftText);
        middlePanel.add(rightText);

        // Południowy 1 przycisk
        Icon walletIcon = new ImageIcon("Images/wallet.png");

        southButton1 = new JButton(walletIcon);
        southButton1.setBorderPainted(false);
        southButton1.setContentAreaFilled(false);
        southButton1.setFocusPainted(false);
        southButton1.setMargin(new Insets(0, 0, 0, 0));
        southButton1.setBounds(30, 70, 45, 45);
        southButton1.addActionListener(e -> {
            this.dispose();
            new CashOption(value);
        });

        // Południowy 2 przycisk
        Icon offerIcon = new ImageIcon("Images/gift.png");

        southButton2 = new JButton(offerIcon);
        southButton2.setSize(50, 50);
        southButton2.setBorderPainted(false);
        southButton2.setContentAreaFilled(false);
        southButton2.setFocusPainted(false);
        southButton2.setMargin(new Insets(0, 0, 0, 0));
        southButton2.setBounds(120, 70, 45, 45);
        southButton2.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://tiny.pl/wvp8q"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        // Południowy 3 przycisk
        Icon supportIcon = new ImageIcon("Images/support.png");

        southButton3 = new JButton(supportIcon);
        southButton3.setSize(50, 50);
        southButton3.setBorderPainted(false);
        southButton3.setContentAreaFilled(false);
        southButton3.setFocusPainted(false);
        southButton3.setMargin(new Insets(0, 0, 0, 0));
        southButton3.setBounds(210, 70, 45, 45);
        southButton3.addActionListener(e -> JOptionPane.showMessageDialog(null, "Jeżeli potrzebujesz pomocy z naszą aplikacją proszę skontaktuj się z naszym działem pomocy technicznej" +
                " pod numerem 123 - 456 - 789. Infolinia czynna jest całą dobę", "Kontakt", JOptionPane.INFORMATION_MESSAGE));

        // Południowy 4 przycisk
        Icon exitIcon = new ImageIcon("Images/shutdown.png");

        southButton4 = new JButton(exitIcon);
        southButton4.setSize(50, 50);
        southButton4.setBorderPainted(false);
        southButton4.setContentAreaFilled(false);
        southButton4.setFocusPainted(false);
        southButton4.setMargin(new Insets(0, 0, 0, 0));
        southButton4.setBounds(300, 70, 100, 100);
        southButton4.addActionListener(e -> this.dispose());

        // Południowy panel zawierający przyciski(pomocniczy)
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 20));
        southPanel.setBackground(Color.GRAY);
        southPanel.add(southButton1);
        southPanel.add(southButton2);
        southPanel.add(southButton3);
        southPanel.add(southButton4);



        // Południowy panel (główny)
        southPanel2 = new JPanel();
        southPanel2.setLayout(new BorderLayout());
        southPanel2.setBackground(Color.white);
        southPanel2.add(southPanel, BorderLayout.SOUTH);

        // Główny panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new GridLayout(3, 1));

        mainPanel.add(northPanel2);
        mainPanel.add(middlePanel);
        mainPanel.add(southPanel2);


        this.add(mainPanel);

        this.setSize(450, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);

    }

    public void offFrame() {
        this.dispose();
    }
}

