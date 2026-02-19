import javax.swing.*;
import java.sql.*;

public class CreateHodFrame extends JFrame {

    JTextField usernameField, emailField, departmentField;
    JPasswordField passwordField;

    CreateHodFrame() {

        setTitle("Create HOD");
        setSize(400, 350);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 30, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 150, 30);
        add(usernameField);

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

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(50, 180, 100, 30);
        add(deptLabel);

        departmentField = new JTextField();
        departmentField.setBounds(150, 180, 150, 30);
        add(departmentField);

        JButton createBtn = new JButton("Create");
        createBtn.setBounds(130, 230, 120, 40);
        add(createBtn);

        createBtn.addActionListener(e -> createHod());

        setVisible(true);
    }

    void createHod() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO hod(username,email,password,department) VALUES(?,?,?,?)"
            );

            ps.setString(1, usernameField.getText());
            ps.setString(2, emailField.getText());
            ps.setString(3, new String(passwordField.getPassword()));
            ps.setString(4, departmentField.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "HOD Created Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
