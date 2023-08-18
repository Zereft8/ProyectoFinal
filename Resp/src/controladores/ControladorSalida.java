
package controladores;


import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import Vistas.Menu;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */

public class ControladorSalida implements ActionListener {
    
        
     /**
      * Instanciando la vista y la conexion para poder cerrar la conexion a la base de datos
     */
    
    Menu vistaMenu = new Menu();
    Connection        con;

    
     /**
     * Constructor de la clase ControladorSalida, se encarga de los componentes
     * necesarios de la vista. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorSalida(Menu m) {
        
        this.vistaMenu = m;
        
        this.vistaMenu.btnMenu_Salir.addActionListener(this);
        this.vistaMenu.btnMenu_Salir.setEnabled(true);



    }
    
     /**
      * Metodo salir, este desplegara un panel de opción si o no, para que el programa no se cierre de golpe.
     */
    
    public void salir() throws SQLException{
    
        if(JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de salir del sistema", "Salir", JOptionPane.YES_NO_OPTION) == 0) {
            
            System.exit(0);
            con.close();
            
        }
    
    }
   
    
     /**
      * Con este metodo verificaremos si el boton salir ha sido presionado y cerraremos la conexión.
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaMenu.btnMenu_Salir) {
            
            try {
                
                salir();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ControladorSalida.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        
        }
    }
}

