
package controladores;

import Vistas.LoginV;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.LoginDAO;
import modelos.LoginDTO;
import Vistas.Menu;


/**
 *
 * @author MSI
 */

public class ControladorLogin implements ActionListener {
    
     /**
     * Declaracion de los objetos necesarios: dao = Data Access Object c =
     * Estructura de datos del objeto vista = Formulario de contactos modelo =
     * Modelo donde se cargan los datos de la tabla
     */
    
    LoginDAO ldao = new LoginDAO();
    LoginDTO ldto = new LoginDTO();
    LoginV loginv = new LoginV();
    
    public ControladorLogin(LoginV ldto) {
        
        this.loginv = ldto;
        this.loginv.btnIniciar_Sesion.addActionListener(this);
        this.loginv.btnLogin_Cancelar.addActionListener(this);
        //Limpiar campos
        limpiarCampos(ldto);
        
    }

    /**
     * Este metodo se encarga de limpiar los campos del formulario de ingreso o
     * login.
     *
     * @param ldto
     */
    private void limpiarCampos(LoginV ldto) {
        
        ldto.txtLogin_Usuario.setText("");
        ldto.txtLogin_Contraseña.setText("");
        ldto.txtLogin_Usuario.requestFocus();
        
    }
    

    /**
     * Este metodo se encarga de validar que tanto el usuario como la clave no
     * esten vacios al momento de realizar el ingreso o login.
     */
    
    private boolean validarCampos(LoginV ldto) {
        if (ldto.txtLogin_Usuario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginv, "El campo del usuario no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            ldto.txtLogin_Usuario.requestFocus();
            return false;
        }
        if (ldto.txtLogin_Contraseña.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginv, "El campo de la contraseña no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            ldto.txtLogin_Contraseña.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Este metodo se encarga de validar los datos del login de usuario.
     *
     * @param usuario
     * @param password
     * @param log
     * 
     * @return boolean
     */
    
    public boolean AccionarLogin(String usuario, String password, LoginV log) {
        if (validarCampos(log)) {
            System.out.println("campos del formulario validados que no esten vacios!");
            try {
                System.out.println("verificando si los datos existen!");
                if (ldao.realizarLogin(usuario, password)) {
                    JOptionPane.showMessageDialog(loginv, "Ingresado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    log.setVisible(false);
                    System.out.println("ingresando a formulario de contactos contactos!");
                    Menu m = new Menu();
                    ControladorEmp control = new ControladorEmp(m);
                    m.setVisible(true);
                    m.setLocationRelativeTo(null);
                    return true;
                } else{
                    JOptionPane.showMessageDialog(log,"Error al tratar de ingresar.\n El usuario o la clave estan incorrectos!", "Error!",JOptionPane.ERROR_MESSAGE);
                    limpiarCampos(log);
                    return false;
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(loginv, "Error al tratar de ingresar: " + e, "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Validando si se presiono la tecla de ingreso
        
        if (e.getSource() == loginv.btnIniciar_Sesion) {
            
            String usuario = loginv.txtLogin_Usuario.getText();
            String clave   = loginv.txtLogin_Contraseña.getText();
            AccionarLogin(usuario, clave, loginv);
            
        }
        
        //Validando si se presiono la tecla de cancelar
        
        if (e.getSource() == loginv.btnLogin_Cancelar) {
            
            System.exit(0);
            
        }
    }
    
}
