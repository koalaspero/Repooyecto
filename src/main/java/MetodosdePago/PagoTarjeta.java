package MetodosdePago;

public class PagoTarjeta extends MetodoPago{
    private String numTarjeta;
    private String nombTitular;
    private String tipo; 
    
    
    public PagoTarjeta(){
        procesarPago();
    }
    
    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getNombTitular() {
        return nombTitular;
    }

    public void setNombTitular(String nombTitular) {
        this.nombTitular = nombTitular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PagoTarjeta: " + "numTarjeta=" + numTarjeta + ", nombTitular=" + nombTitular + ", tipo=" + tipo;
    }

    @Override
    public double procesarPago() {
        double aleatorio= (Math.random()*(99999-10000)+10000);
        return aleatorio;
    }
    
}
