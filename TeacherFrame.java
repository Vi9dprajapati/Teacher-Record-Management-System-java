import javax.swing.*;
import java.sql.*;

public class TeacherFrame extends JFrame {

    JTextField nameField, emailField, mobileField, qualificationField, joiningDateField;
    String department;
    int hodId;

    TeacherFrame(String dept, int hodId) {
        this.department = dept;
        this.hodId = hodId;

        setTitle("Add Teacher - " + dept);
        setSize(450, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 30, 120, 30);
        add(nameLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 70, 120, 30);
        add(emailLabel);

        JLabel mobileLabel = new JLabel("Mobile:");
        mobileLabel.setBounds(40, 110, 120, 30);
        add(mobileLabel);

        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setBounds(40, 150, 120, 30);
        add(qualificationLabel);

        JLabel joiningLabel = new JLabel("Joining Date (YYYY-MM-DD):");
        joiningLabel.setBounds(40, 190, 200, 30);
        add(joiningLabel);

        // TextFields
        nameField = new JTextField();
        nameField.setBounds(200, 30, 180, 30);
        add(nameField);

        emailField = new JTextField();
        emailField.setBounds(200, 70, 180, 30);
        add(emailField);

        mobileField = new JTextField();
        mobileField.setBounds(200, 110, 180, 30);
        add(mobileField);

        qualificationField = new JTextField();
        qualificationField.setBounds(200, 150, 180, 30);
        add(qualificationField);

        joiningDateField = new JTextField();
        joiningDateField.setBounds(200, 190, 180, 30);
        add(joiningDateField);

        JButton addBtn = new JButton("Add Teacher");
        addBtn.setBounds(140, 260, 160, 40);
        add(addBtn);

        addBtn.addActionListener(e -> addTeacher());

        setVisible(true);
    }

    void addTeacher() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO teacher(name,email,mobile,department,qualification,joining_date,created_by_hod) VALUES(?,?,?,?,?,?,?)"
            );

            ps.setString(1, nameField.getText());
            ps.setString(2, emailField.getText());
            ps.setString(3, mobileField.getText());
            ps.setString(4, department);
            ps.setString(5, qualificationField.getText());
            ps.setDate(6, java.sql.Date.valueOf(joiningDateField.getText()));
            ps.setInt(7, hodId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Teacher Added Successfully");

            nameField.setText("");
            emailField.setText("");
            mobileField.setText("");
            qualificationField.setText("");
            joiningDateField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
