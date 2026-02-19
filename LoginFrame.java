import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    JTextField emailField;
    JPasswordField passwordField;
    JButton loginBtn;
    JComboBox<String> roleBox;

    // 🔹 Constructor
    LoginFrame() {

        setTitle("Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setBounds(50, 30, 100, 30);
        add(roleLabel);

        String[] roles = {"Admin", "HOD"};
        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(150, 30, 150, 30);
        add(roleBox);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 80, 150, 30);
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 130, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 150, 30);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(140, 190, 100, 35);
        add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        setVisible(true);
    }

    // 🔹 Login Method
    void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps;

            if (role.equals("Admin")) {
                ps = con.prepareStatement(
                        "SELECT * FROM admin WHERE email=? AND password=?");
            } else {
                ps = con.prepareStatement(
                        "SELECT * FROM hod WHERE email=? AND password=?");
            }

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Success ✅");

                if (role.equals("Admin")) {
                    new AdminDashboard();
                } else {
                    String dept = rs.getString("department");
                    new HodDashboard(dept);
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials ❌");
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 🔥 MAIN METHOD (IMPORTANT)
    public static void main(String[] args) {
        new LoginFrame();
    }
}
