import javax.swing.*;

public class HodDashboard extends JFrame {

    String department;

    HodDashboard(String dept) {
        this.department = dept;

        setTitle("HOD Dashboard - " + dept);
        setSize(400, 300);
        setLayout(null);

        JButton addTeacher = new JButton("Add Teacher");
        addTeacher.setBounds(100, 60, 200, 40);
        add(addTeacher);

        JButton updateTeacher = new JButton("Update Teacher");
        updateTeacher.setBounds(100, 120, 200, 40);
        add(updateTeacher);

        JButton download = new JButton("Download Dept Teachers");
        download.setBounds(100, 180, 200, 40);
        add(download);

        addTeacher.addActionListener(e -> new TeacherFrame(department));

        setVisible(true);
    }
}
