package ro.tuc.tp.presentation;


import ro.tuc.tp.business.DeliveryService;
import ro.tuc.tp.business.UserData;
import ro.tuc.tp.data.Serializator;
import ro.tuc.tp.data.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that implements the login interface
 * @author Petricele Mihaela
 */
public class LoginGUI extends JFrame implements ActionListener, Serializable {
    private static final JTextField username = new JTextField(15);
    private final JPasswordField parola = new JPasswordField(15);

    private final JButton login = new JButton("LOGIN");
    private final JButton reset = new JButton("RESET");
    private final JButton register = new JButton("REGISTER");

    public final JLabel usernameText = new JLabel("Username ");
    public final JLabel parolaText = new JLabel("Password ");
    public final JLabel titlu = new JLabel("PAGINA DE LOGIN");
    public final JLabel mesaj = new JLabel();

    JComboBox<String> facenameCombo = new JComboBox<String>();

    HashMap<String, String> loginInfo = new HashMap<>();


    Serializator serializator;
    DeliveryService deliveryService;
    UserData userData;

    public static final Color purple = new Color(210, 255, 255);
    public static final Color purple1 = new Color(255, 255, 255);


    public LoginGUI(DeliveryService deliveryService, UserData userData) {
        this.deliveryService = deliveryService;
        this.userData = userData;
        JFrame frame = new JFrame("Pagina de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Serializator.serializeClient(UserData.getClient());
                Serializator.serializeProduct(deliveryService.getItemCollection());
            }
        };
        frame.addWindowListener(listener);
        frame.setSize(550, 300);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel.add(titlu);
        titlu.setFont(new Font("Serif", Font.BOLD, 20));
        panel.setMaximumSize(new Dimension(550, 55));
        panel.setBackground(purple);

        panel1.add(usernameText);
        panel1.add(username);
        panel1.setBackground(purple);
        panel1.setMaximumSize(new Dimension(550, 30));

        panel2.add(parolaText);
        panel2.add(parola);
        panel2.setBackground(purple);
        panel2.setMaximumSize(new Dimension(550, 40));

        panel3.add(login);
        login.addActionListener(this);
        login.setFocusable(false);
        panel3.add(reset);
        reset.addActionListener(this);
        reset.setFocusable(false);
        panel3.add(register);
        register.addActionListener(this);
        //register.setFocusable(false);
        panel3.setBackground(purple);
        panel3.setMaximumSize(new Dimension(550, 50));

        panel4.add(facenameCombo);
        facenameCombo.addItem("client");
        facenameCombo.addItem("employee");
        panel4.add(mesaj);
        panel4.setBackground(purple);
        panel4.setMaximumSize(new Dimension(550, 100));

        JPanel loginWindow = new JPanel();
        loginWindow.add(panel);
        loginWindow.add(panel1);
        loginWindow.add(panel2);
        loginWindow.add(panel3);
        loginWindow.add(panel4);
        loginWindow.setLayout(new BoxLayout(loginWindow, BoxLayout.Y_AXIS));
        frame.setContentPane(loginWindow);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            username.setText("");
            parola.setText("");
        }
        if (e.getSource() == login) {
            String userID = username.getText();
            String password = String.valueOf(parola.getPassword());
            String type = String.valueOf(facenameCombo.getSelectedItem());

            if (UserData.getAdmin().containsKey(userID)) {
                if (UserData.getAdmin().get(userID).equals(password)) {
                    if (UserData.getAdmin().get(userID).equals("admin")) {
                        mesaj.setForeground(Color.green);
                        mesaj.setText("Login successful as Admin!");
                        AdministratorGUI administratorGUI = new AdministratorGUI(deliveryService);
                    }
                }
            } else {
                if (UserData.getClient().containsKey(userID)) {
                    if (UserData.getClient().get(userID).equals(password)) {
                        if (type.equals("client")) {
                            mesaj.setForeground(Color.green);
                            mesaj.setText("Login successful as a Client!");
                            User client = new User(userID,password);
                            ClientGUI clientGUI = new ClientGUI(deliveryService, userData,client);
                        }
                    }
                } else {
                    if (UserData.getEmployee().containsKey(userID)) {
                        if (UserData.getEmployee().get(userID).equals(password)) {
                            if (type.equals("employee")) {
                                mesaj.setForeground(Color.green);
                                mesaj.setText("Login successful as a Employee!");
                                EmployeeGUI employeeGUI = new EmployeeGUI(deliveryService, userData);
                            }
                        }
                    } else {
                        mesaj.setForeground(Color.red);
                        mesaj.setText("Login data don't match!");
                    }
                }

            }
        }
        if (e.getSource() == register) {
            String userIdRegister = username.getText();
            String passwordRegister = String.valueOf(parola.getPassword());
            String typeRegister = String.valueOf(facenameCombo.getSelectedItem());
            if (typeRegister.equals("client")) {
                if (UserData.getClient().containsKey(userIdRegister)) {
                    mesaj.setForeground(Color.red);
                    mesaj.setText("User already exists!");
                } else {
                    UserData.addClient(userIdRegister, passwordRegister);
                    mesaj.setForeground(Color.green);
                    mesaj.setText("Register successful as Client!");
                }
            } else {
                if (typeRegister.equals("employee")) {
                    if (UserData.getEmployee().containsKey(userIdRegister)) {
                        mesaj.setForeground(Color.red);
                        mesaj.setText("User already exists!");
                    } else {
                        UserData.addEmployee(userIdRegister, passwordRegister);
                        mesaj.setForeground(Color.green);
                        mesaj.setText("Register successful as Employee!");
                    }
                }
            }
        }
}

    public static JTextField getUsername() {
        return username;
    }

    public JPasswordField getParola() {
        return parola;
    }

    public JComboBox<String> getFacenameCombo() {
        return facenameCombo;
    }

    public UserData getUser() {
        return userData;
    }
}
