package main;

import Vistas.Menu;
import controladores.ControladorEmp;
import controladores.ControladorProd;
import controladores.ControladorClient;
import modelos.EmpleadosDAO;
import modelos.EmpleadosDTO;
import modelos.ProductosDAO;
import modelos.ProductosDTO;
import modelos.ClientesDAO;
import modelos.ClientesDTO;


/**
 *
 * @author MSI
 */

public class Main {
    
    public static void main(String[] args) {
        
        
        EmpleadosDAO empdao = new EmpleadosDAO();
        EmpleadosDTO empdto = new EmpleadosDTO();
        ProductosDAO proddao = new ProductosDAO();
        ProductosDTO proddto = new ProductosDTO();
        ClientesDAO clientdao = new ClientesDAO();
        ClientesDTO clientdto = new ClientesDTO();
        
        Menu menu = new Menu();
        ControladorEmp cemp = new ControladorEmp(menu);
        ControladorProd cprod =new ControladorProd (menu);
        ControladorClient cclient =new ControladorClient (menu);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        
    }
    
    
}
