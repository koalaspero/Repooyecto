/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppTiendaComp;

/**
 *
 * @author HP PAVILION
 */
public class Usuario {
    String nomUsuario;
    String password;
    public Usuario(){
    }
    
    public Usuario(String nomUsuario, String password){
        this.nomUsuario= nomUsuario;
        this.password= password;
    }
    public String getNom(){
        return nomUsuario;
    }
    public String getPass(){
        return password;
    }
    public void setNom(String nomUsuario){
        this.nomUsuario= nomUsuario;
    }
    public void setPass(String password){
        this.password = password;
    }
    public boolean checkLogin(String n, String p){
        if (nomUsuario.equals(n) && password.equals(p)){
            return true;
        }else{
            return false;
        }
    }
}
