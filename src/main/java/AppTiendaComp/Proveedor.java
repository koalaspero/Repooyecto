/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTiendaComp;

import AppTienda.Coordenada;
import AppTienda.Pedido;
import java.util.ArrayList;

/**
 *
 * @author HP PAVILION
 */
public class Proveedor extends Usuario{
    private final String idProveedor;
    private final String nombreProv;
    private final Coordenada ubicacionProv;
    private final String telefono;
    private final String correoProv;

    
    public Proveedor(String n,String p,String idProveedor, String nombreProv, Coordenada ubicacionProv, String telefono, String correoProv){
        super(n,p);
        this.idProveedor = idProveedor;
        this.nombreProv = nombreProv;
        this.ubicacionProv = ubicacionProv;
        this.telefono = telefono;
        this.correoProv = correoProv;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public Coordenada getUbicacionProv() {
        return ubicacionProv;
    }

    public String getTelefono() {
        return telefono;
    }


    public String getCorreoProv() {
        return correoProv;
    }



    @Override
    public String toString() {
        return "{"+"idProveedor=" + idProveedor + ", nombreProv=" + nombreProv + ", ubicacionProv=" + ubicacionProv + ", telefono=" + telefono + ", correoProv=" + correoProv + '}'+"\n";
    }

    
}
