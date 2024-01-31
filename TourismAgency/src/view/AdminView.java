package view;

import business.UserManager;
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
    private DefaultTableModel t_model_user = new DefaultTableModel();
    private JPopupMenu adminMenu;
    private User user;
    private UserManager userManager;
    public AdminView(User user) {
        userManager = new UserManager();

        this.add(container);
        guiInitialize(600,300);

        this.user = user;
        this.lbl_welcome.setText("Welcome, " + user.getUsername());

        loadUserTable();
        loadAdminComponent();
        this.tbl_admin.setComponentPopupMenu(adminMenu);

        btn_add_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserView userView = new AddUserView(null);

                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUserTable();
                    }
                });
            }
        });
    }

    public void loadUserTable(){
        Object[] col_user = {"User ID", "User Name", "User Password", "Role"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length);
        this.createTable(this.t_model_user,this.tbl_admin,col_user,userList);
    }
    private void loadAdminComponent() {
        this.tbl_admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_admin.rowAtPoint(e.getPoint());
                tbl_admin.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });
        this.adminMenu = new JPopupMenu();
        this.adminMenu.add("Add").addActionListener(e -> {
            AddUserView addAdminView = new AddUserView(null);
            addAdminView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.adminMenu.add("Update").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_admin,0);
            AddUserView addUserView = new AddUserView(this.userManager.getById(selectedUserId));
      //      BrandView brandView = new BrandView(this.brandManager.getById(selectedBrandId));
            addUserView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.adminMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedHotelId = this.getTableSelectedRow(tbl_admin,0);
                if (this.userManager.delete(selectedHotelId)){
                    Helper.showMessage("done");
                    loadUserTable();
                }else {
                    Helper.showMessage("error");
                }
            }

        });
    }
}
