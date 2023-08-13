
package modelos;

    
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;  

/**
 *
 * @author MSI
 */

public class ConexionMySQL {
    
    Connection cn;
    
   public Connection conectar(){
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/reposteria", "root", "");
            System.out.println("Estamos conectados con exito.");
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error en la conexion :" + ex);
        }
        return cn;
    }
}   

