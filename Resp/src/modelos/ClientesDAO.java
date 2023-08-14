
package modelos;

/**
 *
 * @author Elianny
 */

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ClientesDAO {
    
    ConexionMySQL conectar = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /*Con este solo se listara un registro de la base de datos*/
    public List listarRegistro(String valorBuscar) {

        String sql = "SELECT * FROM clientes WHERE ID_Clientes LIKE '%" + valorBuscar + "%'";

        List<ClientesDTO> datos = new ArrayList<>();
        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                ClientesDTO cli = new ClientesDTO();

                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellidos(rs.getString(3));
                cli.setCedula(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                

                datos.add(cli);
            }
        } catch (SQLException ex) {

            System.out.println("Error al listar los clientes: " + ex);
        }
        return datos;
    }

    /*Con este metodo se listaran los registros de la base de datos*/
    public List listar() {

        String sql = "SELECT * FROM clientes";

        List<ClientesDTO> datos = new ArrayList<>();
        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                ClientesDTO cli = new ClientesDTO();

                 cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellidos(rs.getString(3));
                cli.setCedula(rs.getString(4));
                cli.setTelefono(rs.getString(5));

                datos.add(cli);
            }
        } catch (SQLException ex) {

            System.out.println("Error al listar los clientes: " + ex);

        }

        return datos;
    }

    //Este metodo se encargara de insertar datos
    public int agregar(ClientesDTO emp) {

        String sql = "INSERT INTO clientes (Nombre, Apellidos, Cedula, Télefono) VALUES (?,?,?,?)";

        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellidos());
            ps.setString(3, emp.getCedula());
            ps.setString(5, emp.getTelefono());
            
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error al insertar datos: " + e);

        }

        return 1;
    }

    // Este metodo se encargara de actualizar nuevos datos
    public int actualizar(ClientesDTO cli) {

        int r = 0;

        String sql = "UPDATE clientes SET Nombre=?, Apellidos=?, Cedula=?, Télefono=? WHERE ID_Clientes=?";

        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellidos());
            ps.setString(3, cli.getCedula());
            ps.setString(4, cli.getTelefono());
            ps.setInt(5, cli.getId());

            r = ps.executeUpdate();

            if (r == 1) {

                return 1;

            } else {

                return 0;

            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar datos: " + e);

        }
        return r;
    }

    //Este metodo se encargara de eliminar datos
    public int eliminar(int id) {

        int r = 0;

        String sql = "DELETE FROM clientes WHERE ID_Clientes = " + id;

        try {

            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

            if (r == 1) {

                return 1;

            } else {

                return 0;

            }
        } catch (SQLException e) {

            System.out.println("Error al tratar de borrar datos: " + e);

        }
        return r;
    }
}


