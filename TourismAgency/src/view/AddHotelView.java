package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class AddHotelView extends Layout{
    private JPanel container;
    private JLabel lbl_hotel;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JButton btn_hotel_save;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JLabel lbl_hotel_address;
    private JLabel lbl_hotel_phone;
    private JLabel lbl_hotel_star;
    private Hotel hotel;
    private HotelManager hotelManager;
    public AddHotelView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.hotel =hotel;
        this.add(container);
        guiInitialize(300, 400);

        btn_hotel_save.addActionListener(e ->{
//            JTextField[] textFieldsArray= {};
            if(Helper.isFieldListEmpty(new JTextField[]{fld_hotel_name, this.fld_hotel_address, this.fld_hotel_phone, this.fld_hotel_star})){
                Helper.showMessage("fill");
            }else{
                boolean result =true;
                if(this.hotel == null){
                    result =this.hotelManager.save(new Hotel(fld_hotel_name.getText(),fld_hotel_address.getText(),fld_hotel_phone.getText(),fld_hotel_star.getText(),
                                        false, false, false, false, false, false,false));
                }
                if (result){
                    Helper.showMessage("done");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
