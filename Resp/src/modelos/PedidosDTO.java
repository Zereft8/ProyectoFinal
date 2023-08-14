/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author Elianny
 */
public class PedidosDTO {
     int id;
    String Descripcion;
    Date fecha_entrega;
    Date fecha_pedidos;
    int id_clientes;
     int id_productos;
     
     public PedidosDTO(){
    
    
    }
    
        public PedidosDTO(int id,  String Descripcion, Date fecha_entrega, Date fecha_pedidos, int id_clientes, int id_productos ){
    
        this.id = id;
        this.Descripcion = Descripcion;
        this.fecha_entrega = fecha_entrega;
        this.id_clientes = id_clientes;
        this.id_productos = id_productos;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    
    public Date getFecha_pedidos() {
        return fecha_pedidos;
    }

    public void setFecha_pedidos(Date fecha_pedidos) {
        this.fecha_pedidos = fecha_pedidos;
    }

    public int getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(int id_clientes) {
        this.id_clientes = id_clientes;
    }
    
    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }
    
    
    
}


