package AppTienda;

import AppTiendaComp.Comprador;
import MetodosdePago.MetodoPago;
import MetodosdePago.PagoPaypal;
import MetodosdePago.PagoTarjeta;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
    private LocalDateTime fecha;
    private Comprador cliente;
    private String codigo;
    private ArrayList<Product> productos;
    private String idProveedor;
    private EstadoPedido estado;
    private double total;
    private MetodoPago metoPago;
    
    public Pedido(LocalDateTime fecha, String codigo, Comprador cliente, ArrayList<Product> productos, String idProveedor) {
        this.fecha = fecha;
        this.codigo = codigo;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        estado= EstadoPedido.Solicitado;
        this.idProveedor= idProveedor;
    }

    public void ConsultarInfoPedido(){
        Set<Product> unicos= new HashSet<Product>(productos);
        for(Product key: unicos){
            int veces= Collections.frequency(productos, key);
            double total = key.getPrecio() * veces;
            System.out.print(codigo+" "+fecha+" ["+key.getIdProducto()+" "+key.getNombreProd()+" "+veces+"] ");
            System.out.print("["+cliente.getNom()+" "+cliente.getUbicacion()+" "+cliente.getCorreo()+" ]"+cliente.getFormaPago()+" "+total);
        }
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Comprador getCliente() {
        return cliente;
    }

    public void setCliente(Comprador cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Product> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Product> productos) {
        this.productos = productos;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setMetoPago(MetodoPago metoPago) {
        this.metoPago = metoPago;
    }
    
    public void agreagarMetodo(){
        if(metoPago instanceof PagoPaypal){
            PagoPaypal pp= (PagoPaypal) metoPago;
        }else if(metoPago instanceof PagoTarjeta){
            PagoTarjeta pt= (PagoTarjeta) metoPago;
        }
    }
    
    
 @Override
    public String toString() {
        String dateFormatter= "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern(dateFormatter);
        return "Codigo: "+codigo+"\n "+"fecha y hora: " + fecha +"\n"+cliente.toString() +"Estado: " + estado+"\n"+"Datos de metodo de pago: "+metoPago.toString();
    }


}
