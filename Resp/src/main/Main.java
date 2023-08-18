package main;



import Vistas.LoginV;
import controladores.ControladorLogin;

/**
 *
 * @author MSI
 */

public class Main {
    
    public static void main(String[] args) {
        
        
        LoginV lv = new LoginV();
        lv.setVisible(true);
        lv.setLocationRelativeTo(null);
        ControladorLogin cl = new ControladorLogin(lv);
        
        
    }
    
    
}
