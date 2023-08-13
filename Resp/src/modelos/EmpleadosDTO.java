
package modelos;

/**
 * //DTO para el mantenimiento de los empleados
 * @author MSI
 */

public class EmpleadosDTO {
    
    int id;
    String nombre;
    String apellidos;
    String cedula;
    float sueldo;
    String telefono;
    String cargo;
    int supervisor;
    
    public EmpleadosDTO(){
    
    }
    
    public EmpleadosDTO(int id,  String nombre, String apellidos, String cedula,  float sueldo, String telefono, String cargo, int supervisor ){
    
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.sueldo = sueldo;
        this.telefono = telefono;
        this.cargo = cargo;
        this.supervisor = supervisor;
        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }
    
    
    
}
