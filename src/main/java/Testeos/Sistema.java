package Testeos;

import AppTienda.Coordenada;
import AppTienda.SistemaGPS;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;

/**
 *
 * @author HP PAVILION
 */
public class Sistema {
    public static void main(String[] args) {

        String direccion = "Lago ESPOL, Guayaquil";

        
        Coordenada ubicacion = SistemaGPS.consultarUbicacion(direccion);
        
        //System.out.println("Las cordenadas son : ("+ubicacion+")");
        System.out.println(ubicacion);

        System.out.println("\nReverse direccion a partir de coordenadas");
        
        String direccion2 = SistemaGPS.consultarUbicacion(ubicacion);
        System.out.println(direccion2);
        
    }

    


}