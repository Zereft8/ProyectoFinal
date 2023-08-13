
package modelos;

/**
 *
 * @author MSI
 * 
 */


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class EmpleadosDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection        con;
    PreparedStatement ps;
    ResultSet         rs;
    
    
    
    /*Con este solo se listara un registro de la base de datos*/
    
     public List listarRegistro(String valorBuscar){
        
        String sql = "SELECT * FROM empleados "+
                     "WHERE ID_Empleados LIKE '%"+valorBuscar+"%' OR "+
                     "Nombres LIKE '%"+valorBuscar+"%' OR "+
                     "Apellidos LIKE '%"+valorBuscar+"%' OR "+
                     "Telefonos LIKE '%"+valorBuscar+"%' OR "+
                     "Correo LIKE '%"+valorBuscar+"%'";
        
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
            
            System.out.println("Error al listar los empleados: " + ex);
        }
        return datos;
    }
    
     /*Con este metodo se listaran los registros de la base de datos*/ 
     
        public List listar(){
            
        String sql = "select * from empleados";
        
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
            
            System.out.println("Error al listar los empleados: " + ex);
            
        }
        
        return datos;
    }
    
        //Este metodo se encargara de insertar datos
    
        public int agregar(EmpleadosDTO emp){
        
        String sql = "INSERT INTO Empleados (Nombre, Apellidos, Cedula, Sueldo, Télefono, Cargo, ID_Empleados_Supervisor) VALUES (?,?,?,?,?,?,?)";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellidos());
            ps.setString(3, emp.getCedula());
            ps.setFloat(4, emp.getSueldo());
            ps.setString(5, emp.getTelefono());
            ps.setString(6, emp.getCargo());
            ps.setInt(7, emp.getSupervisor());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            
            System.out.println("Error al insertar datos: " + e);
            
        }
        
        return 1;
    }
        
        // Este metodo se encargara de insertar nuevos datos
        
        public int actualizar(EmpleadosDTO emp){
        
        int r = 0;
        
        String sql = "UPDATE empleados SET Nombre = ?, Apellidos = ?, Cedula = ?, Sueldo = ?, Télefono = ?, Cargo = ?, ID_Empleados_Supervisor = ? WHERE ID_Empleados = ?";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellidos());
            ps.setString(3, emp.getCedula());
            ps.setFloat(4, emp.getSueldo());
            ps.setString(5, emp.getTelefono());
            ps.setString(6, emp.getCargo());
            ps.setInt(7, emp.getSupervisor());
            
            r = ps.executeUpdate();
            
            if(r == 1){
                
                return 1;
                
            }else{
                
                return 0;
                
            }
            
        } catch (SQLException e) {
            
            System.out.println("Error al actualizar datos: " + e);
            
        }
        return r;
    }
        
        //Este metodo se encargara de eliminar datos
        
        public int eliminar(int id){
        
        int r = 0;
        
        String sql = "DELETE FROM empleados WHERE ID_Empleados = "+ id;
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            r   = ps.executeUpdate();
            
            if(r == 1){
                
                return 1;
                
            }else{
                
                return 0;
                
            }
        } catch (SQLException e) {
            
            System.out.println("Error al tratar de borrar datos: " + e);
            
        }
        return r;
    }
}
