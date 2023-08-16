
package modelos;

/**
 *
 * @author MSI
 */
public class LoginDTO {
    
    int id;
    String password;
    String usuario;

    public LoginDTO() {
    }

    
    public LoginDTO( int id, String password, String usuario) {
        
        this.id = id;
        this.password = password;
        this.usuario = usuario;
        
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
    
}
