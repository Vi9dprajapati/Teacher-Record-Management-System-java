import javax.swing.*;
import java.sql.*;
import java.io.*;

public class HodDashboard extends JFrame {

    String department;
    int hodId;

    HodDashboard(String dept, int hodId) {
        this.department = dept;
        this.hodId = hodId;

        setTitle("HOD Dashboard - " + dept);
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== Buttons =====
        JButton addTeacher = new JButton("Add Teacher");
        addTeacher.setBounds(100, 60, 200, 40);
        add(addTeacher);

        JButton updateTeacher = new JButton("Update Teacher");
        updateTeacher.setBounds(100, 120, 200, 40);
        add(updateTeacher);

        JButton download = new JButton("Download Dept Teachers");
        download.setBounds(100, 180, 200, 40);
        add(download);

        // ===== Actions =====
        addTeacher.addActionListener(e ->
                new TeacherFrame(department, hodId)
        );

        updateTeacher.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Update Feature Coming Soon")
        );

        download.addActionListener(e -> downloadTeachers());

        setVisible(true);
    }

    // 🔥 DOWNLOAD METHOD
    void downloadTeachers() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT name, email, mobile, qualification, joining_date " +
                    "FROM teacher WHERE created_by_hod=?"
            );

            ps.setInt(1, hodId);

            ResultSet rs = ps.executeQuery();

            // File name example: ComputerScience_teachers.csv
            String fileName = department.replaceAll(" ", "_") + "_teachers.csv";
            FileWriter writer = new FileWriter(fileName);

            // CSV Header
            writer.append("Name,Email,Mobile,Qualification,Joining Date\n");

            while (rs.next()) {

                writer.append(rs.getString("name")).append(",");
                writer.append(rs.getString("email")).append(",");
                writer.append(rs.getString("mobile")).append(",");
                writer.append(rs.getString("qualification")).append(",");
                writer.append(rs.getString("joining_date")).append("\n");
            }

            writer.flush();
            writer.close();
            con.close();

            JOptionPane.showMessageDialog(this,
                    "File Downloaded Successfully ✅\nSaved as: " + fileName);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Download Failed ❌");
        }
    }
}
