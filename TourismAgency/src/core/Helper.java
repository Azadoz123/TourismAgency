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
                msg = "Lütfen tüm alanalrı doldurunuz !";
                title = "Hata";
                break;
            case "done":
                msg = "İşlem Başarılı !";
                title = "Sonuç";
                break;
            case "notFound":
                msg = "Kayıt bulunmadı";
                title= "Bulunamadı";
                break;
            case "error":
                msg = "Hatalı işlem yaptınız !";
                title= "Hata !";
                break;
            default:
                msg = str;
                title = "Mesaj";
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
}
