/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas;

/**
 *
 * @author adrig
 */
public class Administrador extends Usuario  {
    
    /**
     * Metodo que construye a un objeto tipo mesero
     * @param u Recibe un parametro de usuario
     * @param c Recibe un parametro de contraseña
     * @param n Recibe un parametro de nombre
     * @param a Recibe un parametro de apellido
     */
    public Administrador(String u, String c, String n, String a) {
        super(u, c, n, a);
    }
    
}
