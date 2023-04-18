package org.example.utility;

import jakarta.persistence.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserDAOImpl implements UserDAO {


    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        return entityManagerFactory.createEntityManager();
    }


    @Override
    public void addCustomer(Customer customer) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void uptadeName(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String name = JOptionPane.showInputDialog("Podaj nowe imię");

        transaction.begin();
        // Pobieranie klienta z bazy
        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

        // Zmodyfikuj wartość w obiekcie encji
        customerToUpdate.setFirstName(name);

        // Wykonaj zapytanie, aby zmienić wartośći w bazie danych
        Query query = entityManager.createQuery("UPDATE Customer SET firstName=:newFirstName WHERE customerId=:customerId");
        query.setParameter("newFirstName", name);
        query.setParameter("customerId", customerToUpdate.getCustomerId());
        query.executeUpdate();

        // Zapisz zmiany w bazie danych
        entityManager.merge(customerToUpdate);

        transaction.commit();

        entityManager.close();

        System.out.println("Zmieniono imię na: " + name);
    }

    @Override
    public void uptadeLastName(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String lastName = JOptionPane.showInputDialog("Podaj nowe nazwisko");

        transaction.begin();
        // Pobieranie klienta z bazy
        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

        // Zmodyfikuj wartość w obiekcie encji
        customerToUpdate.setLastName(lastName);

        // Wykonaj zapytanie, aby zmienić wartośći w bazie danych
        Query query = entityManager.createQuery("UPDATE Customer SET lastName=:newLastName WHERE customerId=:customerId");
        query.setParameter("newLastName", lastName);
        query.setParameter("customerId", customerToUpdate.getCustomerId());
        query.executeUpdate();

        // Zapisz zmiany w bazie danych
        entityManager.merge(customerToUpdate);

        transaction.commit();

        entityManager.close();

        System.out.println("Zmieniono nazwisko na: " + lastName);
    }

    @Override
    public void uptadeEmail(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String email = JOptionPane.showInputDialog("Podaj nowy adres e-mail");

        transaction.begin();
        // Pobieranie klienta z bazy
        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

        // Zmodyfikuj wartość w obiekcie encji
        customerToUpdate.setEmail(email);

        // Wykonaj zapytanie, aby zmienić wartośći w bazie danych
        Query query = entityManager.createQuery("UPDATE Customer SET email=:newEmail WHERE customerId=:customerId");
        query.setParameter("newEmail", email);
        query.setParameter("customerId", customerToUpdate.getCustomerId());
        query.executeUpdate();


        // Zapisz zmiany w bazie danych
        entityManager.merge(customerToUpdate);

        transaction.commit();

        entityManager.close();

        System.out.println("Zmieniono adres e-mail na: " + email);
    }

    @Override
    public void uptadePassword(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String password = JOptionPane.showInputDialog("Podaj nowe nazwisko");

        transaction.begin();
        // Pobieranie klienta z bazy
        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

        // Zmodyfikuj wartość w obiekcie encji
        customerToUpdate.setLoginPassword(Integer.parseInt(password));

        // Wykonaj zapytanie, aby zmienić wartośći w bazie danych
        Query query = entityManager.createQuery("UPDATE Customer SET email=:newPassword WHERE customerId=:customerId");
        query.setParameter("newPassword", password);
        query.setParameter("customerId", customerToUpdate.getCustomerId());
        query.executeUpdate();

        // Zapisz zmiany w bazie danych
        entityManager.merge(customerToUpdate);

        transaction.commit();

        entityManager.close();

        System.out.println("Zmieniono adres e-mail na: " + password);
    }

    @Override
    public void deleteCustomer(Customer customer) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć swoje konto?", "Usuwanie", JOptionPane.YES_NO_CANCEL_OPTION);

        if (answer == 0) {
            String password = JOptionPane.showInputDialog("Wprowadz hasło");
            transaction.begin();

            entityManager.find(Customer.class, customer.getCustomerId());

            customer.setLoginPassword(Integer.parseInt(password));
            customer = entityManager.merge(customer); // dołącz encję do kontekstu trwałości

            Query query = entityManager.createQuery("SELECT loginPassword FROM Customer where customerId=:id");
            query.setParameter("id", customer.getCustomerId());

            String dbPassword = String.valueOf(query.getSingleResult());


            if (password.equals(dbPassword)) {

                entityManager.remove(customer);

                transaction.commit();

                entityManager.close();

            } else {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłowe hasło użytkownika", "Błąd", JOptionPane.ERROR_MESSAGE);
                entityManager.close();

            }

        }


    }


    @Override
    public void depositMoney(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Transaction transaction = new Transaction();

        String depositCash = JOptionPane.showInputDialog("Ile pieniędzy chcesz wpłacić?");
        double userDeposit = Double.parseDouble(depositCash);

        entityTransaction.begin();

        // Pobieranie klienta z bazy

        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

        // Zmodyfikuj wartość w obiekcie encji

        double userMoney = customerToUpdate.getCash();
        userMoney += userDeposit + customer.getCash();
        customerToUpdate.setCash(userMoney);


        transaction.setCustomerId(customer.getCustomerId());
        transaction.setTransactionAmount(userDeposit);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionKind("Wpłata");

        entityManager.persist(transaction);

        // Wykonaj zapytanie, aby zmienić wartośći w bazie danych
        Query query = entityManager.createQuery("UPDATE Customer SET cash=:newCash WHERE customerId=:customerId");
        query.setParameter("newCash", userMoney);
        query.setParameter("customerId", customerToUpdate.getCustomerId());
        query.executeUpdate();

        // Zapisz zmiany w bazie danych

        entityManager.merge(customerToUpdate);
        entityTransaction.commit();

        entityManager.close();

        System.out.println("Wplata uzytkownika: " + depositCash);
    }

    @Override
    public void withdrawMoney(Customer customer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Transaction transaction = new Transaction();


        // Zapytanie do użytkownika i pobranie warości gotówki do wypłaty (zmniejszenie stanu konta)
        String withdrawCash = JOptionPane.showInputDialog("Ile pieniędzy chcesz wypłacić ?");
        double userWithdraw = Double.parseDouble(withdrawCash);

        // Rozpoczęcie transferu
        entityTransaction.begin();

        // Wywołanie użytkownika

        Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());


        // Modyfikacja encji

        double userDeposit = customerToUpdate.getCash();
        userDeposit -= userWithdraw + customer.getCash();
        customerToUpdate.setCash(userDeposit);

        transaction.setCustomerId(customer.getCustomerId());
        transaction.setTransactionAmount(-userWithdraw);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionKind("Wypłata");

        entityManager.persist(transaction);

        // Zapytanie query, aby zmienić określoną wartość w bazie danych

        Query query = entityManager.createQuery("UPDATE Customer SET cash=:newCash WHERE customerId=:id");
        query.setParameter("newCash", userDeposit);
        query.setParameter("id", customerToUpdate.getCustomerId());
        query.executeUpdate();

        // Zapisz w bazie danych

        entityManager.merge(customerToUpdate);
        entityTransaction.commit();

        entityManager.close();

        System.out.println("Użytkownik wypłacił: " + userWithdraw);
        System.out.println("Depozyt użytkownika: " + userDeposit);


    }


    @Override
    public void transferMoney(Customer customer) {



        JTextField name, lastName, amount, accountNumber;
        JLabel nameLabel, lastNameLabel, amountLabel, accountNameLabel;
        JButton saveButton, backButton;
        JPanel mainPanel, buttonPanel, infoPanel;

        saveButton = new JButton("Zapisz");
        saveButton.setSize(80, 40);

        backButton = new JButton("Powrót");
        backButton.setSize(80, 40);

        name = new JTextField();
        nameLabel = new JLabel("Imię:");

        lastName = new JTextField();
        lastNameLabel = new JLabel("Nazwisko:");

        amount = new JTextField();
        amountLabel = new JLabel("Kwota:");

        accountNumber = new JTextField();
        accountNameLabel = new JLabel("Numer konta:");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 4, 5, 5));
        infoPanel.add(buttonPanel);
        infoPanel.add(nameLabel);
        infoPanel.add(name);
        infoPanel.add(lastNameLabel);
        infoPanel.add(lastName);
        infoPanel.add(amountLabel);
        infoPanel.add(amount);
        infoPanel.add(accountNameLabel);
        infoPanel.add(accountNumber);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);



        // Ikona
        Icon transferIcon = new ImageIcon("C:\\Users\\rafal\\OneDrive\\Pulpit\\bank\\src\\main\\resources\\symbols.png");
        // Opcje dialogowe
        String[] options = {"OK", "Cancel"};
        // Pobieranie rezultatu do IF
        int result = JOptionPane.showOptionDialog(null, mainPanel, "Przelew", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, transferIcon, options, options[0]);
        // Pobieranie ilości gotówki
        String transferCash = amount.getText();

        double userTransfer = Double.parseDouble(transferCash);

        if (result == JOptionPane.OK_OPTION) {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            Transaction transaction = new Transaction();
            //Rozpoczęcie połączenia z bazą danych
            entityTransaction.begin();

            // Wywołanie użytkownika
            Customer customerToUpdate = entityManager.find(Customer.class, customer.getCustomerId());

            // Zapis do tabeli TRANSACTIONS
            transaction.setCustomerId(customer.getCustomerId());
            transaction.setTransactionAmount(-userTransfer);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setTransactionKind("Przelew");

            entityManager.persist(transaction);

            // Modyfikacja encji
            double userDeposit = customerToUpdate.getCash();
            userDeposit -= userTransfer + customer.getCash();
            customerToUpdate.setCash(userDeposit);

            // Zapytanie query, aby zmienić określoną wartość w bazie danych

            Query query = entityManager.createQuery("UPDATE Customer SET cash=:newCash WHERE customerId=:id");
            query.setParameter("newCash", userDeposit);
            query.setParameter("id", customerToUpdate.getCustomerId());
            query.executeUpdate();


            // Zapisz w bazie danych

            entityManager.merge(customerToUpdate);
            entityTransaction.commit();

            entityManager.close();

            System.out.println("Nacisięto przycisk ok");
        } else if (result == JOptionPane.CANCEL_OPTION) {

            System.out.println("Naciśnięto przycisk cancel");

        }
    }

    @Override
    public void printTransaction(Transaction transaction) {


        EntityManager entityManager = getEntityManager();


        JTextArea textArea;
        JPanel panel;

        // Zapytanie HQL do bazy
        Query textQuery = entityManager.createQuery("SELECT t FROM Transaction t WHERE t.customerId=:id");
        textQuery.setParameter("id",transaction.getCustomerId());

        // Pobranie i przypisanie wyników do listy
        List<Transaction> transactions = textQuery.getResultList();
        // Sposób wyświetlania listy
        String result = transactions.stream().map(Objects::toString).collect(Collectors.joining());

        System.out.println(result);

        textArea = new JTextArea(result);


        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.add(textArea);


        String[] options = {"OK"};
        JOptionPane.showOptionDialog(null, panel, "Przelew", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    }


}
