import javax.swing.*;

public class AdminDashboard extends JFrame {

    AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLayout(null);

        JButton createHod = new JButton("Create HOD");
        createHod.setBounds(100, 50, 200, 40);
        add(createHod);

        JButton deleteHod = new JButton("Delete HOD");
        deleteHod.setBounds(100, 110, 200, 40);
        add(deleteHod);

        JButton download = new JButton("Download All Teachers");
        download.setBounds(100, 170, 200, 40);
        add(download);

        createHod.addActionListener(e -> new CreateHodFrame());

        setVisible(true);
    }
}
