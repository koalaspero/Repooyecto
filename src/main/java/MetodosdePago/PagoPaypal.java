
package MetodosdePago;

public class PagoPaypal extends MetodoPago {
    private String usuario;
    private String  contraseña;
    private double cantidad;
    
    public PagoPaypal(String usuario,String contraseña){
        this.usuario = usuario;
        this.contraseña= contraseña;
    }
            
    public double  generarDinero(){
        return 12;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PagoPaypal:" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", cantidad=" + cantidad ;
    }

    @Override
    public double procesarPago() {
        //Genera un valor al azar entre 100 y 1000 que representara la cantidad
        // de dinero que el cliente tiene en su cuenta y procesara el pago si los fondos 
        //del cliente son suficientes
        double cantidad=  Math.floor(Math.random()*(1000-100+1)+100);
        return cantidad;
    }
    
    
}
