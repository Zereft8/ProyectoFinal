
package modelos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */

public class LoginDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Este metodo se encarga de listar un registro. El mismo devuelve un objeto
     * tipo arrayList.
     *
     * @param usuario
     * @param password
     * 
     * @return datos
     */
    
    public boolean realizarLogin(String usuario, String password) {
        
        con = conectar.conectar();

        String sql = "SELECT * FROM usuarios WHERE Nombre_Usuario = ? AND Contraseña = ?";

        try {
            
            ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                
                con.close();
                return true;
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            
        }
        return false;
    }
    
}
