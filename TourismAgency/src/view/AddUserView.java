package view;

import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserView extends Layout{
    private JPanel container;
    private JLabel lbl_user;
    private JLabel lbl_user_name;
    private JTextField fld_user_name;
    private JTextField fld_user_password;
    private JTextField fld_user_role;
    private JButton btn_user_save;
    private User user;
    private UserManager userManager;
    public AddUserView(User user) {
        this.user =user;
        this.userManager = new UserManager();

        this.add(container);
        guiInitialize(300,300);

        if(user != null){
            this.fld_user_name.setText(this.user.getUsername());
            this.fld_user_password.setText(this.user.getPassword());
            this.fld_user_role.setText(this.user.getRole());
        }
        btn_user_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Helper.isFieldListEmpty(new JTextField[]{fld_user_name, fld_user_password, fld_user_role})){
                    Helper.showMessage("fill");
                }else{
                    boolean result =true;
                    if(user == null){
                        result =userManager.save(new User(fld_user_name.getText(),fld_user_password.getText(),fld_user_role.getText()));
                    }else {
                        user.setUsername(fld_user_name.getText());
                        user.setPassword(fld_user_password.getText());
                        user.setRole(fld_user_role.getText());
                        result = userManager.update(user);
                    }
                    if (result){
                        Helper.showMessage("done");
                        dispose();
                    }else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
    }
}
