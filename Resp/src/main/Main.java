package main;

import Vistas.Menu;
import controladores.ControladorEmp;
import controladores.ControladorProd;
import controladores.ControladorClient;
import controladores.ControladorPed;
import controladores.ControladorUsuario;
import modelos.EmpleadosDAO;
import modelos.EmpleadosDTO;
import modelos.ProductosDAO;
import modelos.ProductosDTO;
import modelos.ClientesDAO;
import modelos.ClientesDTO;
import modelos.UsuarioDAO;
import modelos.UsuarioDTO;
import modelos.PedidosDAO;
import modelos.PedidosDTO;




/**
 *
 * @author MSI
 */

public class Main {
    
    public static void main(String[] args) {
        
        UsuarioDAO usdao = new UsuarioDAO();
        UsuarioDTO usdto = new UsuarioDTO();
        EmpleadosDAO empdao = new EmpleadosDAO();
        EmpleadosDTO empdto = new EmpleadosDTO();
        ProductosDAO proddao = new ProductosDAO();
        ProductosDTO proddto = new ProductosDTO();
        ClientesDAO clientdao = new ClientesDAO();
        ClientesDTO clientdto = new ClientesDTO();
        PedidosDAO pedao = new PedidosDAO();
        PedidosDTO pedto = new PedidosDTO();
        
        Menu menu = new Menu();
        ControladorUsuario us = new ControladorUsuario(menu);
        ControladorEmp cemp = new ControladorEmp(menu);
        ControladorProd cprod =new ControladorProd (menu);
         ControladorClient cclien =new ControladorClient (menu);
        ControladorPed cped =new ControladorPed (menu);
    
        
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        
    }
    
    
}
