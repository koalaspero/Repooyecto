/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTienda;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Just25fm
 */
public class MailEstructura {
    private String correoRemitente;
    private String contrasenia;
    private String correoReceptor;
    private String asunto;
    private String mensaje;
    
    public MailEstructura(String remitente, String contra, String receptor, String asunto, String mensaje){
        this.correoRemitente = remitente;
        this.contrasenia = contra;
        this.correoReceptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getCorreoRemitente() {
        return correoRemitente;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getCorreoReceptor() {
        return correoReceptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    public void enviarCorreo(){
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(props);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getCorreoRemitente()));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getCorreoReceptor()));
            message.setSubject(getAsunto());
            message.setText(getMensaje());
            
            Transport t = session.getTransport("smtp");
            t.connect(getCorreoRemitente(),getContrasenia());
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            
            System.out.println("El mensaje con destino al correo " + getCorreoReceptor() + 
                    " se ha enviado con exito.");
            
            
        } catch (AddressException ex) {
            Logger.getLogger(MailEstructura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailEstructura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
