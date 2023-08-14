
package controladores;

/**
 *
 * @author Elianny
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Vistas.Menu;
import javax.swing.table.DefaultTableModel;
import modelos.ClientesDAO;
import modelos.ClientesDTO;

public class ControladorClient  implements ActionListener {
      /**
     * Declaracion de los objetos necesarios: DAO = Data Access Object empdao =
     * Estructura de datos del objeto vistaMenu = Formulario de Empleados modelo =
     * Modelo donde se cargan los datos de la tabla
     */
    
    ClientesDAO clientdao = new ClientesDAO();
    ClientesDTO clientdto = new ClientesDTO();
    Menu vistaMenu = new Menu();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    /**
     * Constructor de la clase Controlador, se encarga de inicializar los
     * componentes del formulario de Empleados. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorClient(Menu m) {
        
        this.vistaMenu = m;
        
        this.vistaMenu.btnCliente_Nuevo.addActionListener(this);
        this.vistaMenu.btnCliente_Actualizar.addActionListener(this);
        this.vistaMenu.btnCliente_Eliminar.addActionListener(this);
        this.vistaMenu.btnCliente_Cancelar.addActionListener(this);
        this.vistaMenu.btnCliente_Buscar.addActionListener(this);
        this.vistaMenu.btnCliente_Editar.addActionListener(this);
        this.vistaMenu.txtCliente_Buscar.addActionListener(this);

        this.vistaMenu.btnCliente_Nuevo.setEnabled(true);
        this.vistaMenu.btnCliente_Actualizar.setEnabled(false);
        this.vistaMenu.btnCliente_Buscar.setEnabled(true);
        this.vistaMenu.btnCliente_Cancelar.setEnabled(true);
        this.vistaMenu.btnCliente_Eliminar.setEnabled(true);
        this.vistaMenu.txtCliente_Buscar.setEnabled(true);
        
        vistaMenu.txtIProducto_ID.enable(false);
     

        //Limpiar formulario y Listar contactos
        
        limpiarCampos(m);
        leer(vistaMenu.jtClientes);
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
        List<ClientesDTO> lista = (List<ClientesDTO>) clientdao.listarRegistro(vistaMenu.txtCliente_Buscar.getText());
        Object[] object = new Object[5];
        
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getCedula();
            object[4] = lista.get(i).getTelefono();
           
            
            modelo.addRow(object);
        }
        
        vistaMenu.jtClientes.setModel(modelo);
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
            List<ClientesDTO> lista = (List<ClientesDTO>) clientdao.listar();
            Object[] object = new Object[5];
            
            for (int i = 0; i < lista.size(); i++) {
                
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getNombre();
                object[2] = lista.get(i).getApellidos();
                object[3] = lista.get(i).getCedula();
                object[4] = lista.get(i).getTelefono();
                
                modelo.addRow(object);
            }
            vistaMenu.jtClientes.setModel(modelo);
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

        String Nombre = vistaMenu.txtCliente_Nombre.getText();
        String Apellidos = vistaMenu.txtCliente_Apellidos.getText();
        String Cedula = vistaMenu.txtCliente_Cedula.getText();
        String Telefono = vistaMenu.txtCliente_Telefono.getText();
        
    

        clientdto.setNombre(Nombre);
        clientdto.setApellidos(Apellidos);
        clientdto.setCedula(Cedula);
        clientdto.setTelefono(Telefono);
   
                

        if (validarCampos(vistaMenu) > 0) {
            
            r = clientdao.agregar(clientdto);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaMenu, "Cliente registrado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtClientes);
                limpiarCampos(vistaMenu);
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de registrar Cliente.", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtClientes);
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

        int fila = vistaMenu.jtClientes.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la actualizacion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
            limpiarCampos(vistaMenu);
        } else {
            
            if (validarCampos(vistaMenu) > 0) {
                
                int id = Integer.parseInt(vistaMenu.txtCliente_ID.getText());
                String Nombre = vistaMenu.txtCliente_Nombre.getText();
                String Apellidos = vistaMenu.txtCliente_Apellidos.getText();
                String Cedula = vistaMenu.txtCliente_Cedula.getText();
                String Telefono = vistaMenu.txtCliente_Telefono.getText();
                
                
                Object obj = vistaMenu.jtClientes.getValueAt(fila, 5);

                
                if (obj != null) {
                    
                    
                    clientdto.setId(id);
                    clientdto.setNombre(Nombre);
                    clientdto.setApellidos(Apellidos);
                    clientdto.setCedula(Cedula);
                    clientdto.setTelefono(Telefono);
                    
                }else{
                
                    clientdto.setId(id);
                    clientdto.setNombre(Nombre);
                    clientdto.setApellidos(Apellidos);
                    clientdto.setCedula(Cedula);
                    clientdto.setTelefono(Telefono);
                }
                


                r = clientdao.actualizar(clientdto);
                if (r == 1) {
                    JOptionPane.showMessageDialog(vistaMenu, "Cliente actualizado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtClientes);
                    limpiarCampos(vistaMenu);
                } else {
                    JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de actualizar Cliente.", "Error!", JOptionPane.ERROR_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtClientes);
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

        int fila = vistaMenu.jtClientes.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila a borrar.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
            limpiarCampos(vistaMenu);
            
        } else if (JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de eliminar este cliente?", "Borrar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt((String) vistaMenu.jtClientes.getValueAt(fila, 0).toString());

            clientdao.eliminar(id);
            JOptionPane.showMessageDialog(vistaMenu, "Cliente eliminado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
            limpiarCampos(vistaMenu);
        }
    }
    
     /**
     * Este metodo pone en modo edicion el formulario y carga los datos del
     * contacto a ser modificado.
     */
    
        public void editar() {
            
        vistaMenu.txtCliente_ID.enable(false);
        
        int fila = vistaMenu.jtClientes.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
            limpiarCampos(vistaMenu);
            
        }else {
            
            int id = Integer.parseInt((String) vistaMenu.jtClientes.getValueAt(fila, 0).toString());
            String Nombre = (String) vistaMenu.jtClientes.getValueAt(fila, 1);
            String Apellidos = (String) vistaMenu.jtClientes.getValueAt(fila, 2);
            String Cedula = (String) vistaMenu.jtClientes.getValueAt(fila, 3);
            String Telefono = (String) vistaMenu.jtClientes.getValueAt(fila, 4);
            

            vistaMenu.txtCliente_ID.setText("" + id);
            vistaMenu.txtCliente_Nombre.setText(Nombre);
            vistaMenu.txtCliente_Apellidos.setText(Apellidos);
            vistaMenu.txtCliente_Cedula.setText(Cedula);
            vistaMenu.txtCliente_Telefono.setText(Telefono);
            
            vistaMenu.txtCliente_Nombre.requestFocus();
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

        if (m.txtCliente_Nombre.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo nombre, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCliente_Nombre.requestFocus();
            validacion = 0;
            
        } else if (m.txtCliente_Apellidos.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo apellidos, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCliente_Apellidos.requestFocus();
            validacion = 0;
            
        } else if (m.txtCliente_Cedula.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo cedula, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCliente_Cedula.requestFocus();
            validacion = 0;
            
       
        } else if (m.txtCliente_Telefono.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo telefono, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtCliente_Telefono.requestFocus();
            
            validacion = 0;
            
       

    }
        return 0;
    
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
           m.txtTelefono.setText("");


       }
       
        /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        if (e.getSource() == vistaMenu.btnCliente_Nuevo) {
            nuevo();
            leer(vistaMenu.jtClientes);

        }if (e.getSource() == vistaMenu.btnCliente_Editar) {
            editar();
            this.vistaMenu.btnCliente_Editar.setEnabled(true);
            this.vistaMenu.btnCliente_Actualizar.setEnabled(true);
            this.vistaMenu.btnCliente_Nuevo.setEnabled(false);
            this.vistaMenu.btnCliente_Eliminar.setEnabled(true);
            
         }
        if (e.getSource() == vistaMenu.btnCliente_Actualizar) { // GUARDAR
            actualizar();
            this.vistaMenu.btnCliente_Actualizar.setEnabled(false);
            this.vistaMenu.btnCliente_Nuevo.setEnabled(true);
            this.vistaMenu.btnCliente_Eliminar.setEnabled(false);
            this.vistaMenu.btnCliente_Editar.setEnabled(true);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
            vistaMenu.txtCliente_Nombre.requestFocus();
        }
        if (e.getSource() == vistaMenu.btnCliente_Eliminar) {
            eliminar();
            this.vistaMenu.btnCliente_Nuevo.setEnabled(true);
            this.vistaMenu.btnCliente_Actualizar.setEnabled(false);
            this.vistaMenu.btnCliente_Eliminar.setEnabled(false);
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
        }
        if (e.getSource() == vistaMenu.btnCliente_Cancelar) {
            this.vistaMenu.btnCliente_Nuevo.setEnabled(true);
            this.vistaMenu.btnCliente_Editar.setEnabled(true);            
            this.vistaMenu.btnCliente_Actualizar.setEnabled(false);
            this.vistaMenu.btnCliente_Eliminar.setEnabled(true);
            vistaMenu.txtCliente_Buscar.setText("");
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtClientes);
        }
        if (e.getSource() == vistaMenu.btnCliente_Buscar) {
            
            if (!this.vistaMenu.txtCliente_Buscar.getText().equals("")) {
                this.vistaMenu.btnCliente_Nuevo.setEnabled(false);
                this.vistaMenu.btnCliente_Actualizar.setEnabled(true);
                this.vistaMenu.btnCliente_Cancelar.setEnabled(true);
                this.vistaMenu.btnCliente_Eliminar.setEnabled(true);
                limpiarCampos(vistaMenu);
                limpiarTabla();
                leerEmpleados(vistaMenu.jtClientes);
            } else {
                this.vistaMenu.btnCliente_Cancelar.setEnabled(true);
                JOptionPane.showMessageDialog(vistaMenu, "El campo de busqueda esta vacio.");
            }
        }
    
}
    
}






