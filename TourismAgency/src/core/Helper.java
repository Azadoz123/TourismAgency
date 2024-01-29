package core;

import javax.swing.*;

public class Helper {
    public static void setTheme(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
    public static void showMessage(String str){

        String msg;
        String title;

        switch (str){
            case "fill":
                msg = "Please, fill all area !";
                title = "Error";
                break;
            case "done":
                msg = "Operation is successful!";
                title = "Sonuç";
                break;
            case "notFound":
                msg = "Registry is not found";
                title= "Not Found";
                break;
            case "error":
                msg = "You made mistake operarion!";
                title= "Error !";
                break;
            default:
                msg = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null,msg, title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field: fieldList) {
            if(isFieldEmpty(field))
                return true;
        }
        return false;
    }
    public static boolean confirm(String str){

        String msg;
        if(str.equals("sure")){
            msg = "Are you sure for this operation ?";
        }else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null,msg,"Are you sure?",JOptionPane.YES_NO_OPTION) == 0;
    }
    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }
}
