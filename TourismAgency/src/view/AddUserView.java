package view;

import business.UserManager;
import core.Helper;
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
    private JComboBox<User.Role> cmb_user_role;
    private User user;
    private UserManager userManager;
    public AddUserView(User user) {
        this.user =user;
        this.userManager = new UserManager();

        this.add(container);
        guiInitialize(300,300);

        cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        if(user.getId() != 0){
            this.fld_user_name.setText(this.user.getUsername());
            this.fld_user_password.setText(this.user.getPassword());
            this.cmb_user_role.setSelectedItem(this.user.getRole());
          //  this.fld_user_role.setText(String.valueOf() );
        }
        //save user
        btn_user_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check empty field
                if(Helper.isFieldListEmpty(new JTextField[]{fld_user_name, fld_user_password})){
                    Helper.showMessage("fill");
                }else{
                    boolean result =true;
                    if(user.getId() == 0){
                        result =userManager.save(new User(fld_user_name.getText(),fld_user_password.getText(), (User.Role) cmb_user_role.getSelectedItem()) );
                    }else {
                        user.setUsername(fld_user_name.getText());
                        user.setPassword(fld_user_password.getText());
                        user.setRole((User.Role) cmb_user_role.getSelectedItem());
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
