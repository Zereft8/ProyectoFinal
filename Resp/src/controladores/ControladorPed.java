
package controladores;

import Vistas.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.PedidosDAO;
import modelos.PedidosDTO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Elianny
 */

public class ControladorPed implements ActionListener {
    
    
     /**
      * Instanciando el Dao, Dto, la vista y un objeto de tipo DefaultTableModel para poder comunicarse con la base de datos y actualizar la vista
     */
    
    
    PedidosDAO pedao = new PedidosDAO();
    PedidosDTO pedto = new PedidosDTO();
    Menu vistaMenu = new Menu();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    /**
     * Constructor de la clase ControladorPed, se encarga de inicializar los
     * componentes del formulario de Empleados. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorPed (Menu m) {
        
        this.vistaMenu = m;
        
        this.vistaMenu.btnPedido_Nuevo.addActionListener(this);
        this.vistaMenu.btnPedido_Actualizar.addActionListener(this);
        this.vistaMenu.btnPedido_Eliminar.addActionListener(this);
        this.vistaMenu.btnPedido_Cancelar.addActionListener(this);
        this.vistaMenu.btnPedido_Buscar.addActionListener(this);
        this.vistaMenu.btnPedido_Editar.addActionListener(this);
        this.vistaMenu.btnPedido_Buscar.addActionListener(this);

        this.vistaMenu.btnPedido_Nuevo.setEnabled(true);
        this.vistaMenu.btnPedido_Actualizar.setEnabled(false);
        this.vistaMenu.btnPedido_Buscar.setEnabled(true);
        this.vistaMenu.btnPedido_Cancelar.setEnabled(true);
        this.vistaMenu.btnPedido_Eliminar.setEnabled(true);
        this.vistaMenu.btnPedido_Buscar.setEnabled(true);
        
        vistaMenu.txtPedidos_ID.enable(false);

     

        //Limpia el Jtable y Leer los clientes
        
        limpiarCampos(m);
        leer(vistaMenu.jtPedidos);
        
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * Pedidos.
     *
     * @param tabla
     */
    
    public void leerPedidos(JTable tabla) {
        
        limpiarTabla();
        
        modelo = (DefaultTableModel) tabla.getModel();
        List<PedidosDTO> lista = (List<PedidosDTO>) pedao.listarRegistro(vistaMenu.txtPedido__Buscar.getText());
        Object[] object = new Object[6];
        
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getDescripcion();
            object[2] = lista.get(i).getFecha_entrega();
            object[3] = lista.get(i).getFecha_pedidos();
            object[4] = lista.get(i).getId_clientes();
            object[5] = lista.get(i).getId_productos();

           
            
            modelo.addRow(object);
        }
        
