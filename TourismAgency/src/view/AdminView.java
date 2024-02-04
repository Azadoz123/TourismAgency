package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JButton btn_log_out;
    private JButton btn_add_user;
    private JTable tbl_admin;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JComboBox<User.Role> cmb_s_admin_role;
    private JButton btn_search_admin;
    private JButton btn_reset_admin;
    private DefaultTableModel t_model_user = new DefaultTableModel();
    Object[] col_user;
    private JPopupMenu adminMenu;
    private User user;
    private UserManager userManager;
    public AdminView(User user) {
        userManager = new UserManager();

        this.add(container);
        guiInitialize(600,300);

        this.user = user;
        this.lbl_welcome.setText("Welcome, " + user.getUsername());

        loadUserTable(null);
        loadAdminComponent();
        loadAdminFilterUser();
    }
    //load admin filter combobox
    public void loadAdminFilterUser(){
        /*this.cmb_s_admin_role.removeAllItems();
        for (User user : this.userManager.findAll()){
            this.cmb_s_admin_role.addItem(new ComboItem(user.getId(),String.valueOf(user.getRole()) ));
        }*/

        cmb_s_admin_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_s_admin_role.setSelectedItem(null);
    }
    //load user table
    public void loadUserTable(ArrayList<Object[]> userList){
        col_user = new Object[]{"User ID", "User Name", "User Password", "Role"};
        if(userList == null){
            userList = this.userManager.getForTable(col_user.length,this.userManager.findAll());
        }
        this.createTable(this.t_model_user,this.tbl_admin,col_user,userList);
    }
    //load admin component
    private void loadAdminComponent() {
        this.tbl_admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_admin.rowAtPoint(e.getPoint());
                tbl_admin.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });
        this.adminMenu = new JPopupMenu();
        //add user
        this.adminMenu.add("Add").addActionListener(e -> {
            AddUserView addAdminView = new AddUserView(new User());
            addAdminView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        //update user
        this.adminMenu.add("Update").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_admin,0);
            AddUserView addUserView = new AddUserView(this.userManager.getById(selectedUserId));
      //      BrandView brandView = new BrandView(this.brandManager.getById(selectedBrandId));
            addUserView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        //delete user
        this.adminMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedHotelId = this.getTableSelectedRow(tbl_admin,0);
                if (this.userManager.delete(selectedHotelId)){
                    Helper.showMessage("done");
                    loadUserTable(null);
                }else {
                    Helper.showMessage("error");
                }
            }

        });
        this.tbl_admin.setComponentPopupMenu(adminMenu);

        btn_add_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserView userView = new AddUserView(new User());

                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUserTable(null);
                        loadAdminFilterUser();
                    }
                });
            }
        });
        //search user
        btn_search_admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   ComboItem selectedUser = (ComboItem) cmb_s_admin_role.getSelectedItem();
                User.Role selectedUserRole = (User.Role) cmb_s_admin_role.getSelectedItem();
                /*User selectedUser = us
                int userId = 0;
                if(selectedUser != null){
                    userId = selectedUser.getKey();
                }*/
                ArrayList<User> userListBySearch = userManager.searchForTable(selectedUserRole);

                ArrayList<Object[]> userRowListBySearch = userManager.getForTable(col_user.length,userListBySearch);
                loadUserTable(userRowListBySearch);
            }
        });
        btn_reset_admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_s_admin_role.setSelectedItem(null);
                loadUserTable(null);
            }
        });
        btn_log_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                dispose();
            }
        });
    }
}
