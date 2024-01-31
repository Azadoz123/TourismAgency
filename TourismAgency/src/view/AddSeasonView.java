package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        btn_season_save.addActionListener(e -> {
          //  if(Helper.isFieldListEmpty(new JTextField[]{fld_sesason_id,fld_season_hotel_name,fld_start_time,fld_start_time})){
            if(Helper.isFieldListEmpty(new JTextField[]{fld_season_hotel_name,fld_start_time,fld_start_time})){
                Helper.showMessage("fill");
            }else{
                boolean result = true;
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

               // String dateInString = "7-Jun-2013";
                Date date1 = null;
                Date date2 =null;
                try {
                    date1 = formatter.parse(fld_start_time.getText());
                    date2 = formatter.parse(fld_finish_time.getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                if(this.season == null){
                    result = this.seasonManager.save(new Season(date1,date2,hotel_id));
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
