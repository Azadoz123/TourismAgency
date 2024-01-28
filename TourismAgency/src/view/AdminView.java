package view;

import business.HotelManager;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private DefaultTableModel t_model_hotel = new DefaultTableModel();
    private HotelManager hotelManager;
    private JPopupMenu hotelMenu;
    public AdminView(User user) {
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialize(1000, 500);
        this.user = user;
        if (user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUsername());

        Object[] col_hotel = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Phone Number", "Hotel Star",
                "Car Parking", "Wifi", "Pool", "Fitness Center", "Concierge", "SPA", "Room Service"};
        ArrayList<Hotel> hotelArrayList = hotelManager.findAll();
        this.t_model_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel: hotelArrayList){
            Object[] obj = { hotel.getId(), hotel.getName(), hotel.getAddress(),hotel.getPhoneNumber(),hotel.getStar(),hotel.isPool(),
                    hotel.isCar_parking(),hotel.isWifi(),hotel.isPool(),hotel.isFitness_center(),hotel.isConcierge(),
                    hotel.isSpa(),hotel.isRoom_service()};
            t_model_hotel.addRow(obj);
        };

        this.tbl_hotel.setModel(t_model_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.setEnabled(false);
        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Add").addActionListener(e -> {
            HotelView hotelView = new HotelView(null);
        });

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
    }
}
