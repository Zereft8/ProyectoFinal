
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Vistas.Menu;
import javax.swing.table.DefaultTableModel;
import modelos.ProductosDAO;
import modelos.ProductosDTO;



/**
 *
 * @author Elianny
 */
public class ControladorProd implements ActionListener{
    
    
     /**
      * Instanciando el Dao, Dto, la vista y un objeto de tipo DefaultTableModel para poder comunicarse con la base de datos y actualizar la vista
     */
    
    
    ProductosDAO proddao = new ProductosDAO();
    ProductosDTO proddto = new ProductosDTO();
    Menu vistaMenu = new Menu();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    /**
     * Constructor de la clase Controlador, se encarga de inicializar los
     * componentes del formulario de Producto. Recibe como parameto la ventana
     * "Menu".
     *
     * @param m
     */
    
    public ControladorProd(Menu m) {
        
        this.vistaMenu = m;
        
        
        this.vistaMenu.btnProducto_Nuevo.addActionListener(this);
        this.vistaMenu.btnProducto_Actualizar.addActionListener(this);
        this.vistaMenu.btnProducto_Eliminar.addActionListener(this);
        this.vistaMenu.btnProducto_Cancelar.addActionListener(this);
        this.vistaMenu.btnProductos_Buscar.addActionListener(this);
        this.vistaMenu.btnProducto_Editar.addActionListener(this);
        this.vistaMenu.txtProductos_Buscar.addActionListener(this);

        this.vistaMenu.btnProducto_Nuevo.setEnabled(true);
        this.vistaMenu.btnProducto_Actualizar.setEnabled(false);
        this.vistaMenu.btnProductos_Buscar.setEnabled(true);
        this.vistaMenu.btnProducto_Cancelar.setEnabled(true);
        this.vistaMenu.btnProducto_Eliminar.setEnabled(true);
        this.vistaMenu.btnProductos_Buscar.setEnabled(true);
        
        vistaMenu.txtIProducto_ID.enable(false);

        
     

        //Limpia el Jtable y Leer los clientes
        
        limpiarCampos(m);
        
        leer(vistaMenu.jtProductos);
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * Productos.
     *
     * @param tabla
     */
    
    public void leerProductos(JTable tabla) {
        
        limpiarTabla();
        
        modelo = (DefaultTableModel) tabla.getModel();
        List<ProductosDTO> lista = (List<ProductosDTO>) proddao.listarRegistro(vistaMenu.txtProductos_Buscar.getText());
        Object[] object = new Object[3];
        
        for (int i = 0; i < lista.size(); i++) {
            
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getPrecio();
         
           
            
            modelo.addRow(object);
        }
        
        vistaMenu.jtProductos.setModel(modelo);
    }
    
    
    /**
     * Este metodo se encarga de cargar los registros dentro de la tabla de
     * productos.
     *
     * @param tabla
     */
        public final void leer(JTable tabla) {
            
            limpiarTabla();
            
            modelo = (DefaultTableModel) tabla.getModel();
            List<ProductosDTO> lista = (List<ProductosDTO>) proddao.listar();
            Object[] object = new Object[3];
            
            for (int i = 0; i < lista.size(); i++) {
                
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getNombre();
                object[2] = lista.get(i).getPrecio();
               
                
                modelo.addRow(object);
            }
            vistaMenu.jtProductos.setModel(modelo);
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
     * Este metodo se encarga de hacer un nuevo registro.
     */
    
    public void nuevo() {

        int r;

        String Nombre = vistaMenu.txtProducto_Nombre.getText();
        float Precio = Float.parseFloat(vistaMenu.txtProducto_Precio.getText());
        
    
        proddto.setNombre(Nombre);
        proddto.setPrecio(Precio);
        
                

        if (validarCampos(vistaMenu) > 0) {
            
            r = proddao.agregar(proddto);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaMenu, "Producto registrado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtProductos);
                limpiarCampos(vistaMenu);
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de registrar producto.", "Error!", JOptionPane.ERROR_MESSAGE);
                limpiarTabla();
                leer(vistaMenu.jtProductos);
                limpiarCampos(vistaMenu);
            }
        }
    }
    
    
     /**
     * Este metodo se encarga de actualizar el cliente seleccionado dentro de
     * la tabla.
     */
    
    public void actualizar() {
        int r;

        int fila = vistaMenu.jtProductos.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la actualizacion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
            limpiarCampos(vistaMenu);
        } else {
            
            if (validarCampos(vistaMenu) > 0) {
                
                int id = Integer.parseInt(vistaMenu.txtIProducto_ID.getText());
                String Nombre = vistaMenu.txtProducto_Nombre.getText();
                float Precio = Float.parseFloat(vistaMenu.txtProducto_Precio.getText());
                
                
                Object obj = vistaMenu.jtProductos.getValueAt(fila, 2);

                
                if (obj != null) {
                    
                    
                    proddto.setId(id);
                    proddto.setNombre(Nombre);
                    proddto.setPrecio(Precio);
                    
                    
                }else{
                
                    proddto.setId(id);
                    proddto.setNombre(Nombre);
                    proddto.setPrecio(Precio);
                
                }
                


                r = proddao.actualizar(proddto);
                if (r == 1) {
                    JOptionPane.showMessageDialog(vistaMenu, "Producto actualizado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtProductos);
                    limpiarCampos(vistaMenu);
                } else {
                    JOptionPane.showMessageDialog(vistaMenu, "Error: tratando de actualizar Producto.", "Error!", JOptionPane.ERROR_MESSAGE);
                    limpiarTabla();
                    leer(vistaMenu.jtProductos);
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

        int fila = vistaMenu.jtProductos.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila a borrar.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
            limpiarCampos(vistaMenu);
            
        } else if (JOptionPane.showConfirmDialog(vistaMenu, "Esta seguro de eliminar este producto?", "Borrar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt((String) vistaMenu.jtProductos.getValueAt(fila, 0).toString());

            proddao.eliminar(id);
            JOptionPane.showMessageDialog(vistaMenu, "Producto eliminado con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
            limpiarCampos(vistaMenu);
        }
    }
    
     /**
     * Este metodo pone en modo edicion el formulario y carga los datos del
     * producto a ser modificado.
     */
    
        public void editar() {
                    
        int fila = vistaMenu.jtProductos.getSelectedRow();

        if (fila == -1) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
            limpiarCampos(vistaMenu);
            
        }else {
            
            int id = Integer.parseInt((String) vistaMenu.jtProductos.getValueAt(fila, 0).toString());
            String Nombre = (String) vistaMenu.jtProductos.getValueAt(fila, 1);
            float Precio = Float.parseFloat((String) vistaMenu.jtProductos.getValueAt(fila, 2).toString());
           
            vistaMenu.txtIProducto_ID.setText("" + id);
            vistaMenu.txtProducto_Nombre.setText(Nombre);
            vistaMenu.txtProducto_Precio.setText(String.valueOf(Precio));

           

            vistaMenu.txtProducto_Nombre.requestFocus();
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

        if (m.txtProducto_Nombre.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo nombre, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtProducto_Nombre.requestFocus();
            validacion = 0;
            
        } else if (m.txtProducto_Precio.getText().equals("")) {
            
            JOptionPane.showMessageDialog(vistaMenu, "Favor verificar campo Precio, no puede estar vacio.", "Error!", JOptionPane.ERROR_MESSAGE);
            m.txtProducto_Precio.requestFocus();
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

           m.txtIProducto_ID.setText("");
           m.txtProducto_Nombre.setText("");
           m.txtProducto_Precio.setText("");
           m.txtProducto_Nombre.requestFocus();


       }
       
        /**
     * Este metodo verifica si se ha producido algun evento dentro del
     * formulario.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        if (e.getSource() == vistaMenu.btnProducto_Nuevo) {
            nuevo();
            leer(vistaMenu.jtProductos);

        }if (e.getSource() == vistaMenu.btnProducto_Editar) {
            editar();
            this.vistaMenu.btnProducto_Editar.setEnabled(true);
            this.vistaMenu.btnProducto_Actualizar.setEnabled(true);
            this.vistaMenu.btnProducto_Nuevo.setEnabled(false);
            this.vistaMenu.btnProducto_Eliminar.setEnabled(true);
            
         }
        if (e.getSource() == vistaMenu.btnProducto_Actualizar) { // GUARDAR
            actualizar();
            this.vistaMenu.btnProducto_Actualizar.setEnabled(false);
            this.vistaMenu.btnProducto_Nuevo.setEnabled(true);
            this.vistaMenu.btnProducto_Eliminar.setEnabled(false);
            this.vistaMenu.btnProducto_Editar.setEnabled(true);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
            vistaMenu.txtProducto_Nombre.requestFocus();
        }
        if (e.getSource() == vistaMenu.btnProducto_Eliminar) {
            eliminar();
            this.vistaMenu.btnProducto_Nuevo.setEnabled(true);
            this.vistaMenu.btnProducto_Actualizar.setEnabled(false);
            this.vistaMenu.btnProducto_Eliminar.setEnabled(false);
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
        }
        if (e.getSource() == vistaMenu.btnProducto_Cancelar) {
            
            this.vistaMenu.btnProducto_Nuevo.setEnabled(true);
            this.vistaMenu.btnProducto_Editar.setEnabled(true);            
            this.vistaMenu.btnProducto_Actualizar.setEnabled(false);
            this.vistaMenu.btnProducto_Eliminar.setEnabled(true);
            vistaMenu.txtProductos_Buscar.setText("");
            limpiarCampos(vistaMenu);
            limpiarTabla();
            leer(vistaMenu.jtProductos);
        }
        if (e.getSource() == vistaMenu.btnProductos_Buscar) {
            
            if (!this.vistaMenu.txtProductos_Buscar.getText().equals("")) {
                this.vistaMenu.btnProducto_Nuevo.setEnabled(false);
                this.vistaMenu.btnProducto_Actualizar.setEnabled(true);
                this.vistaMenu.btnProducto_Cancelar.setEnabled(true);
                this.vistaMenu.btnProducto_Eliminar.setEnabled(true);
                limpiarCampos(vistaMenu);
                limpiarTabla();
                leerProductos(vistaMenu.jtProductos);
            } else {
                this.vistaMenu.btnProducto_Cancelar.setEnabled(true);
                JOptionPane.showMessageDialog(vistaMenu, "El campo de busqueda esta vacio.");
            }
        }
    
}
    
}





    

