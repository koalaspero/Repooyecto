/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

import java.util.ArrayList;


public class ProductosProveedor {
    private ArrayList<Product>productosProov= new ArrayList<>();
    
    public ProductosProveedor() {
        this.productosProov= productosProov;
    }
    
    
    public ArrayList<Product> buscarProducto(String nombre, String categoria, String idProveedor){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getIdProveedor().equals(idProveedor)){
                if(p.getNombreProd().equals(nombre) && p.getCategorias().contains(categoria)){
                    filtrados.add(p);
                }
            }
        }
        return filtrados;
    }
    
    public ArrayList<Product> buscarPorCatCli(String categoria){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getCategorias().contains(categoria)){
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public ArrayList<Product> buscarProdCli(String nombre, String categoria){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getNombreProd().equals(nombre) && p.getCategorias().contains(categoria)){
                filtrados.add(p);
            }
        }   
        return filtrados;
    }
    
    public ArrayList<Product> buscarPorPrecioCli(double precio1, double precio2){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getPrecio()>=precio1 && p.getPrecio()<=precio2){
                filtrados.add(p);
            }
        }
        return filtrados;
    }
    
    public ArrayList<Product> buscarPorCatPrecioCli(String categoria,double precio1, double precio2){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getCategorias().contains(categoria) && (p.getPrecio()>=precio1 && p.getPrecio()<=precio2)){
                filtrados.add(p);
            }
        }
        return filtrados;
    }
    
    public ArrayList<Product> buscarPorNomPrecioCli(String nombre,double precio1, double precio2){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getNombreProd().equalsIgnoreCase(nombre)){
                if(p.getPrecio()>=precio1 && p.getPrecio()<=precio2){
                    filtrados.add(p);
                }
            }
        }
        return filtrados;
    }
    
    public ArrayList<Product> buscarProdCatNombCli(String nombre, String categoria){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getNombreProd().equals(nombre) && p.getCategorias().contains(categoria)){
                filtrados.add(p);
            }
        }   
        return filtrados;
    }
    
    
    public ArrayList<Product> buscarPorNomCli(String nombre){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getNombreProd().equals(nombre)){
                filtrados.add(p);
            }
        }
        return filtrados;
    }
 
    
    public static void revisoFiltro(ArrayList<Product> p){
        if(p.isEmpty()){
            System.out.println("No hay productos disponibles.");
        }
        else{
            System.out.println("Filtrados: ");
        }
    } 
    
    public ArrayList<Product> buscarPorCat(String categoria, String idProveedor){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getIdProveedor().equals(idProveedor)){
                if(p.getCategorias().contains(categoria)){
                    filtrados.add(p);
                }
            }
        }
        return filtrados;
    }

    public ArrayList<Product> buscarPorNom(String nombre, String idProveedor){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getIdProveedor().equals(idProveedor)){
                if(p.getNombreProd().equals(nombre)){
                    filtrados.add(p);
                }
            }
        }
        return filtrados;
    }


    
    public ArrayList<Product> buscarPro(String idProveedor){
        ArrayList<Product> filtrados= new ArrayList<>();
        for(Product p: productosProov){
            if(p.getIdProveedor().equalsIgnoreCase(idProveedor)){
                filtrados.add(p);
            }
        }
        return filtrados;
    }
    
    public void mostrarFiltrados(ArrayList<Product> p){
        for(Product pro: p){
            System.out.print("Codigo: "+pro.getIdProducto()+" ,Precio: "+ pro.getPrecio()+ " ,Nombre: "+ pro.getNombreProd()+ " ,Categoría(s): "+ pro.getCategorias());
        }
    }
   

    public ArrayList<Product> getProductosProov() {
        return productosProov;
    }

    public void setProductosProov(ArrayList<Product> productosProov) {
        this.productosProov = productosProov;
    }

    public void agregarProducto(Product p){
	    productosProov.add(p);
    }

}

