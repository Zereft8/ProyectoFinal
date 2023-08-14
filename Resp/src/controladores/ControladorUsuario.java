
package controladores;

import Vistas.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.UsuarioDAO;
import modelos.UsuarioDTO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */

public class ControladorUsuario implements ActionListener {
    
    
    /**
     * Declaracion de los objetos necesarios: DAO = Data Access Object empdao =
     * Estructura de datos del objeto vistaMenu = Formulario de Empleados modelo =
     * Modelo donde se cargan los datos de la tabla
     */
    
    UsuarioDAO usdao = new UsuarioDAO();
    UsuarioDTO usdto = new UsuarioDTO();
    Menu vistaMenu = new Menu();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    /**
     * Constructor de la clase Controlador, se encarga de inicializar los
     * componentes del formulario de Empleados. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorUsuario(Menu m) {
        
        this.vistaMenu = m;
        
        this.vistaMenu.btnUsuario_Nuevo.addActionListener(this);
        this.vistaMenu.btnUsuario_Actualizar.addActionListener(this);
        this.vistaMenu.btnUsuario_Eliminar.addActionListener(this);
        this.vistaMenu.btnUsuario_Cancelar.addActionListener(this);
        this.vistaMenu.btnUsuario_Buscar.addActionListener(this);
        this.vistaMenu.btnUsuario_Editar.addActionListener(this);
        this.vistaMenu.btnUsuario_Buscar.addActionListener(this);

        this.vistaMenu.btnUsuario_Nuevo.setEnabled(true);
        this.vistaMenu.btnUsuario_Actualizar.setEnabled(false);
        this.vistaMenu.btnUsuario_Buscar.setEnabled(true);
        this.vistaMenu.btnUsuario_Cancelar.setEnabled(true);
        this.vistaMenu.btnUsuario_Eliminar.setEnabled(true);
        this.vistaMenu.btnUsuario_Buscar.setEnabled(true);
        
        vistaMenu.txtUsuario_ID.enable(false);

     

        //Limpiar formulario y Listar contactos
        
        limpiarCampos(m);
        leer(vistaMenu.jtUsuarios);
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
        List<UsuarioDTO> lista = (List<UsuarioDTO>) usdao.listarRegistro(vistaMenu.txtUsuario_Buscar.getText());
        Object[] object = new Object[6];
        
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getPassword();
            object[2] = lista.get(i).getUsuario();
            object[3] = lista.get(i).getTipo_usuario();
            object[4] = lista.get(i).getFecha_registro();
            object[5] = lista.get(i).getId_empleados();

           
            
            modelo.addRow(object);
        }
        
        vistaMenu.jtUsuarios.setModel(modelo);
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
            
            List<UsuarioDTO> lista = (List<UsuarioDTO>) usdao.listar();
            Object[] object = new Object[6];
            
            for (int i = 0; i < lista.size(); i++) {
                
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getPassword();
                object[2] = lista.get(i).getUsuario();
                object[3] = lista.get(i).getTipo_usuario();
                object[4] = lista.get(i).getFecha_registro();
                object[5] = lista.get(i).getId_empleados();
                
                modelo.addRow(object);
            }
            vistaMenu.jtUsuarios.setModel(modelo);
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
        
        String password = vistaMenu.txtUsuario_Contraseña.getText();
        String nombre_usuario = vistaMenu.txtUsuario_Nombre.getText();
        String tipo_usuario = (String) vistaMenu.jComboBox_Usuario.getSelectedItem();
        Date fecha_registro = Date.valueOf(vistaMenu.txtUsuario_Fecha.getText());
        int id_empleados = Integer.parseInt(vistaMenu.txtIUsuario_ID_Empleado.getText());


        usdto.setPassword(password);
        usdto.setUsuario(nombre_usuario);
        usdto.setTipo_usuario(tipo_usuario);
        usdto.setFecha_registro(fecha_registro);
        usdto.setId_empleados(id_empleados);

                

        if (validarCampos(vistaMenu) > 0) {
            
            r = usdao.agregar(usdto);
            
            if (r == 1) {
                
                JOptionPane.showMessageDialog(vistaMenu, "Usuario registrado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtUsuarios);
                limpiarCampos(vistaMenu);
                
            } else {
                
                JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de registrar usuario.", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtUsuarios);
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

        int fila = vistaMenu.jtUsuarios.getSelectedRow();
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la actualizacion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
            limpiarCampos(vistaMenu);
            
        } else {
            
            if (validarCampos(vistaMenu) > 0) {
                
                int id = Integer.parseInt(vistaMenu.txtUsuario_ID.getText());
                String password = vistaMenu.txtUsuario_Contraseña.getText();
                String nombre_usuario = vistaMenu.txtUsuario_Nombre.getText();
                String tipo_usuario = vistaMenu.jComboBox_Usuario.getToolTipText();
                String fecha_registro = vistaMenu.txtUsuario_Fecha.getText();
                int id_empleados = Integer.parseInt(vistaMenu.txtIUsuario_ID_Empleado.getText());
                
                
                SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfSalida = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    
                    java.util.Date fechaUtil = sdfEntrada.parse(fecha_registro);
                    String fechaSqlStr = sdfSalida.format(fechaUtil);
                    Date fecharegistro = Date.valueOf(fechaSqlStr);

                    usdto.setId(id);
                    usdto.setPassword(password);
                    usdto.setUsuario(nombre_usuario);
                    usdto.setTipo_usuario(tipo_usuario);
                    usdto.setFecha_registro(fecharegistro);
                    usdto.setId_empleados(id_empleados);
                    
                } catch (ParseException e) {
                    
                    e.printStackTrace();
                    
                }
                



                r = usdao.actualizar(usdto);
                if (r == 1) {
                    
                    JOptionPane.showMessageDialog(vistaMenu, "Usuario actualizado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtUsuarios);
                    limpiarCampos(vistaMenu);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de actualizar usuario.", "Error!", JOptionPane.ERROR_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtUsuarios);
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

        int fila = vistaMenu.jtUsuarios.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila a borrar.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
            limpiarCampos(vistaMenu);
            
        } else if (JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de eliminar este usuario ?", "Borrar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt((String) vistaMenu.jtUsuarios.getValueAt(fila, 0).toString());

            usdao.eliminar(id);
            JOptionPane.showMessageDialog(vistaMenu, "Usuario eliminado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
            limpiarCampos(vistaMenu);
        }
    }
    
     /**
     * Este metodo pone en modo edicion el formulario y carga los datos del
     * contacto a ser modificado.
     */
    
