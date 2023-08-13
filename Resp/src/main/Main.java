package main;

import Vistas.Menu;
import controladores.ControladorEmp;


/**
 *
 * @author MSI
 */

public class Main {
    
    public static void main(String[] args) {
        
        Menu menu = new Menu();
        ControladorEmp cemp = new ControladorEmp(menu);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        
    }
    
    
}
