
package modelos;

import java.util.Date;

/**
 *
 * @author MSI
 */

public class UsuarioDTO {
    
    int id;
    String password;
    String usuario;
    String tipo_usuario;
    Date fecha_registro;
    int id_empleados;
    
    public UsuarioDTO(){
    
    
    }
    
        public UsuarioDTO(int id,  String password, String usuario, String tipo_usuario,  Date fecha_registro, int id_empleados ){
    
        this.id = id;
        this.password = password;
        this.usuario = usuario;
        this.tipo_usuario = tipo_usuario;
        this.fecha_registro = fecha_registro;
        this.id_empleados = id_empleados;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId_empleados() {
        return id_empleados;
    }

    public void setId_empleados(int id_empleados) {
        this.id_empleados = id_empleados;
    }
     
    
}