        public void editar() {
            
        vistaMenu.txtUsuario_ID.enable(false);
        
        int fila = vistaMenu.jtUsuarios.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
            limpiarCampos(vistaMenu);
            
        }else {
            
            int id = Integer.parseInt((String) vistaMenu.jtUsuarios.getValueAt(fila, 0).toString());
            String password = (String) vistaMenu.jtUsuarios.getValueAt(fila, 1);
            String nombre_usuario = (String) vistaMenu.jtUsuarios.getValueAt(fila, 2);
            String tipo_usuario = (String) vistaMenu.jtUsuarios.getValueAt(fila, 3);
            
            Date fechaSQL = (Date) vistaMenu.jtUsuarios.getValueAt(fila, 4);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaStr = sdf.format(fechaSQL);

            int id_empleados = Integer.parseInt((String) vistaMenu.jtUsuarios.getValueAt(fila, 5).toString());
            

            vistaMenu.txtUsuario_ID.setText("" + id);
            vistaMenu.txtUsuario_Contraseña.setText(password);
            vistaMenu.txtUsuario_Nombre.setText(nombre_usuario);
            vistaMenu.jComboBox_Usuario.setToolTipText(tipo_usuario);
            vistaMenu.txtUsuario_Fecha.setText(fechaStr);
            vistaMenu.txtIUsuario_ID_Empleado.setText(" "+ id_empleados);


            vistaMenu.txtUsuario_Nombre.requestFocus();
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

        if (m.txtUsuario_Contraseña.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo contraseña, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtUsuario_Contraseña.requestFocus();
            validacion = 0;
            
        } else if (m.txtUsuario_Nombre.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo nombre de usuario, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtUsuario_Nombre.requestFocus();
            validacion = 0;
            
            
        } else if (m.txtUsuario_Fecha.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo fecha, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtUsuario_Fecha.requestFocus();
            validacion = 0;
            
        } else if (m.txtIUsuario_ID_Empleado.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo empleado registrador, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtIUsuario_ID_Empleado.requestFocus();
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

           m.txtUsuario_ID.setText("");
           m.txtUsuario_Contraseña.setText("");
           m.txtUsuario_Nombre.setText("");
           m.jComboBox_Usuario.setToolTipText("");
           m.txtUsuario_Fecha.setText("");
           m.txtIUsuario_ID_Empleado.setText("");
           m.txtUsuario_Nombre.requestFocus();


       }
       
        /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        if (e.getSource() == vistaMenu.btnUsuario_Nuevo) {
            nuevo();
            leer(vistaMenu.jtUsuarios);

        }if (e.getSource() == vistaMenu.btnUsuario_Editar) {
            editar();
            this.vistaMenu.btnUsuario_Editar.setEnabled(true);
            this.vistaMenu.btnUsuario_Actualizar.setEnabled(true);
            this.vistaMenu.btnUsuario_Nuevo.setEnabled(false);
            this.vistaMenu.btnUsuario_Eliminar.setEnabled(true);
            
         }
        if (e.getSource() == vistaMenu.btnUsuario_Actualizar) { // GUARDAR
            actualizar();
            
            this.vistaMenu.btnUsuario_Actualizar.setEnabled(false);
            this.vistaMenu.btnUsuario_Nuevo.setEnabled(true);
            this.vistaMenu.btnUsuario_Eliminar.setEnabled(false);
            this.vistaMenu.btnUsuario_Editar.setEnabled(true);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
            vistaMenu.txtUsuario_Nombre.requestFocus();
        }
        if (e.getSource() == vistaMenu.btnUsuario_Eliminar) {
            eliminar();
            this.vistaMenu.btnUsuario_Nuevo.setEnabled(true);
            this.vistaMenu.btnUsuario_Actualizar.setEnabled(false);
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
        }
        if (e.getSource() == vistaMenu.btnUsuario_Cancelar) {
            this.vistaMenu.btnUsuario_Nuevo.setEnabled(true);
            this.vistaMenu.btnUsuario_Editar.setEnabled(true);            
            this.vistaMenu.btnUsuario_Actualizar.setEnabled(false);
            this.vistaMenu.btnUsuario_Eliminar.setEnabled(true);
            vistaMenu.txtUsuario_Buscar.setText("");
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtUsuarios);
        }
        if (e.getSource() == vistaMenu.btnUsuario_Buscar) {
            
            if (!this.vistaMenu.txtUsuario_Buscar.getText().equals("")) {
                this.vistaMenu.btnUsuario_Nuevo.setEnabled(false);
                this.vistaMenu.btnUsuario_Actualizar.setEnabled(true);
                this.vistaMenu.btnUsuario_Cancelar.setEnabled(true);
                this.vistaMenu.btnUsuario_Eliminar.setEnabled(true);
                limpiarCampos(vistaMenu);
                limpiarTabla();
                leerEmpleados(vistaMenu.jtUsuarios);
            } else {
                this.vistaMenu.btnUsuario_Cancelar.setEnabled(true);
                JOptionPane.showMessageDialog(vistaMenu, "El campo de busqueda esta vacio.");
            }
        }
    
  }
}
