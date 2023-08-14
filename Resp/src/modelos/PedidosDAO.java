
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
 * @author Elianny
 */
public class PedidosDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection        con;
    PreparedStatement ps;
    ResultSet         rs;
    
    
    
    /*Con este solo se listara un registro de la base de datos*/
    
     public List listarRegistro(String valorBuscar){
         

        String sql = "SELECT * FROM pedidos WHERE ID_Pedidos LIKE '%"+valorBuscar+"%'";
        
        List<PedidosDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                PedidosDTO pd = new PedidosDTO();
                
                pd.setId(rs.getInt(1));
                pd.setDescripcion(rs.getString(2));
                pd.setFecha_entrega(rs.getDate(3));
                pd.setFecha_pedidos(rs.getDate(4));
                pd.setId_clientes(rs.getInt(5));
                pd.setId_productos(rs.getInt(6));
                
                datos.add(pd);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los upedidos: " + ex);
        }
        return datos;
    }
    
     /*Con este metodo se listaran los registros de la base de datos*/ 
     
        public List listar(){
            
        String sql = "SELECT * FROM pedidos";
        
        List<PedidosDTO>datos = new ArrayList<>();
        try{
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while(rs.next()){
                
                PedidosDTO pd = new PedidosDTO();
                
                pd.setId(rs.getInt(1));
                pd.setDescripcion(rs.getString(2));
                pd.setFecha_entrega(rs.getDate(3));
                pd.setFecha_pedidos(rs.getDate(4));
                pd.setId_clientes(rs.getInt(5));
                pd.setId_productos(rs.getInt(6));
                
                datos.add(pd);
            }
        }catch(SQLException ex){
            
            System.out.println("Error al listar los usuarios: " + ex);
            
        }
        
        return datos;
    }
    
        //Este metodo se encargara de insertar datos
    
        public int agregar(PedidosDTO us){
        
        String sql = "INSERT INTO pedidos (Descripcion, Fecha_Entrega, Fecha_Pedido, ID_Clientes,ID_Productos) VALUES (?,?,?,?,?)";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, us.getDescripcion());
            ps.setDate(2, (Date) us.getFecha_entrega());
             ps.setDate(3, (Date) us.getFecha_pedidos());
            ps.setInt(4, us.getId_clientes());
            ps.setInt(5, us.getId_productos());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            
            System.out.println("Error al insertar datos: " + e);
            
        }
        
        return 1;
    }
        
        // Este metodo se encargara de actualizar nuevos datos
        
        public int actualizar(PedidosDTO pe){
        
        int r = 0;
        
        String sql = "UPDATE pedidos SET Descripcion=?, Fecha_Entrega=?, Fecha_Pedido=?, ID_Clientes=?,ID_Productos=? WHERE ID_Pedidos=?";
        
        try {
            
            con = conectar.conectar();
            ps  = con.prepareStatement(sql);
            
            ps.setString(1, pe.getDescripcion());
            ps.setDate(2, (Date) pe.getFecha_entrega());
            ps.setDate(3, (Date) pe.getFecha_pedidos());
            ps.setInt(4, pe.getId_clientes());
            ps.setInt(5, pe.getId_productos());
            ps.setInt(6, pe.getId());
            

            
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
        
        String sql = "DELETE FROM pedidos WHERE ID_Pedidos = "+ id;
        
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

    private PedidosDTO PedidosDTO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
