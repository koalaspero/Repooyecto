/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

import AppTiendaComp.Proveedor;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author HP PAVILION
 */
public class Product {
    private String idProducto;
    private String nombreProd;
    private ArrayList<String> categorias= new ArrayList<>();
    private double precio;
    private String descriProd;
    private String idProveedor;

    public Product(String idProducto, String nombreProd, ArrayList<String> categorias,double precio, String descriProd,String idProveedor) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.categorias= categorias;
        this.precio = precio;
        this.descriProd = descriProd;
        this.idProveedor = idProveedor;
    }


    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<String> categorias) {
        this.categorias = categorias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescriProd() {
        return descriProd;
    }

    public void setDescriProd(String descriProd) {
        this.descriProd = descriProd;
    }
    
    public String getIdProveedor(){
        return idProveedor;
    }
    
    public static boolean dividoCategoria(String categorias){
        if(categorias.toLowerCase().contains(",")){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.idProducto, other.idProducto)) {
            return false;
        }
        return true;
    }
    
    
    
    
    @Override
    public String toString(){
        return "Id: "+idProducto+"\n"+"Nombre: "+nombreProd+"\n"+"Categorias: "+categorias+"\n"+"Precio: $"+precio+"\n"+"Descripcion: "+ descriProd
        +"\n" +"Proveedor: " + idProveedor+"\n";
    }
}
