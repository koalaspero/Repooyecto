/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;

/**
 *
 * @author HP PAVILION
 */
public class Coordenada {
    private double latitud;
    private double longitud;
    private static final double radTierra= 6378;
    
    public Coordenada(double latitud, double longitud){
        this.latitud= latitud;
        this.longitud= longitud;
    }
    public double getLat(){
        return latitud;
    }
    public double getLon(){
        return longitud;
    }
    public void setLat(double latitud){
        this.latitud= latitud;
    }
    public void setLongitud(double longitud){
        this.longitud = longitud;
    }
    public static double calcularDistanciaDospuntos(Coordenada c1, Coordenada c2){
        double difLat; double difLon;
        double lat1= c1.getLat(); double lat2= c2.getLat();
        double lon1= c1.getLon(); double lon2= c2.getLon();
        double a; double c;
        difLat= lat2 - lat1;
        difLon= lon2 - lon1;
        a = Math.pow(Math.sin(Math.toRadians(difLat/2)),2)+Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.pow(Math.sin(Math.toRadians(difLon/2)),2);
        c= 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return radTierra * c;
        
    }

    @Override
    public String toString() {
        return "( "+latitud +" , " +longitud + " )";
    }
}
