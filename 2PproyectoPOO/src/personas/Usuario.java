/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas;


import java.io.Serializable;


/**
 *
 * @author adrig
 */
public abstract class Usuario implements Serializable{
    private String usuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    
    /**
     * Contructor de un objeto Usuario que es es usado por sus hijos
     * @param usuario recibe un String usuario
     * @param contrasena recibe un String contrasena
     * @param nombre recibe un String nombre
     * @param apellido recibe un String apellido
     */
    public Usuario(String usuario,String contrasena, String nombre, String apellido){
        this.usuario= usuario;
        this.contrasena=contrasena;
        this.nombre= nombre;
        this.apellido = apellido;
    }
    
    /**
     * Metodo que devuelve el String de usuario
     * @return String usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Metodo que devuelve la contraseña del ususario
     * @return String contraseña
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Metodo que devuelve un string nombre del usuario
     * @return String nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Metodo que devuelve un string apellido del user
     * @return String apellido
     */
    public String getApellido(){
        return apellido;
    }
    
    /**
     * Metodo equals() que compara si un pbjeto es igual a otro a partir del
     * usuario y la contraseña.
     * @param obj Recibe un objeto
     * @return devuelve un boolean indicando si el objeto es igual o no
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && (obj instanceof Usuario)){
            Usuario user = (Usuario)obj;
            return ((user.getUsuario().equals(this.usuario)) && (user.getContrasena().equals(this.contrasena)));
        }else{
            return false;
        }
    }
    
    /**
     * @return hash Devuelve un valor entero con el Código Hash de usuario
     * y contraseña del objeto
     */
    @Override
    public int hashCode() {
        int hash = this.usuario.hashCode() + this.contrasena.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido;
    }
    
    
     
    
}
