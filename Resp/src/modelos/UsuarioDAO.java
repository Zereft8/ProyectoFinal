
package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */

public class UsuarioDAO {
    
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection        con;
    PreparedStatement ps;
    ResultSet         rs;
    
    
    
    /*Con este solo se listara un registro de la base de datos*/
    
     public List listarRegistro(String valorBuscar){
         

        String sql = "SELECT * FROM usuarios WHERE ID_Usuarios LIKE '%"+valorBuscar+"%'";
        
        List<UsuarioDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                UsuarioDTO us = new UsuarioDTO();
                
                us.setId(rs.getInt(1));
                us.setPassword(rs.getString(2));
                us.setUsuario(rs.getString(3));
                us.setTipo_usuario(rs.getString(4));
                us.setFecha_registro(rs.getDate(5));
                us.setId_empleados(rs.getInt(6));
                
                datos.add(us);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los usuarios: " + ex);
        }
        return datos;
    }
    
     /*Con este metodo se listaran los registros de la base de datos*/ 
     
        public List listar(){
            
        String sql = "SELECT * FROM usuarios";
        
        List<UsuarioDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                UsuarioDTO us = new UsuarioDTO();
                
                us.setId(rs.getInt(1));
                us.setPassword(rs.getString(2));
                us.setUsuario(rs.getString(3));
                us.setTipo_usuario(rs.getString(4));
                us.setFecha_registro(rs.getDate(5));
                us.setId_empleados(rs.getInt(6));
                
                datos.add(us);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los usuarios: " + ex);
            
        }
        
        return datos;
    }
    
        //Este metodo se encargara de insertar datos
    
        public int agregar(UsuarioDTO us){
        
        String sql = "INSERT INTO usuarios (Contraseña, Nombre_Usuario, Tipo_Usuario, Fecha_Registro, ID_Empleados) VALUES (?,?,?,?,?)";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, us.getPassword());
            ps.setString(2, us.getUsuario());
            ps.setString(3, us.getTipo_usuario());
            ps.setDate(4, (Date) us.getFecha_registro());
            ps.setInt(5, us.getId_empleados());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            
            System.out.println("Error al insertar datos: " + e);
            
        }
        
        return 1;
    }
        
        // Este metodo se encargara de actualizar nuevos datos
        
        public int actualizar(UsuarioDTO us){
        
        int r = 0;
        
        String sql = "UPDATE usuarios SET Contraseña=?, Nombre_Usuario=?, Tipo_Usuario=?, Fecha_Registro=?, ID_Empleados=? WHERE ID_Usuarios=?";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, us.getPassword() );
            ps.setString(2, us.getUsuario() );
            ps.setString(3, us.getTipo_usuario() );
            ps.setDate(4, (Date) us.getFecha_registro());
            ps.setInt(5, us.getId_empleados());
            ps.setInt(6, us.getId() );

            
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
        
        String sql = "DELETE FROM usuarios WHERE ID_Usuarios = "+ id;
        
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
