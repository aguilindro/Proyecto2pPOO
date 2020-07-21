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
public class Mesero extends Usuario {
    
    /**
     * Metodo que construye a un objeto tipo mesero
     * @param u Recibe un parametro de usuario
     * @param c Recibe un parametro de contraseña
     * @param n Recibe un parametro de nombre
     * @param a Recibe un parametro de apellido
     */
    public Mesero(String u, String c, String n, String a) {
        super(u, c, n , a);
    }
    /**
     * Metodo equals() que compara si un pbjeto es igual a otro a partir del
     * usuario y la contraseña.
     * @param obj Recibe un objeto
     * @return devuelve un boolean indicando si el objeto es igual o no
     */
    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            Usuario user = (Usuario)obj;
            return (user.getNombre().equals(super.getNombre())) && (user.getApellido().equals(super.getApellido()));
        }
        return false;
    }

    /**
     * @return hash Devuelve un valor entero con el Código Hash de usuario
     * y contraseña del objeto
     */
    @Override
    public int hashCode() {
        return super.hashCode()+super.getNombre().hashCode() + super.getApellido().hashCode(); 
    }

    
}
