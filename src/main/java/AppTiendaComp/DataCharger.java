/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTiendaComp;

import AppTienda.Carrito;
import AppTienda.Coordenada;
import AppTienda.EstadoPedido;
import AppTienda.Pedido;
import AppTienda.Product;
import AppTienda.ProductosProveedor;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author HP PAVILION
 */
public class DataCharger{
    public ArrayList<Proveedor> proveedores= new ArrayList<>();
    public ArrayList<Comprador> clientes= new ArrayList<>();
    public ProductosProveedor pro= new ProductosProveedor();
    public ArrayList<Pedido> pedidos= new ArrayList<>();
    public ArrayList<Carrito> carros= new ArrayList<>();

    public DataCharger(){
        IniciarSistema();
    }
    
    private void IniciarSistema(){
        proveedores.add(new Proveedor("Roberto234", "12345", "412-61-7508", "Roberto Carlos", new Coordenada(-2.047585, -79.913632), "0987654321", "Thue1942@jourrapide.com"));
        proveedores.add(new Proveedor("Cristiano89", "password", "226-07-0765", "Ronaldo Cristiano", new Coordenada(-2.038800, -79.891752), "0987654329", "Messi1939@cuvox.de"));
        clientes.add(new Comprador("053-58-5053", "Rocio Mera", new Coordenada(-2.050644, -79.917811), "remera@espol.edu.ec", "Tarjeta","Forkoala", "12345"));
        
        pro.agregarProducto(new Product("Uoa94oNG", "CostillaSimple", new ArrayList<String>(), 5.00, "Esta costilla proviene de la parte de la res llamada costillar-falda", proveedores.get(0).getIdProveedor()) );
        pro.agregarProducto(new Product("qWUbgCXz","Brocoli", new ArrayList<String>(), 3.00, "El brócoli posee un alto contenido de vitamina C y es rico en fibras que ayudan a mejorar significativamente el tracto digestivo.", proveedores.get(0).getIdProveedor() ));
        pro.agregarProducto(new Product("ann3mWwx", "Lechuga", new ArrayList<String>(), 2.50, "La lechuga es la protagonista del plato más ligero y sencillo del recetario: la ensalada. ", proveedores.get(0).getIdProveedor()));
        ArrayList<String> categorias= new ArrayList<>();
        pro.getProductosProov().get(0).getCategorias().add("Carnicos");
        pro.getProductosProov().get(1).getCategorias().add("Vegetales");
        pro.getProductosProov().get(2).getCategorias().add("Vegetales");
        
        pro.getProductosProov().add(new Product("JbRdi3B7", "LecheEntera", new ArrayList<String>(), 1.10, "Hemos agregado Vitamina D adicional a este básico almacenable para la familia ¡Es leche entera sin preocupaciones!", proveedores.get(1).getIdProveedor()));
        pro.getProductosProov().add(new Product("AvhR5hbL", "ConservaDeDurazno", new ArrayList<String>(), 2.00, "Puede llevar duraznos frescos a sus salidas cortas, paseos al parque y meriendas de los niños.", proveedores.get(1).getIdProveedor()));
        pro.getProductosProov().add(new Product("LKyeotUR", "QuesoFresco", new ArrayList<String>(), 2.50, "Nuestro Queso Fresco es de consistencia blanda, color blanco y sabor suave.", proveedores.get(1).getIdProveedor()));
        pro.getProductosProov().get(3).getCategorias().add("Lacteos");
        pro.getProductosProov().get(4).getCategorias().add("Frutas"); pro.getProductosProov().get(4).getCategorias().add("Conservas");
        pro.getProductosProov().get(5).getCategorias().add("Lacteos");
    }
    

    public boolean checkListCli(String u, String p){
        for(Comprador c: clientes){
            boolean valorVerdad= c.checkLogin(u, p);
            if (valorVerdad){
                return true;
            }
        }
        return false;
    }    
    
    public boolean checkListPro(String u, String p){
        for(Proveedor pro: proveedores){
            boolean valorVerdad= pro.checkLogin(u, p);
            if (valorVerdad){
                return true;
            }
        }
        return false;
    }
    
    
    
    public ArrayList<String> formaCat(String linea, boolean b){
        ArrayList<String> categorias= new ArrayList<>();
        if(b){
            String[] cateprima= linea.split(",");
            for(String s: cateprima){
                categorias.add(s);
            }
        }else{
            categorias.add(linea);
        }
        return categorias;
    }
    
    public String buscarProveedor(String user, String pass){
        for(Proveedor p: proveedores){
            if(user.equals(p.getNom()) && pass.equals(p.getPass())){
                return p.getIdProveedor();
            }
        }
        return null;
    }
    
    public Product ubicarProducto(String code){
        for(Product p: pro.getProductosProov()){
            if(p.getIdProducto().equals(code)){
                return p;
            }
        }
        return null;
    }
    
