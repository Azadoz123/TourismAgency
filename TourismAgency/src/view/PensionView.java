package view;

import business.HotelManager;
import business.PensionManager;
import entity.Pension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class PensionView extends Layout{
    private JPanel container;
    private JLabel lbl_header;
    private JLabel lbl_hotel_name;
    private JButton btn_add_pension;
    private JTable tbl_pension;
    private DefaultTableModel t_model_pension= new DefaultTableModel();

    public PensionManager pensionManager;
    public HotelManager hotelManager;
    public PensionView(Pension pension, int hotel_id) {
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialize(500,500);

        this.lbl_hotel_name.setText("Hotel Name : " + this.hotelManager.getById(hotel_id).getName());

        loadPensionTableByHotelId(hotel_id);

        btn_add_pension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPensionView addPensionView = new AddPensionView(new Pension(),hotel_id);
                addPensionView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //  loadAllSeasonTable();
                        loadAllPensionTable();
                        loadPensionTableByHotelId(hotel_id);
                    }
                });
            }
        });
    }
    private void loadPensionTableByHotelId(int hotel_id) {
        Object[] col_pension = {"Pension ID", "Hotel Name", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.getPensionListByHotelId(hotel_id));
        this.createTable(this.t_model_pension,this.tbl_pension,col_pension,pensionList);
    }
    private void loadAllPensionTable() {
        Object[] col_pension = {"Pension ID", "Hotel Name", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.t_model_pension,this.tbl_pension,col_pension,pensionList);
    }
}
