
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Vistas.Menu;
import javax.swing.table.DefaultTableModel;
import modelos.EmpleadosDAO;
import modelos.EmpleadosDTO;

/**
 *
 * @author MSI
 */

public class ControladorEmp implements ActionListener {
    
     /**
     * Declaracion de los objetos necesarios: DAO = Data Access Object empdao =
     * Estructura de datos del objeto vistaMenu = Formulario de Empleados modelo =
     * Modelo donde se cargan los datos de la tabla
     */
    
        EmpleadosDAO empdao = new EmpleadosDAO();
        EmpleadosDTO empdto = new EmpleadosDTO();
        Menu vistaMenu = new Menu();
        DefaultTableModel modelo = new DefaultTableModel();
    
    
    /**
     * Constructor de la clase Controlador, se encarga de inicializar los
     * componentes del formulario de Empleados. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorEmp(Menu m) {
        
        this.vistaMenu = m;
        
        this.vistaMenu.btnNuevo.addActionListener(this);
        this.vistaMenu.btnActualizar.addActionListener(this);
        this.vistaMenu.btnEliminar.addActionListener(this);
        this.vistaMenu.btnCancelar.addActionListener(this);
        this.vistaMenu.btnBuscar.addActionListener(this);
        this.vistaMenu.btnEditar.addActionListener(this);
        this.vistaMenu.txtBuscar.addActionListener(this);

        this.vistaMenu.btnNuevo.setEnabled(true);
        this.vistaMenu.btnActualizar.setEnabled(false);
        this.vistaMenu.btnBuscar.setEnabled(true);
        this.vistaMenu.btnCancelar.setEnabled(true);
        this.vistaMenu.btnEliminar.setEnabled(true);
        this.vistaMenu.btnBuscar.setEnabled(true);
        
     
        vistaMenu.txtID.enable(false);

        
        //Limpiar formulario y Listar contactos
        
        limpiarCampos(m);
        leer(vistaMenu.jtEmp);
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * Empleados.
     *
     * @param tabla
     */
    
    public void leerEmpleados(JTable tabla) {
        
        limpiarTabla();
        
        modelo = (DefaultTableModel) tabla.getModel();
        List<EmpleadosDTO> lista = (List<EmpleadosDTO>) empdao.listarRegistro(vistaMenu.txtBuscar.getText());
        Object[] object = new Object[8];
        
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getCedula();
            object[4] = lista.get(i).getSueldo();
            object[5] = lista.get(i).getTelefono();
            object[6] = lista.get(i).getCargo();
            object[7] = lista.get(i).getSupervisor();
           
            
            modelo.addRow(object);
        }
        
        vistaMenu.jtEmp.setModel(modelo);
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * Empleados.
     *
     * @param tabla
     */
        public final void leer(JTable tabla) {
            
            limpiarTabla();
            
            modelo = (DefaultTableModel) tabla.getModel();
            List<EmpleadosDTO> lista = (List<EmpleadosDTO>) empdao.listar();
            Object[] object = new Object[8];
            
            for (int i = 0; i < lista.size(); i++) {
                
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getNombre();
                object[2] = lista.get(i).getApellidos();
                object[3] = lista.get(i).getCedula();
                object[4] = lista.get(i).getSueldo();
                object[5] = lista.get(i).getTelefono();
                object[6] = lista.get(i).getCargo();
                object[7] = lista.get(i).getSupervisor();
                
                modelo.addRow(object);
            }
            vistaMenu.jtEmp.setModel(modelo);
        }
    
