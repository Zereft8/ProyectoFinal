package main;

import Vistas.Menu;
import controladores.ControladorEmp;
import modelos.EmpleadosDAO;
import modelos.EmpleadosDTO;


/**
 *
 * @author MSI
 */

public class Main {
    
    public static void main(String[] args) {
        
        
        EmpleadosDAO empdao = new EmpleadosDAO();
        EmpleadosDTO empdto = new EmpleadosDTO();
        Menu menu = new Menu();
        ControladorEmp cemp = new ControladorEmp(menu);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        
    }
    
    
}
