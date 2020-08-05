/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testeos;

import java.util.Scanner;

/**
 *
 * @author Just25fm
 */
import AppTienda.MailEstructura;
public class TestCorreo {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        TestCorreo correo = new TestCorreo();
        MailEstructura mail;
        
        System.out.println("----------BIENVENIDO AL SISTEMA DE ENVIO DE CORREO ELECTRONICO----------");
        System.out.println("");
        System.out.println("¿Quien envía el correo?");
        System.out.print("Ingrese su correo: ");
        String remitente = sc.nextLine();
        System.out.print("Ingrese su contrsenia: ");
        String contrasenia = sc.nextLine();
        System.out.println("¿Para quien desea enviar el correo?");
        System.out.print("Ingrese correo del receptor: ");
        String receptor = sc.nextLine();
        System.out.println("¿Cual es el asunto del mensaje?");
        System.out.print("Ingrese asunto: ");
        String asunto = sc.nextLine();
        System.out.println("¿Que mensaje desea enviar?");
        System.out.print("Redacte el mensaje: ");
        String mensaje = sc.nextLine();
        
        mail = new MailEstructura(remitente, contrasenia, receptor, asunto, mensaje);
        
        mail.enviarCorreo();
        
    }
    
}
