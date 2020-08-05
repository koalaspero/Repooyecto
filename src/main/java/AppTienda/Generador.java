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
public class Generador {
    public static String RequiredString(int n){
    String codigoAlfanumerico = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"+ "abcdefghijklmnopqrstuvxyz";
    StringBuilder s = new StringBuilder(n);
    int y;
    for ( y = 0; y < n; y++) {
        int index= (int)(codigoAlfanumerico.length()* Math.random());
        s.append(codigoAlfanumerico .charAt(index));
    }
return s.toString();
}
}
