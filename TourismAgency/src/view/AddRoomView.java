package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomView extends Layout{
    private JPanel container;
    private JTextField fld_stock;
    private JTextField fld_child_price;
    private JTextField fld_adult_price;
    private JTextField fld_number_of_bed;
    private JComboBox<ComboItem> cmb_pension;
    private JComboBox<ComboItem> cmb_season;
    private JComboBox<Room.Type> cmb_room_type;
//    private JComboBox<Room.Type> cmb_room_type;
    private JButton btn_save_room;
    private JRadioButton rdb_tv;
    private JRadioButton rdb_bar;
    private JRadioButton rdb_game_console;
    private JRadioButton rdb_projection;
    private JRadioButton rdb_money_case;
    private JTextField fld_area_of_room;
    private JTextField fld_room_hotel_name;
    private Room room;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    public AddRoomView(Room room) {
        this.room = room;
        this.hotelManager =new HotelManager();
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        guiInitialize(700,700);

//        if(this.hotelManager != null) {
           /* for (Hotel hotel : this.hotelManager.findAll()) {
                this.cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
                System.out.println(hotel.getName());
            }*/
        this.fld_room_hotel_name.setText(hotelManager.getById(room.getHotelId()).getName());
        this.fld_room_hotel_name.setEditable(false);
     //   this.cmb_hotel.addItem(new ComboItem(room.getHotelId(), hotelManager.getById(room.getHotelId()).getName()));
        for (Season season : this.seasonManager.getSeasonListByHotelId(room.getHotelId())) {
            this.cmb_season.addItem(new ComboItem(season.getId(), String.valueOf(season.getStartTime()) +"/" + String.valueOf(season.getFinishTime())));
        }
        for (Pension pension : this.pensionManager.getPensionListByHotelId(room.getHotelId())) {
            this.cmb_pension.addItem(new ComboItem(pension.getId(), pension.getType().toString()));
        }
//        }
        /*for (Season season : this.seasonManager.findAll()){
            String str = String.valueOf(season.getStartTime());
            String str2 = String.valueOf(season.getFinishTime());
            str += "/" + str2;
            this.cmb_season.addItem(new ComboItem(season.getId(),str));
        }*/

        /*for (Pension pension : this.pensionManager.findAll()){
            this.cmb_pension.addItem(new ComboItem(pension.getId(), pension.getType().toString()));
        }*/

        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.Type.values()));
      //  this.cmb_pension.setModel(new DefaultComboBoxModel<>(Pension.Type.values()));
        btn_save_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //|| cmb_hotel.getSelectedItem() == null || cmb_pension.getSelectedItem() == null || cmb_season == null
                if(Helper.isFieldListEmpty(new JTextField[]{fld_stock, fld_child_price, fld_adult_price,fld_number_of_bed }) ){
                    Helper.showMessage("fill");
                }else{
                    boolean result =true;



           //         if(room.getId() == 0){
                        /*result =roomManager.save(new Room(fld_stock.getText(),fld_hotel_address.getText(),fld_hotel_phone.getText(),fld_hotel_star.getText(),
                                false, false, false, false, false, false,false));*/

             //       }
           //         ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                    ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                    ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();
                    room.setHotelId(room.getHotelId());
                    room.setSeasonId(selectedSeason.getKey());
                    room.setPensionId(selectedPension.getKey());
                //    ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
//                    room.setPensionId((Integer) cmb_pension.getSelectedItem());
//                    ComboItem selectedSeason= (ComboItem) cmb_season.getSelectedItem();
//                    room.setSeasonId(selectedSeason.getKey());
                    room.setAreaOfRoom(Integer.parseInt(fld_area_of_room.getText()));
                    room.setStock(Integer.parseInt(fld_stock.getText()));
                    room.setChildPrice(Double.parseDouble(fld_child_price.getText()));
                    room.setAdultPrice(Double.parseDouble(fld_adult_price.getText()));
                    room.setNumberOfBed(Integer.parseInt(fld_number_of_bed.getText()));
                    room.setType((Room.Type) cmb_room_type.getSelectedItem());
                    room.setTV(rdb_tv.isSelected());
                    room.setBar(rdb_bar.isSelected());
                    room.setGameConsole(rdb_game_console.isSelected());
                    room.setMoneyCase(rdb_money_case.isSelected());
                    room.setProjection(rdb_projection.isSelected());
                    if(room.getId() != 0){
                      //  result = this.carManager.update(this.car);
                    }else{
                        result = roomManager.save(room);
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
}
