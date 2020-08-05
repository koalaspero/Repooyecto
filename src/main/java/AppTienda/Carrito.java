/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

import AppTiendaComp.Comprador;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

/**
 *
 * @author HP PAVILION
 */
public class Carrito {
    private ArrayList<Product> productos;
    

    public Carrito() {
        productos= new ArrayList<>();
    }
    
    
    public void agregarProducto(ArrayList<Product> ps){
        for(Product p: ps){
            productos.add(p);
        }
    }
    public void consultarCarrito(){
        double subtotal = 0;
        double total= 0;
        Set<Product> unicos= new HashSet<Product>(productos);
        for(Product key: unicos){
            int veces= Collections.frequency(productos, key);
            subtotal += key.getPrecio() * veces;
            System.out.println(key.getIdProducto()+" "+key.getNombreProd()+" "+veces+" "+ key.getPrecio()+" "+subtotal);
        }
        total=subtotal;
        System.out.print("Total a pagar: "+total);
    }
   
    public void quitarProducto(String codigo){
        Iterator<Product> iter= productos.iterator();
        while(iter.hasNext()){
            Product pro= iter.next();
            if(pro.getIdProducto().equalsIgnoreCase(codigo)){
                iter.remove();
            }
        }
    }
    
    public double calcularTotal(){
        double total = 0;
        for(Product pro:productos){
            total += pro.getPrecio();
        }
        return total;
    }
    
    public ArrayList<Pedido> generarPedidos(Comprador c){
        ArrayList<Pedido> pedidos= new ArrayList<>();
        ArrayList<String> proveedores= new ArrayList<>();
        ArrayList<String> codeProd= new ArrayList<>();
        int n= -1;
        for(Product p: productos){
            if (!proveedores.contains(p.getIdProveedor())){
                n += 1;
                pedidos.add(new Pedido(LocalDateTime.now(),Generador.RequiredString(9), c, new ArrayList<Product>(), p.getIdProveedor()));
                pedidos.get(n).getProductos().add(p);
                pedidos.get(n).setTotal(p.getPrecio());
                proveedores.add(p.getIdProveedor());
                codeProd.add(p.getIdProducto());
            }else{
                int indicador= proveedores.indexOf(p.getIdProveedor());
                pedidos.get(indicador).setTotal(pedidos.get(indicador).getTotal()+p.getPrecio());
                if(!codeProd.contains(p.getIdProducto())){
                    pedidos.get(indicador).getProductos().add(p);
                }
            }
        }
        return pedidos;
    }
    
        public void generarCorreo(Comprador c){
        String remitente = "nelsona449@gmail.com";
        String contrasenia = "nelsongabriel";
        String asunto = "Detalle de Compra";
        ArrayList<Pedido> pedidoCliente = generarPedidos(c);
        double totalPagar = calcularTotal();
        int cont1 = 1;
        boolean validar = true;
        int contFor = 0 ;
        int tam = pedidoCliente.size();
        String tam1 = String.valueOf(tam);
        String mensajePrinc = "Detalles de la Compra\n" + "Su compra contiene "
                    + tam1 + " pedidos";
        //ArrayList<String> mensajes = new ArrayList<>();
        for(Pedido ped:pedidoCliente){
            ArrayList<Product> producto = ped.getProductos();
            String mensaje1 = "PEDIDO " + cont1 + " del " + ped.getIdProveedor()+ "\n";
            for(Product pro:productos){
                String mensaje2 = "PRODUCTO:  Nombre: " + pro.getNombreProd() + " Precio: "
                        + pro.getPrecio();
                mensaje1 += mensaje2 + "\n";
                
            }
            mensajePrinc += mensaje1 + "\n";
            ++cont1;
        }
        
        mensajePrinc += "Total de pago por la Compra: $" + totalPagar + "\n";
        
        MailEstructura mail = new MailEstructura(remitente, contrasenia, c.getCorreo(), asunto, mensajePrinc);
        
        mail.enviarCorreo();
    }
    
    
}
