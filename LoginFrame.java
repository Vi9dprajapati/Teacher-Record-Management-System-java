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

        loginBtn.addActionListener(e -> login());

        setVisible(true);
    }

    // 🔹 Login Method
    void login() {

        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = roleBox.getSelectedItem().toString();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields ❗");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps;

            if (role.equals("Admin")) {

                ps = con.prepareStatement(
                        "SELECT * FROM admin WHERE email=? AND password=?");

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Admin Login Success ✅");
                    new AdminDashboard();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Admin Credentials ❌");
                }

            } else {  // 🔥 HOD LOGIN

                ps = con.prepareStatement(
                        "SELECT hod_id, department FROM hod WHERE email=? AND password=?");

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    int hodId = rs.getInt("hod_id");       // 🔥 IMPORTANT
                    String dept = rs.getString("department");

                    JOptionPane.showMessageDialog(this, "HOD Login Success ✅");

                    new HodDashboard(dept, hodId);   // 🔥 FIXED
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid HOD Credentials ❌");
                }
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error ❌");
        }
    }

    // 🔥 MAIN METHOD
    public static void main(String[] args) {
        new LoginFrame();
    }
}
