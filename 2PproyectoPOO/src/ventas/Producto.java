/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class Producto implements Serializable, Comparable<Producto>{
    private String nombre;
    private double precio;
    private String categoria;
    private String path;
    
    public Producto(String nombre,String categoria,double precio, String path){
    this.nombre=nombre;
    this.precio=precio;
    this.path = path;
    this.categoria=categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        return true;
    }

    

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public int compareTo(Producto p){
        return this.nombre.compareTo(p.getNombre());
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria;
    }

}
