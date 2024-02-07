package view;

import business.*;
import core.Helper;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_hotel;
    private JScrollPane scl_hotel;
    private JPanel pnl_season;
    private JPanel pnl_pension;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JTable tbl_room;
    private JButton btn_add_hotel;
    private JPanel pnl_room;
    private JButton btn_add_room;
    private JTable tbl_reservation;
    private JPanel pnl_reservation;
    private JTextField fld_src_hotel_name;
    private JTextField fld_src_hotel_city;
    private JTextField fld_search_count_of_child;
    private JTextField fld_count_of_adult;
    private JButton btn_search_room;
    private JFormattedTextField fld_s_check_in;
    private JFormattedTextField fld_s_check_out;
    private User user;
    private DefaultTableModel t_model_hotel = new DefaultTableModel();
    private DefaultTableModel t_model_pension = new DefaultTableModel();
    private DefaultTableModel t_model_season= new DefaultTableModel();
    private DefaultTableModel t_model_room= new DefaultTableModel();
    private DefaultTableModel t_model_reservation= new DefaultTableModel();
    private Object[] col_room;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private JPopupMenu hotelMenu;
    private JPopupMenu reservation_menu;
    private JPopupMenu room_menu;
    public EmployeeView(User user) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();

        this.add(container);
        guiInitialize(1200, 500);
        this.user = user;
        if (user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUsername());
        
        loadHotelTable();
        loadHotelComponent();

        loadPensionTable();
        loadSeasonTable();

        loadRoomTable(null);
        loadRoomComponent();
//        loadRoomFilter();

        loadReservationTable();
        loadReservationComponent();

  //      pnl_pension.removeAll();
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                dispose();
            }
        });
    }

    private void loadReservationComponent() {
        tableRowSelected(tbl_reservation);
        btn_search_room.addActionListener(e -> {
            ArrayList<Room> roomList = this.roomManager.SearchForReservation(
                    fld_src_hotel_name.getText(),
                    fld_src_hotel_city.getText(),
                    fld_s_check_in.getText(),
                    fld_s_check_out.getText(),
                    fld_search_count_of_child.getText(),
                    fld_count_of_adult.getText()
            );
            ArrayList<Object[]> roomBookingRow = this.roomManager.getForTable(this.col_room.length,roomList);
            loadRoomTable(roomBookingRow);
        });
        this.reservation_menu = new JPopupMenu();
        //delete reservation
        this.reservation_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedReservationId = this.getTableSelectedRow(tbl_reservation,0);
                int roomIdForSelectedReservation = reservationManager.getById(selectedReservationId).getRoom_id();
                if (this.reservationManager.delete(selectedReservationId)){
                    Helper.showMessage("done");
                    loadReservationTable();
                    Room updateRoom = roomManager.getById(roomIdForSelectedReservation);
                    updateRoom.setStock(roomManager.getById(roomIdForSelectedReservation).getStock() + 1);
                    roomManager.update(updateRoom);
                    loadRoomTable(null);
                }else {
                    Helper.showMessage("error");
                }
            }
        });
        //update reservation
        this.reservation_menu.add("Update").addActionListener(e -> {
            int selectedReservationId = this.getTableSelectedRow(tbl_reservation,0);
            Reservation reservation =this.reservationManager.getById(selectedReservationId);
        //    System.out.println(ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate()));
            BookRoomView bookRoomView = new BookRoomView(reservation,String.valueOf(reservation.getCheckInDate()) ,String.valueOf(reservation.getCheckOutDate()),
                    String.valueOf(reservation.getNumberOfChild()),String.valueOf(reservation.getNumberOfAdult()));
            //      BrandView brandView = new BrandView(this.brandManager.getById(selectedBrandId));
            bookRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }

    private void loadReservationTable() {
        Object[] col_reservation = {"ID", "Room Id", "Check In Date", "Check Out Date", "Total Price", "Guest Count", "Guest Name", "Guest Id", "Guest Mail", "Guest Phone Number"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        this.createTable(this.t_model_reservation,this.tbl_reservation,col_reservation,reservationList);
    }
    /*public void loadRoomFilter(){

    }*/
    private void loadRoomComponent() {
        tableRowSelected(tbl_room);

        /*this.room_menu = new JPopupMenu();
        room_menu.add("Book Room").addActionListener(e -> {

        });*/
        /*btn_add_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomView addRoomView = new AddRoomView(new Room());
                addRoomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }
        });
*/
        //    this.fld_s_check_in = new JFormattedTextField(new MaskFormatter("####-##-##"));
   //     this.fld_s_check_out = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_s_check_in.setText("2024-01-01");
        this.fld_s_check_out.setText("2024-01-02");
        this.fld_search_count_of_child.setText("0");
        this.fld_count_of_adult.setText("0");

        this.room_menu = new JPopupMenu();
        //book room
        room_menu.add("Book Room").addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_room,0);
            if (roomManager.getById(selectedRoomId).getStock() >0){
                Reservation reservation = new Reservation();
                reservation.setRoom_id(selectedRoomId);

                if (fld_search_count_of_child.getText().isEmpty() || fld_count_of_adult.getText().isEmpty()){
                    Helper.showMessage("fillCountOfChildAndAdult");
                }else {
                    if(Integer.parseInt(fld_search_count_of_child.getText()) + Integer.parseInt(fld_count_of_adult.getText()) > roomManager.getById(selectedRoomId).getNumberOfBed()){
                        Helper.showMessage("moreThanNumberOfBed");
                    }else{
                        BookRoomView bookRoomView = new BookRoomView(reservation, fld_s_check_in.getText(), fld_s_check_out.getText(), fld_search_count_of_child.getText(), fld_count_of_adult.getText() );
                        bookRoomView.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                loadReservationTable();
                        /*Room updateRoom = roomManager.getById(selectedRoomId);
                        updateRoom.setStock(roomManager.getById(selectedRoomId).getStock() - 1);
                        roomManager.update(updateRoom);*/
                                loadRoomTable(null);
                            }
                        });
                    }
                }
            }else {
                Helper.showMessage("outOfStock");
            }

        });
        this.tbl_room.setComponentPopupMenu(room_menu);
    }

    private void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"Room ID", "Hotel Name","Pension Type","Room Type","Stock", "Child Price", "Adult Price", "Number Of Bed", "Area Of Room", "TV", "Bar", "Game Console", "Money Case", "Projection"};
        if(roomList == null){
             roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.t_model_room,this.tbl_room,col_room,roomList);
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
        //add season
        this.hotelMenu.add("Add Season").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel,0);
            SeasonView seasonView = new SeasonView(null,selectedHotelId);
