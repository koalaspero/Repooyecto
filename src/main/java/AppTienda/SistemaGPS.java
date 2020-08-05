/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;

public class SistemaGPS {
      /**
     * Método que recibe un String direccion y retorna coordenadas.
     *
     * @param direccion String
     * @return Ubicacion ( lat, lon )
     */
    public static Coordenada consultarUbicacion(String direccion){
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("a1b1f4d1cfb64779bee3a623e502348c");

        JOpenCageForwardRequest request;
        Coordenada ubicacion;

        request = new JOpenCageForwardRequest(direccion);

        // Prioriza los resultados en un lenguaje específico
        request.setLanguage("es");
        // Restringe los resultados a un país específico.
        request.setRestrictToCountryCode("ec");
        // Restringe los resultados con una calificación de confianza de n/10.
        request.setMinConfidence(3);

        JOpenCageResponse respuesta = jOpenCageGeocoder.forward(request);
        // get del par de coordenadas del primer resultado
        JOpenCageLatLng coordenadas = respuesta.getFirstPosition();

        // Transformación de tipo JOpenCageLatLng a Ubicacion.
        ubicacion = new Coordenada(coordenadas.getLat(), coordenadas.getLng());

        return ubicacion;
    }

    /**
     * Método que recibe coordenadas y devuelve un String direccion.
     *
     * @param u Coordenada
     * @return La dirección como String
     */
    public static String consultarUbicacion(Coordenada u) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("a1b1f4d1cfb64779bee3a623e502348c"); //claves
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(u.getLat(), u.getLon());

        request.setLanguage("es");
        // Sólo retornará el primer resultado(10 por default)
        request.setLimit(1);
        // Excluye información extra como el timezone, etc.
        request.setNoAnnotations(true);
        request.setMinConfidence(3);

        JOpenCageResponse respuesta = jOpenCageGeocoder.reverse(request);
        // get de la primera dirección resultante formateada.
        String direccion = respuesta.getResults().get(0).getFormatted();

        return direccion;
      
    }
}
