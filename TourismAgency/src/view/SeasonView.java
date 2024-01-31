package view;

import business.HotelManager;
import business.SeasonManager;
import entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SeasonView extends Layout{
    private JPanel container;
    private JLabel lbl_header;
    private JTable tbl_season;
    private JButton btn_add_season;
    private JLabel lbl_hotel_name;
    private DefaultTableModel t_model_season= new DefaultTableModel();
    public SeasonManager seasonManager;
    public HotelManager hotelManager;

    public SeasonView(Season season, int hotel_id) {
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialize(500,500);

    //    this.fld_hotel_name.setText(this.hotelManager.getById(hotel_id).getName());
        this.lbl_hotel_name.setText("Hotel Name : " + this.hotelManager.getById(hotel_id).getName());

     //   loadAllSeasonTable();
        loadASeasonTableByHotelId(hotel_id);
        btn_add_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSeasonView addSeasonView = new AddSeasonView(null,hotel_id);
                addSeasonView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                      //  loadAllSeasonTable();
                        loadASeasonTableByHotelId(hotel_id);
                    }
                });
            }
        });
    }
    private void loadASeasonTableByHotelId(int hotel_id) {
        Object[] col_season = {"Season ID", "Hotel Name", "Season Start Time", "Season Finish Time"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.getSeasonListByHotelId(hotel_id));
        this.createTable(this.t_model_season,this.tbl_season,col_season,seasonList);
    }
    private void loadAllSeasonTable() {
        Object[] col_season = {"Season ID", "Hotel Name", "Season Start Time", "Season Finish Time"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.t_model_season,this.tbl_season,col_season,seasonList);
    }
}
