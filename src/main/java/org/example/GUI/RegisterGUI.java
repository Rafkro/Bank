package org.example.GUI;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.Windows.LoginPanel;
import org.example.utility.Customer;
import org.example.utility.UserDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import static org.example.utility.UserDAOImpl.getEntityManager;

public class RegisterGUI extends JFrame {


    JPanel textPanel, buttonPanel, mainPanel, helpPanel;
    JButton saveButton, backButton;
    JTextField customerId, customerName, customerSurrname, customerEmail, customerCash, loginPassword;
    JLabel customerIdLabel, customerNameLabel, customerSurrnameLabel, customerEmailLabel, customerCashLabel, loginPasswordLabel;
    Integer idValue;
    JLabel idHelp,nameHelp, surrnameHelp, emailHelp, cashHelp, passwordHelp;


    public RegisterGUI() {

        Customer customer = new Customer();
        EntityManager entityManager = getEntityManager();

        Query idQuery = entityManager.createQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");
        idValue = (Integer) idQuery.getSingleResult();


        customerIdLabel = new JLabel("ID");
        customerId = new JTextField();
        customerId.setText(String.valueOf(idValue + 1));
        customerId.setEditable(false);

        customerName = new JTextField();
        customerNameLabel = new JLabel("Imię: ");

        customerSurrname = new JTextField();
        customerSurrnameLabel = new JLabel("Nazwisko: ");

        customerEmail = new JTextField();
        customerEmailLabel = new JLabel("e-mail: ");

        customerCash = new JTextField();
        customerCashLabel = new JLabel("Wpłata początkowa: ");


        loginPassword = new JTextField();
        loginPasswordLabel = new JLabel("Hasło: ");

        saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> {

            // Elementy przekazane do zapisu w bazie danych



            SecureRandom secureRandom = new SecureRandom();
            BigInteger randomNumber = new BigInteger(130, secureRandom);
            BigInteger accountNumber = new BigInteger(randomNumber.toString().substring(0, 26));

// sprawdzenie czy użytkownik dobrze uzupełnil text field

            String regex = "\\d+"; // wyrażenie regularne dopasowujące tylko liczby
            String passwordStr = loginPassword.getText();

            if (customerName.getText().matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+") && !customerName.getText().isEmpty()) {
                System.out.println("Imię spełnia wymogi");
                if (customerSurrname.getText().matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+") && !customerSurrname.getText().isEmpty()) {
                    System.out.println("Naziwsko spełnia wymogi");
                    if (!customerEmail.getText().isEmpty() && customerEmail.getText().contains("@")) {
                        System.out.println("Email spełnia wymogi");
                    if (Pattern.matches(passwordStr, regex) && loginPassword.getText().length() > 5) {
                        System.out.println("Hasło spełnia wymogi");

                        customer.setFirstName(customerName.getText());
                        customer.setLastName(customerSurrname.getText());
                        customer.setEmail(customerEmail.getText());
                        customer.setCash(Double.parseDouble(customerCash.getText()));
                        customer.setLoginPassword(Integer.parseInt(loginPassword.getText()));
                        customer.setAccountNumber(accountNumber);

                        UserDAOImpl userDAO = new UserDAOImpl();

                        userDAO.addCustomer(customer);

                        System.out.println("Zapisano nowego użytkownika");

                        JOptionPane.showMessageDialog(null, "Utworzono nowego użytkownika", "Panel administratora", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new LoginPanel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hasło zawiera błędne znaki", "Błąd", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Hasło nie spełnia wymogów");
                    }
                } else {
                        JOptionPane.showMessageDialog(null, "Adres e-mail nie może być pusty.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Polę email jest puste");
                }
            }else {
                    JOptionPane.showMessageDialog(null, "Nazwisko zawiera błędne znaki", "Błąd", JOptionPane.ERROR_MESSAGE);
                    System.out.println("W polu nazwisko wprwowadzono błędne znaki");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Imię zawiera błędne znaki","Błąd",JOptionPane.ERROR_MESSAGE);
                System.out.println("Imię zawiera błędne znaki");
            }




        });
        backButton = new JButton("Cofnij");
        backButton.addActionListener(e -> {
            this.dispose();
            new LoginPanel();
        });


        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        textPanel = new JPanel();
        textPanel.setBackground(Color.white);
        textPanel.setLayout(new GridLayout(14, 2));
        textPanel.setSize(400, 400);
        textPanel.add(customerIdLabel);
        textPanel.add(customerId);
        textPanel.add(customerNameLabel);
        textPanel.add(customerName);
        textPanel.add(customerSurrnameLabel);
        textPanel.add(customerSurrname);
        textPanel.add(customerEmailLabel);
        textPanel.add(customerEmail);
        textPanel.add(customerCashLabel);
        textPanel.add(customerCash);
        textPanel.add(loginPasswordLabel);
        textPanel.add(loginPassword);


        Icon helpIcon = new ImageIcon("C:\\Users\\rafal\\OneDrive\\Pulpit\\bank\\src\\main\\resources\\Informacje.png");


        idHelp = new JLabel();
        idHelp.setIcon(helpIcon);
        idHelp.setLocation(10,20);
        idHelp.setSize(30,30);
        idHelp.setToolTipText("Numer Twojego unikalnego identyfikatora ID. Jest on przydzielany automatycznie.");

        nameHelp = new JLabel();
        nameHelp.setIcon(helpIcon);
        nameHelp.setLocation(10,20);
        nameHelp.setSize(30,30);
        nameHelp.setToolTipText("W tym oknie należy wpisać swoję imię.");

        surrnameHelp = new JLabel();
        surrnameHelp.setIcon(helpIcon);
        surrnameHelp.setSize(30,30);
        surrnameHelp.setToolTipText("W tym oknie należy wpisać swoję nazwisko.");

        emailHelp = new JLabel();
        emailHelp.setIcon(helpIcon);
        emailHelp.setSize(30,30);
        emailHelp.setToolTipText("W tym oknie należy wpisać swój adres e-mail.");

        cashHelp = new JLabel();
        cashHelp.setIcon(helpIcon);
        cashHelp.setSize(30,30);
        cashHelp.setToolTipText("W tym oknie należy wpisać kwotę pierwszej wpłaty na konto, w przypadku braku wpłaty należy wpisać 0.");

        passwordHelp = new JLabel();
        passwordHelp.setIcon(helpIcon);
        passwordHelp.setSize(30,30);
        passwordHelp.setToolTipText("W tym oknie należy wpisać swoję hasło. Powinno ono się składać tylko z cyfr. Hasło powinno zawierać przynajmniej 4 znaki");


        helpPanel = new JPanel();
        helpPanel.setLayout(new GridLayout(6,1,0,-20));
        helpPanel.setBackground(Color.white);
        helpPanel.add(idHelp);
        helpPanel.add(nameHelp);
        helpPanel.add(surrnameHelp);
        helpPanel.add(emailHelp);
        helpPanel.add(cashHelp);
        helpPanel.add(passwordHelp);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(helpPanel,BorderLayout.EAST);

        this.add(mainPanel);


        this.setSize(300, 350);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);

    }

}
