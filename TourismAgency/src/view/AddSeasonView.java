package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddSeasonView extends Layout{
    private JPanel container;
    private JTextField fld_season_hotel_name;
    private JTextField fld_start_time;
    private JTextField fld_finish_time;
    private JButton btn_season_save;
    private JLabel lbl_hotel_name;
    private Season season;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;
    public AddSeasonView(Season season, int hotel_id) {
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialize(300,300);

     //   this.fld_season_hotel_name.setText(this.hotelManager.getById(hotel_id).getName());
        this.lbl_hotel_name.setText("Hotel Name : " + this.hotelManager.getById(hotel_id).getName());
        //save season
        btn_season_save.addActionListener(e -> {
          // check empty field
            if(Helper.isFieldListEmpty(new JTextField[]{fld_start_time,fld_start_time})){
                Helper.showMessage("fill");
            }else{
                boolean result = true;

                if(this.season == null){
                    result = this.seasonManager.save(new Season(LocalDate.parse(fld_start_time.getText()),LocalDate.parse(fld_finish_time.getText()),hotel_id));
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
