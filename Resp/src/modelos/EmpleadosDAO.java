
package modelos;

/**
 *
 * @author MSI
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class EmpleadosDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection        con;
    PreparedStatement ps;
    ResultSet         rs;
    
    
    /*Con este metodo mostratemos los datos de la base de datos en el formulario*/
    
     public List listarRegistro(String valorBuscar){
        
        String sql = "SELECT * FROM empleados "+
                     "WHERE ID_Empleados||Nombres||Apellidos||Telefonos||Correo like '%"+valorBuscar+"%'";
        
        List<EmpleadosDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                EmpleadosDTO emp = new EmpleadosDTO();
                
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellidos(rs.getString(3));
                emp.setCedula(rs.getString(4));
                emp.setSueldo(rs.getFloat(5));
                emp.setTelefono(rs.getString(6));
                emp.setCargo(rs.getString(7));
                emp.setSupervisor(rs.getInt(8));
                
                datos.add(emp);
            }
        }catch(SQLException ex){
            System.out.println("Error al listar los contactos: " + ex);
        }
        return datos;
    }
    
    
    
}