    public void editoListProd(Product p){
        Iterator<Product> iter= pro.getProductosProov().iterator();
        while(iter.hasNext()){
            Product pro= iter.next();
            if(pro.equals(p)){
                iter.remove();
            }
        }
        pro.getProductosProov().add(p);
    }

    
    public void cambiaDireccion(Comprador c, String d){
        for(Comprador cl: clientes){
            if(c.getIdComprador().equals(c)){
                c.setDireccion(d);
            }
        }
    }
    
    public void seteoTodo(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese el codigo del producto a editar: ");
        String code= sc.next();
        Product p= this.ubicarProducto(code);
        System.out.println("Ingresa lo que deseas editar (Precio, nombre, descripcion, categoria(s): ");
        String campo= sc.next();
        System.out.println("Ingresa la linea con el cambio a realizar: ");
        String cambio= sc.next();
        if(campo.equalsIgnoreCase("Precio")){
            double n= Double.parseDouble(cambio);
            p.setPrecio(n);
        }else if(campo.equalsIgnoreCase("Nombre")){
            p.setNombreProd(cambio);
        }else if(campo.equalsIgnoreCase("Categoria")){
            boolean dividono= Product.dividoCategoria(cambio);
            ArrayList<String> cateLista= this.formaCat(cambio, dividono);
            p.setCategorias(cateLista);
        }else if(campo.equalsIgnoreCase("Descripcion")){
            p.setDescriProd(cambio);
        }
        this.editoListProd(p);
    }
    
    
    public ArrayList<Pedido> pedidosProveedor(String id){
        ArrayList<Pedido> pedidosProv= new ArrayList<>();
        for(Pedido p: pedidos){
            if(p.getIdProveedor().equals(id)){
                pedidosProv.add(p);
            }
        }
        return pedidosProv;
    }
    
    public boolean checkPed(String id){
        for(Pedido p: pedidos){
            if(p.getIdProveedor().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public void cambiarEstado(String c, String estado){
        for(Pedido p: pedidos){
            if(p.getCodigo().equalsIgnoreCase(c)){
                EstadoPedido e= EstadoPedido.valueOf(estado);
                p.setEstado(e);
                if(estado.equals("Procesando")){
                    p.setFecha(p.getFecha().plus(5, ChronoUnit.MINUTES));
                    System.out.println("El estado del pedido fue cambiado a Procesando: "+ p.getFecha());
                }else if(estado.equals("Despachado")){
                    p.setFecha(p.getFecha().plus(25, ChronoUnit.MINUTES));
                    System.out.println("El estado del pedido fue cambiado a Despachado: "+ p.getFecha());
                }
            }
        }
    }
    
    public Coordenada ubiCom(String user){
        for(Comprador c: clientes){
            if(c.getNom().equals(user)){
                return c.getUbicacion();
            }
        }
        return null;
    }
    
    public Comprador buscarComprador(String user, String pass){
        for(Comprador c: clientes){
            if(user.equals(c.getNom()) && pass.equals(c.getPass())){
                return c;
            }
        }
        return null;
    }
    

    public ArrayList<Product> ubiCincuenta(Coordenada c, String tipo, String filtro){
        ArrayList<Product> prodCli= new ArrayList<>();
        for(Proveedor p: proveedores){
            if(Coordenada.calcularDistanciaDospuntos(p.getUbicacionProv(), c)<=50){
               if(tipo.equals("1")){
                   for(Product pd: pro.buscarPorCatCli(filtro)){
                       prodCli.add(pd);
                   }
               }else if(tipo.equals("2")){
                   for(Product pd: pro.buscarPorNomCli(filtro)){
                       prodCli.add(pd);
                   }
               }else if(tipo.equals("3")){
                   String[] filtros= filtro.split(",");
                   double precio1= Double.valueOf(filtros[0]);
                   double precio2= Double.valueOf(filtros[1]);
                   for(Product pd: pro.buscarPorPrecioCli(precio1,precio2)){
                       prodCli.add(pd);
                   }
               }else if(tipo.equals("4")){
                   String[] filtros= filtro.split(",");
                   String f1= filtros[0];
                   String f2= filtros[1];
                   for(Product pd: pro.buscarProdCatNombCli(f1, f2)){
                       prodCli.add(pd);
                   }
                }else if(tipo.equals("5")){
                   String[] filtros= filtro.split(",");
                   String cat= filtros[0];
                   double precio1 = Double.valueOf(filtros[1]);
                   double precio2 = Double.valueOf(filtros[2]);
                   for(Product pd:pro.buscarPorCatPrecioCli(cat,precio1,precio2)){
                       prodCli.add(pd);
                   }
                }else if(tipo.equals("6")){
                   String[] filtros= filtro.split(",");
                   String nombre= filtros[0];
                   double precio1 = Double.valueOf(filtros[1]);
                   double precio2 = Double.valueOf(filtros[2]);
                   for(Product pd:pro.buscarPorNomPrecioCli(nombre,precio1,precio2)){
                       prodCli.add(pd);
                   }
                }
                    
            }
        }
        return prodCli;
    }
    
    public void agregarPedidos(ArrayList<Pedido> p){
        for(Pedido ped: p){
            pedidos.add(ped);
        }
    }
    
}