        vistaMenu.jtPedidos.setModel(modelo);
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * pedidos.
     *
     * @param tabla
     */
        public final void leer(JTable tabla) {
            
            limpiarTabla();
            
            modelo = (DefaultTableModel) tabla.getModel();
            
            List<PedidosDTO> lista = (List<PedidosDTO>) pedao.listar();
            Object[] object = new Object[6];
            
            for (int i = 0; i < lista.size(); i++) {
                
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getDescripcion();
                object[2] = lista.get(i).getFecha_entrega();
                object[3] = lista.get(i).getFecha_pedidos();
                object[4] = lista.get(i).getId_clientes();
                object[5] = lista.get(i).getId_productos();

                
                modelo.addRow(object);
            }
            
            vistaMenu.jtPedidos.setModel(modelo);
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
        
        String descripcion = vistaMenu.txtPedidos_Descripcion.getText();
        Date fecha_entrega = Date.valueOf(vistaMenu.txtPedidos_Entrega.getText());
        Date fecha_pedidos = Date.valueOf(vistaMenu.txtPedidos_Fecha_realizado.getText());
        int id_clientes = Integer.parseInt(vistaMenu.txtPedidos_ID_Cliente.getText());
        int id_productos = Integer.parseInt(vistaMenu.txtPedidos_ID_Producto.getText());


        pedto.setDescripcion(descripcion);
        pedto.setFecha_entrega(fecha_entrega);
        pedto.setFecha_pedidos(fecha_pedidos);
        pedto.setId_clientes(id_clientes);
        pedto.setId_productos(id_productos);

                

        if (validarCampos(vistaMenu) > 0) {
            
            r = pedao.agregar(pedto);
            
            if (r == 1) {
                
                JOptionPane.showMessageDialog(vistaMenu, "Pedido registrado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtPedidos);
                limpiarCampos(vistaMenu);
                
            } else {
                
                JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de registrar pedido.", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtPedidos);
                limpiarCampos(vistaMenu);
            }
        }
    }
    
    
     /**
     * Este metodo se encarga de actualizar el contacto seleccionado dentro de
     * la tabla.
     * 
     */
    
    public void actualizar(){
        int r;

        int fila = vistaMenu.jtPedidos.getSelectedRow();
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la actualizacion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
            limpiarCampos(vistaMenu);
            
        } else {
            
            if (validarCampos(vistaMenu) > 0) {
                
                int id = Integer.parseInt(vistaMenu.txtPedidos_ID.getText().trim());
                String Descripcion = vistaMenu.txtPedidos_Descripcion.getText();
                String fecha_entrega = vistaMenu.txtPedidos_Entrega.getText();
                String fecha_pedido = vistaMenu.txtPedidos_Fecha_realizado.getText();
                int id_clientes = Integer.parseInt(vistaMenu.txtPedidos_ID_Cliente.getText().trim());
                int id_productos = Integer.parseInt(vistaMenu.txtPedidos_ID_Producto.getText().trim());
                
                
                SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfSalida = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    
                    java.util.Date fechaConvert_entrega = sdfEntrada.parse(fecha_entrega);
                    String fechaSqlStr = sdfSalida.format(fechaConvert_entrega);
                    Date fecha_Entrega = Date.valueOf(fechaSqlStr);
                    
                    java.util.Date fechaConvert_pedido = sdfEntrada.parse(fecha_pedido);
                    String fechaDb_str = sdfSalida.format(fechaConvert_pedido);
                    Date fecha_Pedido = Date.valueOf(fechaDb_str);
                    
                    pedto.setId(id);
                    pedto.setDescripcion(Descripcion);
                    pedto.setFecha_entrega(fecha_Entrega);
                    pedto.setFecha_pedidos(fecha_Pedido);
                    pedto.setId_clientes(id_clientes);
                    pedto.setId_productos(id_productos);
                    
                } catch (ParseException e) {
                    
                    e.printStackTrace();
                    
                }
                    
           
                r = pedao.actualizar(pedto);
                if (r == 1) {
                    
                    JOptionPane.showMessageDialog(vistaMenu, "Pedido actualizado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtPedidos);
                    limpiarCampos(vistaMenu);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de actualizar pedido.", "Error!", JOptionPane.ERROR_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtPedidos);
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

        int fila = vistaMenu.jtPedidos.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila a borrar.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
            limpiarCampos(vistaMenu);
            
        } else if (JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de eliminar este pedido ?", "Borrar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt((String) vistaMenu.jtPedidos.getValueAt(fila, 0).toString());

            pedao.eliminar(id);
            JOptionPane.showMessageDialog(vistaMenu, "Pedido eliminado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
            limpiarCampos(vistaMenu);
        }
    }
    
     /**
     * Este metodo pone en modo edicion el formulario y carga los datos del
     * contacto a ser modificado.
     */
    
        public void editar() {
            
        vistaMenu.txtUsuario_ID.enable(false);
        
        int fila = vistaMenu.jtPedidos.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
            limpiarCampos(vistaMenu);
            
        }else {
            
            //ESTA PARTE
            
            int id = Integer.parseInt((String) vistaMenu.jtPedidos.getValueAt(fila, 0).toString());
            
            String Descripcion = (String) vistaMenu.jtPedidos.getValueAt(fila, 1);
            Date fechaConvert = (Date) vistaMenu.jtPedidos.getValueAt(fila, 2);
            SimpleDateFormat sdfe = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_entrega = sdfe.format(fechaConvert);
            
            Date fechaConvert_2 = (Date) vistaMenu.jtPedidos.getValueAt(fila, 3);
            SimpleDateFormat sdfp = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_pedido = sdfp.format(fechaConvert_2);

            int id_clientes = Integer.parseInt((String) vistaMenu.jtPedidos.getValueAt(fila, 4).toString());
            int  id_productos = Integer.parseInt((String) vistaMenu.jtPedidos.getValueAt(fila, 5).toString());
            

            vistaMenu.txtPedidos_ID.setText(" " + id);
            vistaMenu.txtPedidos_Descripcion.setText(Descripcion);
            vistaMenu.txtPedidos_Entrega.setText(fecha_entrega);
            vistaMenu.txtPedidos_Fecha_realizado.setText(fecha_pedido);
            vistaMenu.txtPedidos_ID_Cliente.setText(" "+ id_clientes);
            vistaMenu.txtPedidos_ID_Producto.setText(" "+ id_productos);


            vistaMenu.txtPedidos_Descripcion.requestFocus();
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

        if (m.txtPedidos_Descripcion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo descripcion, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtPedidos_Descripcion.requestFocus();
            validacion = 0;
            
        } else if (m.txtPedidos_Entrega.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo Fecha de entrega, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtPedidos_Entrega.requestFocus();
            validacion = 0;
            
            
        } else if (m.txtPedidos_Fecha_realizado.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo fecha de pedido, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtPedidos_Fecha_realizado.requestFocus();
            validacion = 0;
            
        } else if (m.txtPedidos_ID_Cliente.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo empleado registrador, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtPedidos_ID_Cliente.requestFocus();
            validacion = 0;
        }

        
         else if (m.txtPedidos_ID_Producto.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo empleado registrador, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtPedidos_ID_Producto.requestFocus();
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

           m.txtPedidos_ID.setText("");
           m.txtPedidos_Descripcion.setText("");
           m.txtPedidos_Entrega.setText("");
           m.txtPedidos_Fecha_realizado.setText("");
           m.txtPedidos_ID_Cliente.setText("");
           m.txtPedidos_ID_Producto.setText("");
           m.txtPedidos_Descripcion.requestFocus();


       }
       
        /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        if (e.getSource() == vistaMenu.btnPedido_Nuevo) {
            nuevo();
            leer(vistaMenu.jtPedidos);

        }if (e.getSource() == vistaMenu.btnPedido_Editar) {
            editar();
            this.vistaMenu.btnPedido_Editar.setEnabled(true);
            this.vistaMenu.btnPedido_Actualizar.setEnabled(true);
            this.vistaMenu.btnPedido_Nuevo.setEnabled(false);
            this.vistaMenu.btnPedido_Eliminar.setEnabled(true);
            
         }
        if (e.getSource() == vistaMenu.btnPedido_Actualizar) { // GUARDAR
            actualizar();
            
            this.vistaMenu.btnPedido_Actualizar.setEnabled(false);
            this.vistaMenu.btnPedido_Nuevo.setEnabled(true);
            this.vistaMenu.btnPedido_Eliminar.setEnabled(false);
            this.vistaMenu.btnPedido_Editar.setEnabled(true);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
            vistaMenu.txtUsuario_Nombre.requestFocus();
        }
        if (e.getSource() == vistaMenu.btnPedido_Eliminar) {
            eliminar();
            this.vistaMenu.btnPedido_Nuevo.setEnabled(true);
            this.vistaMenu.btnPedido_Actualizar.setEnabled(false);
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
        }
        if (e.getSource() == vistaMenu.btnPedido_Cancelar) {
            
            this.vistaMenu.btnPedido_Nuevo.setEnabled(true);
            this.vistaMenu.btnPedido_Editar.setEnabled(true);            
            this.vistaMenu.btnPedido_Actualizar.setEnabled(false);
            this.vistaMenu.btnPedido_Eliminar.setEnabled(true);
            vistaMenu.txtPedido__Buscar.setText("");
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtPedidos);
        }
        if (e.getSource() == vistaMenu.btnPedido_Buscar) {
            
            if (!this.vistaMenu.txtPedido__Buscar.getText().equals("")) {
                this.vistaMenu.btnPedido_Nuevo.setEnabled(false);
                this.vistaMenu.btnPedido_Actualizar.setEnabled(true);
                this.vistaMenu.btnPedido_Cancelar.setEnabled(true);
                this.vistaMenu.btnPedido_Eliminar.setEnabled(true);
                limpiarCampos(vistaMenu);
                limpiarTabla();
                leerPedidos(vistaMenu.jtPedidos);
            } else {
                this.vistaMenu.btnPedido_Cancelar.setEnabled(true);
                JOptionPane.showMessageDialog(vistaMenu, "El campo de busqueda esta vacio.");
            }
        }
    
  }
    
}
