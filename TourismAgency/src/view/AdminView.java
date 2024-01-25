package view;

import entity.User;

import javax.swing.*;

public class AdminView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tabbedPane1;
    private JButton btn_logout;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JScrollPane scl_hotel;
    private User user;
    public AdminView(User user)  {
        this.add(container);
        guiInitialize(1000,500);
        this.user = user;
        if (user == null){
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUsername());
    }
}
