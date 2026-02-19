import javax.swing.*;
import java.sql.*;

public class TeacherFrame extends JFrame {

    JTextField nameField, emailField, mobileField;
    String department;

    TeacherFrame(String dept) {
        this.department = dept;

        setTitle("Add Teacher");
        setSize(400, 300);
        setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(100, 30, 200, 30);
        add(nameField);

        emailField = new JTextField();
        emailField.setBounds(100, 70, 200, 30);
        add(emailField);

        mobileField = new JTextField();
        mobileField.setBounds(100, 110, 200, 30);
        add(mobileField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(120, 160, 150, 40);
        add(addBtn);

        addBtn.addActionListener(e -> addTeacher());

        setVisible(true);
    }

    void addTeacher() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO teacher(name,email,mobile,department) VALUES(?,?,?,?)"
            );

            ps.setString(1, nameField.getText());
            ps.setString(2, emailField.getText());
            ps.setString(3, mobileField.getText());
            ps.setString(4, department);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Teacher Added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
