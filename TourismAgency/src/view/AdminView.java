package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JScrollPane scl_hotel;
    private JPanel pnl_season;
    private JPanel pnl_pension;
    private JTable tbl_pension;
    private JTable tbl_season;
    private User user;
    private DefaultTableModel t_model_hotel = new DefaultTableModel();
    private DefaultTableModel t_model_pension = new DefaultTableModel();
    private DefaultTableModel t_model_season= new DefaultTableModel();
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private JPopupMenu hotelMenu;
    public AdminView(User user) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();

        this.add(container);
        guiInitialize(1000, 500);
        this.user = user;
        if (user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUsername());
        
        loadHotelTable();
        loadHotelComponent();

        loadPensionTable();
        loadSeasonTable();
        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
    }

    private void loadSeasonTable() {
        Object[] col_season = {"Season ID", "Hotel Name", "Season Start Time", "Season Finish Time"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.t_model_season,this.tbl_season,col_season,seasonList);
    }

    private void loadPensionTable() {
        Object[] col_pension = {"Pension ID", "Hotel Name", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.t_model_pension,this.tbl_pension,col_pension,pensionList);
    }

    private void loadHotelComponent() {
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
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotelMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedHotelId = this.getTableSelectedRow(tbl_hotel,0);
                if (this.hotelManager.delete(selectedHotelId)){
                    Helper.showMessage("done");
                    loadHotelTable();
                }else {
                    Helper.showMessage("error");
                }
            }

        });
    }

    public void loadHotelTable(){
        Object[] col_hotel = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Phone Number", "Hotel Star",
                "Car Parking", "Wifi", "Pool", "Fitness Center", "Concierge", "SPA", "Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length);
        this.createTable(this.t_model_hotel,this.tbl_hotel,col_hotel,hotelList);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
