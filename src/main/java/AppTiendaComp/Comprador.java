/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTiendaComp;

import AppTienda.Coordenada;
import java.util.ArrayList;

/**
 *
 * @author HP PAVILION
 */
public class Comprador extends Usuario{
    private String idComprador;
    private String nombComprador;
    private Coordenada ubicacion;
    private String direccion;
    private String correo;
    private String formaPago;

    public Comprador(String idComprador, String nombComprador, Coordenada ubicacion, String correo, String formaPago, String nomUsuario, String password) {
        super(nomUsuario, password);
        this.idComprador = idComprador;
        this.nombComprador = nombComprador;
        this.ubicacion = ubicacion;
        this.correo = correo;
        this.formaPago = formaPago;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public String getNombComprador() {
        return nombComprador;
    }
    
    public Coordenada getUbicacion() {
        return ubicacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUbicacion(Coordenada ubicacion) {
        this.ubicacion = ubicacion;
    }


    @Override
    public String toString() {
        return "{" + " idComprador:" + idComprador + ", nombComprador:" + nombComprador + ", ubicacion:" + ubicacion + ", correo:" + correo + ", formaPago:" + formaPago + '}'+"\n";
    }


    
}
