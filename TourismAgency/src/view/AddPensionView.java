package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPensionView extends Layout{
    private JPanel container;
    private JComboBox cmb_pension_type;
    private JButton btn_save_pension;
    private Pension pension;
    private PensionManager pensionManager;
    private HotelManager hotelManager;
    public AddPensionView(Pension pension, int hotel_id) {
        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialize(300,300);

        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(Pension.Type.values()));
        //save pension
        btn_save_pension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                pension.setHotel_id(hotel_id);
                pension.setType((Pension.Type) cmb_pension_type.getSelectedItem());
                result = pensionManager.save(pension);
                if(result){
                    Helper.showMessage("done");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
