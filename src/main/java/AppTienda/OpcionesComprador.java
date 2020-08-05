/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

import AppTiendaComp.Comprador;
import AppTiendaComp.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author HP PAVILION
 */
public class OpcionesComprador {
    private Product producto;
    private Comprador comprador;
    private Proveedor proveedor;
    private ProductosProveedor productosProv;
    
    public void filtrarProductoCat(String categoria){
        //ArrayList<Product> filtroProd = new ArrayList<>();
        ArrayList<Product> productProov = productosProv.getProductosProov();
        for(Product pro:productProov){
            if(pro.getCategorias().contains(categoria)){
                System.out.println(pro.toString());
            }
        }
    }
    
    public void filtrarProductoNombre(String nombre){
        //ArrayList<Product> filtroProd = new ArrayList<>();
        ArrayList<Product> productProov = productosProv.getProductosProov();
        for(Product pro:productProov){
            if(pro.getNombreProd().toLowerCase().contains(nombre.toLowerCase())){
                System.out.println(pro.toString());
            }
        }
    }
    
    public void filtrarProductoPrecio(double prec1,double prec2){
        //ArrayList<Product> filtroProd = new ArrayList<>();
        ArrayList<Product> productProov = productosProv.getProductosProov();
        for(Product pro:productProov){
            if(pro.getPrecio()>=prec1 && pro.getPrecio()<=prec2){
                System.out.println(pro.toString());
            }
        }
    }
    
    public void filtrarProductoProv(String idProv){
        //ArrayList<Product> filtroProd = new ArrayList<>();
        ArrayList<Product> productProov = productosProv.getProductosProov();
        for(Product pro:productProov){
            if(pro.getIdProveedor().equals(idProv)){
                System.out.println(pro.toString());
            }
        }
    }
    
    public void seleccionarProducto(String codigo){
       ArrayList<Product> productProov = productosProv.getProductosProov();
        for(Product pro:productProov){
            if(pro.getIdProducto().equals(codigo)){
                System.out.println("PRODUCTO: " + " Id: "+ pro.getIdProducto() + " Nombre: " + pro.getNombreProd()
                + " Precio: $" + pro.getPrecio());
            }
        } 
    }
 
}