     /**
     * Este metodo se encarga de limpiar los datos de la tabla antes de cargarla
     * al inicio o al refrescar los datos.
     */
    

        
    public void limpiarTabla() {
        
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
            
        }
    }

    
     /**
     * Este metodo se encarga de registrar un nuevo contacto.
     */
    
    public void nuevo() {

        int r;

        String Nombre = vistaMenu.txtNombre.getText();
        String Apellidos = vistaMenu.txtApellidos.getText();
        String Cedula = vistaMenu.txtCedula.getText();
        float Sueldo = Float.parseFloat(vistaMenu.txtSueldo.getText());
        String Telefono = vistaMenu.txtTelefono.getText();
        String Cargo = vistaMenu.txtCargo.getText();
        int Supervisor = Integer.parseInt(vistaMenu.txtSupervisor.getText());
    

        empdto.setNombre(Nombre);
        empdto.setApellidos(Apellidos);
        empdto.setCedula(Cedula);
        empdto.setSueldo(Sueldo);
        empdto.setTelefono(Telefono);
        empdto.setCargo(Cargo);
        empdto.setSupervisor(Supervisor);
                

        if (validarCampos(vistaMenu) > 0) {
            
            r = empdao.agregar(empdto);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaMenu, "Empleado registrado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtEmp);
                limpiarCampos(vistaMenu);
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de registrar empleado.", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtEmp);
                limpiarCampos(vistaMenu);
            }
        }
    }
    
    
     /**
     * Este metodo se encarga de actualizar el contacto seleccionado dentro de
     * la tabla.
     */
    
    public void actualizar() {
        int r;

        int fila = vistaMenu.jtEmp.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la actualizacion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
            limpiarCampos(vistaMenu);
        } else {
            
            if (validarCampos(vistaMenu) > 0) {
                
                int id = Integer.parseInt(vistaMenu.txtID.getText());
                String Nombre = vistaMenu.txtNombre.getText();
                String Apellidos = vistaMenu.txtApellidos.getText();
                String Cedula = vistaMenu.txtCedula.getText();
                float Sueldo = Float.parseFloat(vistaMenu.txtSueldo.getText());
                String Telefono = vistaMenu.txtTelefono.getText();
                String Cargo = vistaMenu.txtCargo.getText();
                int Supervisor = Integer.parseInt(vistaMenu.txtSupervisor.getText());
                
                Object obj = vistaMenu.jtEmp.getValueAt(fila, 7);

                
                if (obj != null) {
                    
                    
                    empdto.setId(id);
                    empdto.setNombre(Nombre);
                    empdto.setApellidos(Apellidos);
                    empdto.setCedula(Cedula);
                    empdto.setSueldo(Sueldo);
                    empdto.setTelefono(Telefono);
                    empdto.setCargo(Cargo);
                    empdto.setSupervisor(Supervisor);
                    
                }else{
                
                    empdto.setId(id);
                    empdto.setNombre(Nombre);
                    empdto.setApellidos(Apellidos);
                    empdto.setCedula(Cedula);
                    empdto.setSueldo(Sueldo);
                    empdto.setTelefono(Telefono);
                    empdto.setCargo(Cargo);
                    empdto.setSupervisor(0);
                
                }
                


                r = empdao.actualizar(empdto);
                if (r == 1) {
                    JOptionPane.showMessageDialog(vistaMenu, "Empleado actualizado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtEmp);
                    limpiarCampos(vistaMenu);
                } else {
                    JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de actualizar Empleado.", "Error!", JOptionPane.ERROR_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtEmp);
                    limpiarCampos(vistaMenu);
                }
            }
        }
    }
    
     /**
     * Este metodo procede a eliminar el registro seleccionado dentro de la
     * tabla.
     */
    
    public void eliminar() {

        int fila = vistaMenu.jtEmp.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila a borrar.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
            limpiarCampos(vistaMenu);
            
        } else if (JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de eliminar este empleado?", "Borrar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt((String) vistaMenu.jtEmp.getValueAt(fila, 0).toString());

            empdao.eliminar(id);
            JOptionPane.showMessageDialog(vistaMenu, "Empleado eliminado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
            limpiarCampos(vistaMenu);
        }
    }
    
     /**
     * Este metodo pone en modo edicion el formulario y carga los datos del
     * contacto a ser modificado.
     */
    
        public void editar() {
            
        vistaMenu.txtID.enable(false);
        
        int fila = vistaMenu.jtEmp.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
            limpiarCampos(vistaMenu);
            
        }else {
            
            int id = Integer.parseInt((String) vistaMenu.jtEmp.getValueAt(fila, 0).toString());
            String Nombre = (String) vistaMenu.jtEmp.getValueAt(fila, 1);
            String Apellidos = (String) vistaMenu.jtEmp.getValueAt(fila, 2);
            String Cedula = (String) vistaMenu.jtEmp.getValueAt(fila, 3);
            float Sueldo = Float.parseFloat((String) vistaMenu.jtEmp.getValueAt(fila, 4).toString());
            String Telefono = (String) vistaMenu.jtEmp.getValueAt(fila, 5);
            String Cargo = (String) vistaMenu.jtEmp.getValueAt(fila, 6);
            int Supervisor = Integer.parseInt((String) vistaMenu.jtEmp.getValueAt(fila, 7).toString());

            vistaMenu.txtID.setText("" + id);
            vistaMenu.txtNombre.setText(Nombre);
            vistaMenu.txtApellidos.setText(Apellidos);
            vistaMenu.txtCedula.setText(Cedula);
            vistaMenu.txtSueldo.setText("" + Sueldo);
            vistaMenu.txtTelefono.setText(Telefono);
            vistaMenu.txtCargo.setText(Cargo);
            vistaMenu.txtSupervisor.setText("" + Supervisor);

            vistaMenu.txtNombre.requestFocus();
        }
    }

    
    
    /**
     * Este metodo valida los campos del formulario y devuelve si los campos han
     * sido validados.
     *
     * @param m
     * @return validacion
     */
    
    
    public int validarCampos(Menu m) {

        int validacion = 1;

        if (m.txtNombre.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo nombre, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtNombre.requestFocus();
            validacion = 0;
            
        } else if (m.txtApellidos.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo apellidos, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtApellidos.requestFocus();
            validacion = 0;
            
        } else if (m.txtCedula.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo cedula, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCedula.requestFocus();
            validacion = 0;
            
        } else if (m.txtSueldo.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo sueldo, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtSueldo.requestFocus();
            validacion = 0;
            
        } else if (m.txtTelefono.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo telefono, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtTelefono.requestFocus();
            validacion = 0;
            
        } else if (m.txtCargo.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo cargo, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCargo.requestFocus();
            validacion = 0;
            
        } else if (m.txtSupervisor.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo supervisor, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtSupervisor.requestFocus();
            validacion = 0;
        }
        

        return validacion;
    }
    
    
        /**
        * Este metodo limpia los campos del formulario antes de ser utilizado.
        *
        * @param m
        */
       public final void limpiarCampos(Menu m) {

           m.txtID.setText("");
           m.txtNombre.setText("");
           m.txtApellidos.setText("");
           m.txtCedula.setText("");
           m.txtSueldo.setText("");
           m.txtTelefono.setText("");
           m.txtCargo.setText("");
           m.txtSupervisor.setText("");
           m.txtNombre.requestFocus();


       }
       
        /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
       
    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        if (e.getSource() == vistaMenu.btnNuevo) {
            nuevo();
            leer(vistaMenu.jtEmp);

        }if (e.getSource() == vistaMenu.btnEditar) {
            editar();
            this.vistaMenu.btnEditar.setEnabled(true);
            this.vistaMenu.btnActualizar.setEnabled(true);
            this.vistaMenu.btnNuevo.setEnabled(false);
            this.vistaMenu.btnEliminar.setEnabled(true);
            
         }
        if (e.getSource() == vistaMenu.btnActualizar) { // GUARDAR
            actualizar();
            this.vistaMenu.btnActualizar.setEnabled(false);
            this.vistaMenu.btnNuevo.setEnabled(true);
            this.vistaMenu.btnEliminar.setEnabled(false);
            this.vistaMenu.btnEditar.setEnabled(true);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
            vistaMenu.txtNombre.requestFocus();
        }
        if (e.getSource() == vistaMenu.btnEliminar) {
            eliminar();
            this.vistaMenu.btnNuevo.setEnabled(true);
            this.vistaMenu.btnActualizar.setEnabled(false);
            this.vistaMenu.btnEliminar.setEnabled(false);
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
        }
        if (e.getSource() == vistaMenu.btnCancelar) {
            this.vistaMenu.btnNuevo.setEnabled(true);
            this.vistaMenu.btnEditar.setEnabled(true);            
            this.vistaMenu.btnActualizar.setEnabled(false);
            this.vistaMenu.btnEliminar.setEnabled(true);
            vistaMenu.txtBuscar.setText("");
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtEmp);
        }
        if (e.getSource() == vistaMenu.btnBuscar) {
            
            if (!this.vistaMenu.txtBuscar.getText().equals("")) {
                this.vistaMenu.btnNuevo.setEnabled(false);
                this.vistaMenu.btnActualizar.setEnabled(true);
                this.vistaMenu.btnCancelar.setEnabled(true);
                this.vistaMenu.btnEliminar.setEnabled(true);
                limpiarCampos(vistaMenu);
                limpiarTabla();
                leerEmpleados(vistaMenu.jtEmp);
            } else {
                this.vistaMenu.btnCancelar.setEnabled(true);
                JOptionPane.showMessageDialog(vistaMenu, "El campo de busqueda esta vacio.");
            }
        }
    
}
    
}




