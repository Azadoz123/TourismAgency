package view;

import business.ReservationManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class BookRoomView extends Layout{
    private JPanel container;
    private JTextField fld_book_hotel_name;
    private JTextField fld_book_hotel_city;
    private JTextField fld_book_hotel_star;
    private JRadioButton rdb_car_parking;
    private JRadioButton rdb_wifi;
    private JRadioButton rdb_pool;
    private JRadioButton rdb_fitness_center;
    private JRadioButton rdb_spa;
    private JRadioButton rdb_room_service;
    private JRadioButton rdb_concierge;
    private JTextField fld_number_of_bed;
    private JTextField fld_pension_type;
    private JTextField fld_check_in_date;
    private JTextField fld_check_out_date;
    private JTextField fld_total_price;
    private JTextField fld_capacity_of_bed;
    private JTextField fld_area_of_room;
    private JRadioButton rdb_TV;
    private JRadioButton rdb_game_console;
    private JRadioButton rdb_projection;
    private JRadioButton rdb_bar;
    private JRadioButton rdb_money_case;
    private JTextField fld_guest_name;
    private JTextField fld_guest_ID;
    private JTextField fld_guest_mail;
    private JTextField fld_guest_phone_number;
    private JTextField fld_number_of_child;
    private JTextField fld_number_of_adult;
    private JButton btn_book_room;
    private Reservation reservation;
    private Room room;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    public BookRoomView(Reservation reservation) {
 //   public BookRoomView(int selectedRoomId) {
  //     this.reservation = new Reservation();
        this.reservation = reservation;
        int selectedRoomId = reservation.getRoom_id();
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.add(container);
        guiInitialize(900,700);

        room = new Room();
        room = this.roomManager.getById(selectedRoomId);
        //fill hotel information
        this.fld_book_hotel_name.setText(this.roomManager.getById(selectedRoomId).getHotel().getName());
        this.fld_book_hotel_name.setEditable(false);
        this.fld_book_hotel_city.setText(this.roomManager.getById(selectedRoomId).getHotel().getAddress());
        this.fld_book_hotel_city.setEditable(false);
        this.fld_book_hotel_star.setText(this.roomManager.getById(selectedRoomId).getHotel().getStar());
        this.fld_book_hotel_star.setEditable(false);
        this.rdb_car_parking.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isCar_parking());
        this.rdb_car_parking.setEnabled(false);
        this.rdb_wifi.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isWifi());
        this.rdb_wifi.setEnabled(false);
        this.rdb_pool.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isPool());
        this.rdb_pool.setEnabled(false);
        this.rdb_fitness_center.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isFitness_center());
        this.rdb_fitness_center.setEnabled(false);
        this.rdb_concierge.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isConcierge());
        this.rdb_concierge.setEnabled(false);
        this.rdb_spa.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isSpa());
        this.rdb_spa.setEnabled(false);
        this.rdb_room_service.setSelected(this.roomManager.getById(selectedRoomId).getHotel().isRoom_service());
        this.rdb_room_service.setEnabled(false);
        //fill room information
        this.fld_number_of_bed.setText(String.valueOf(this.roomManager.getById(selectedRoomId).getNumberOfBed()));
        this.fld_number_of_bed.setEditable(false);
        this.fld_capacity_of_bed.setText(String.valueOf(this.roomManager.getById(selectedRoomId).getNumberOfBed()));
        this.fld_capacity_of_bed.setEditable(false);
        this.fld_pension_type.setText(String.valueOf(this.roomManager.getById(selectedRoomId).getType()));
        this.fld_pension_type.setEditable(false);
        this.fld_area_of_room.setText(String.valueOf(this.roomManager.getById(selectedRoomId).getAreaOfRoom()));
        this.fld_area_of_room.setEditable(false);
        this.fld_total_price.setEditable(false);
        //fill room information
        this.rdb_TV.setSelected(this.roomManager.getById(selectedRoomId).isTV());
        this.rdb_TV.setEnabled(false);
        this.rdb_bar.setSelected(this.roomManager.getById(selectedRoomId).isBar());
        this.rdb_bar.setEnabled(false);
        this.rdb_game_console.setSelected(this.roomManager.getById(selectedRoomId).isGameConsole());
        this.rdb_game_console.setEnabled(false);
        this.rdb_money_case.setSelected(this.roomManager.getById(selectedRoomId).isMoneyCase());
        this.rdb_money_case.setEnabled(false);
        this.rdb_projection.setSelected(this.roomManager.getById(selectedRoomId).isProjection());
        this.rdb_projection.setEnabled(false);

        if (this.reservation.getId() != 0){

            this.fld_guest_ID.setText(reservation.getGuestCitizenId());
            this.fld_guest_name.setText(reservation.getGuestName());
            this.fld_guest_phone_number.setText(reservation.getGueastPhone());
            this.fld_guest_mail.setText(reservation.getGuestMail());
            this.fld_check_in_date.setText(String.valueOf(reservation.getCheckInDate()));
            this.fld_check_out_date.setText(String.valueOf(reservation.getCheckOutDate()));
            this.fld_number_of_child.setText(String.valueOf(reservation.getNumberOfChild()));
            this.fld_number_of_adult.setText(String.valueOf(reservation.getNumberOfAdult()));

        }
        btn_book_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldListEmpty(new JTextField[]{
                        fld_guest_name, fld_guest_ID, fld_guest_mail,fld_guest_phone_number,
                        fld_check_in_date,fld_check_out_date,fld_number_of_child,fld_number_of_adult
                }) ){
                    Helper.showMessage("fill");
                }else{
                    boolean result =true;
                    reservation.setRoom_id(selectedRoomId);
                    reservation.setCheckInDate(LocalDate.parse(fld_check_in_date.getText()) );
                    reservation.setCheckOutDate(LocalDate.parse(fld_check_out_date.getText()));
                    reservation.setTotalPrice( (Integer.parseInt(fld_number_of_child.getText()) * room.getChildPrice()) + (Integer.parseInt(fld_number_of_adult.getText()) * room.getAdultPrice()));
                    reservation.setGuestCount(Integer.parseInt(fld_number_of_adult.getText()) +Integer.parseInt(fld_number_of_adult.getText()));
                    reservation.setGuestCitizenId(fld_guest_ID.getText());
                    reservation.setGuestName(fld_guest_name.getText());
                    reservation.setGuestMail(fld_guest_mail.getText());
                    reservation.setGueastPhone(fld_guest_phone_number.getText());
                    reservation.setNumberOfChild(Integer.parseInt(fld_number_of_child.getText()) );
                    reservation.setNumberOfAdult(Integer.parseInt(fld_number_of_adult.getText()) );

                    if(reservation.getId() != 0){
                          result = reservationManager.update(reservation);
                    }else{
                        result = reservationManager.save(reservation);
                        Room updateRoom = roomManager.getById(reservation.getRoom_id());
                        updateRoom.setStock(roomManager.getById(reservation.getRoom_id()).getStock() - 1);
                        roomManager.update(updateRoom);
                    }
                    if (result){
                        Helper.showMessage("done");
                        dispose();
                    }else {
                        // System.out.println(room);
                        Helper.showMessage("error");
                    }
                }
            }

        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
