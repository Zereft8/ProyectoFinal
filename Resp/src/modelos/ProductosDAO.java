
package modelos;

/**
 *
 * @author Elianny
 */
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductosDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection        con;
    PreparedStatement ps;
    ResultSet         rs;
    
    
    
    public List listarRegistro(String valorBuscar){
         

        String sql = "SELECT * FROM productos WHERE ID_Productos LIKE '%"+valorBuscar+"%'";
        
        List<ProductosDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                ProductosDTO prod = new ProductosDTO();
                
                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setPrecio(rs.getFloat(3));
                
                
                datos.add(prod);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los empleados: " + ex);
        }
        return datos;
    }
    
     /*Con este metodo se listaran los registros de la base de datos*/ 
     
      public List listar(){
            
        String sql = "SELECT * FROM productos";
        
        List<ProductosDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
               ProductosDTO prod = new ProductosDTO();
                
                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setPrecio(rs.getFloat(3));
                
                
                datos.add(prod);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los productos: " + ex);
            
        }
        
        return datos;
    }
    
        //Este metodo se encargara de insertar datos
    
        public int agregar(ProductosDTO prod){
        
        String sql = "INSERT INTO productos (Nombre, Precio) VALUES (?,?)";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, prod.getNombre());
            ps.setFloat(2, prod.getPrecio());
            
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            
            System.out.println("Error al insertar datos: " + e);
            
        }
        
        return 1;
    }
        
        // Este metodo se encargara de actualizar nuevos datos
        
        public int actualizar(ProductosDTO emp){
        
        int r = 0;
        
        String sql = "UPDATE productos SET Nombre=?, Precio=?  WHERE ID_Productos=?";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, emp.getNombre());
            ps.setFloat(2, emp.getPrecio());
            ps.setInt(3, emp.getId());
            
            
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
        
        String sql = "DELETE FROM productos WHERE ID_Productos = "+ id;
        
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