//            AddSeasonView seasonView = new AddSeasonView(null,selectedHotelId);
           // AddHotelView hotelView = new AddHotelView(null);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        //add pension
        this.hotelMenu.add("Add Pension").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel,0);
            PensionView pensionView = new PensionView(null,selectedHotelId);
//            AddSeasonView seasonView = new AddSeasonView(null,selectedHotelId);
            // AddHotelView hotelView = new AddHotelView(null);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });

      /*  this.hotelMenu.add("Add").addActionListener(e -> {
            AddHotelView hotelView = new AddHotelView(null);
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadSeasonTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }
            });
        });*/
        //add room
        this.hotelMenu.add("Add Room").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel,0);
            Room newRoom = new Room();
//            newRoom.setHotel(hotelManager.getById(selectedHotelId));
            newRoom.setHotelId(selectedHotelId);
            AddRoomView addRoomView = new AddRoomView(newRoom);
            addRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadSeasonTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }
            });
        });

        /*this.hotelMenu.add("Delete Hotel").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedHotelId = this.getTableSelectedRow(tbl_hotel,0);
                if (this.hotelManager.delete(selectedHotelId)){
                    Helper.showMessage("done");
                    loadHotelTable();
                    loadSeasonTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }else {
                    Helper.showMessage("error");
                }
            }
        });*/
        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
        btn_add_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHotelView addHotelView = new AddHotelView(null);
                addHotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            }
        });
    }

    public void loadHotelTable(){
        Object[] col_hotel = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Phone Number", "Hotel Star",
                "Car Parking", "Wifi", "Pool", "Fitness Center", "Concierge", "SPA", "Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length);
        this.createTable(this.t_model_hotel,this.tbl_hotel,col_hotel,hotelList);
    }

    private void createUIComponents() throws ParseException {
        fld_s_check_in = new JFormattedTextField(new MaskFormatter("####-##-##"));
        fld_s_check_out = new JFormattedTextField(new MaskFormatter("####-##-##"));
    }
}
