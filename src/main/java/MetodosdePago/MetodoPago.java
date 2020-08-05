package MetodosdePago;

//import Tiendapp.Cliente;

import AppTiendaComp.Comprador;

//import Tiendapp.Proveedor;

public abstract class  MetodoPago {
    //private Credito TarjetaCredito;
    private Comprador Paypal;
    
    /**
     *
     * @return
     */
    public abstract double procesarPago();
            
}
