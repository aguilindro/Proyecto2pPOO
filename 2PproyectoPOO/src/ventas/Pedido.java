/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import application.Mesa;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import personas.Mesero;
import ventas.Producto;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class Pedido implements Serializable{
    private Mesa mesa;
    private List<Producto> productos;
    private int numCuenta;
    private LocalDate fecha;
    private Mesero mesero;
    private double totalCuenta;
    
    /**Variables especiales para el tableview.
     */
    private SimpleStringProperty pfecha;
    private SimpleIntegerProperty numMesa;
    private SimpleStringProperty nameMesero;
    private SimpleStringProperty numeroCuenta;
    private SimpleStringProperty nameCliente;
    private SimpleDoubleProperty total;
    
    public Pedido(Mesa m, List<Producto> p, int n, LocalDate f, Mesero me){
    this.mesa=m;
    this.productos=p;
    this.numCuenta=n;
    this.fecha=f;
    this.mesero=me;
    this.totalCuenta=0;
    }
    /**
     * Constructo de pedido
     * @param f String 
     * @param n String
     * @param nameM Mesa
     * @param numC no te olvides de sumarli
     * @param nameCl nombre de la clase
     * @param t otro coment
     */
    public Pedido(String f,int n,String nameM,String numC,String nameCl,double t){
        this.pfecha= new SimpleStringProperty(f);
        this.fecha= LocalDate.parse(f);
        this.numMesa= new SimpleIntegerProperty(n);
        this.nameMesero= new SimpleStringProperty(nameM);
        this.numeroCuenta= new SimpleStringProperty(numC);
        this.nameCliente= new SimpleStringProperty(nameCl);
        this.total= new SimpleDoubleProperty(t);
    }
   
    /**
     * Metodo que calcula el total de la cuenta y lo setea para que se modifique
     * en tocas las manerias
     * @return double retorna un double con el nuevo promedio de la mesa
     */
    public double calcularTotalCuenta(){
        double respuesta = 0;
        for(Producto p: productos){
            double precio= p.getPrecio();
            respuesta+= precio;
        }
        return respuesta;
    }

    public double getTotalCuenta() {
        return totalCuenta;
    }
    

    public Mesa getMesa() {
        return mesa;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public void setTotalCuenta(double totalCuenta) {
        this.totalCuenta = totalCuenta;
    }

    public String getPfecha() {
        return pfecha.get();
    }

    public int getNumMesa() {
        return numMesa.get();
    }

    public String getNameMesero() {
        return nameMesero.get();
    }

    public String getNumeroCuenta() {
        return numeroCuenta.get();
    }

    public String getNameCliente() {
        return nameCliente.get();
    }

    public double getTotal() {
        return total.get();
    }

    /**
     * Metodo void que genera el reporte de ventas.
     */
    public void generarReporteVenta(){
        System.out.println("Entrando al generador de archivo");
        try(BufferedWriter bf = new BufferedWriter(new FileWriter("src/recursos/archivos/ventas.txt", true))){
            bf.write(fecha.toString()+","+String.valueOf(mesa.getNumMesa())
                    +","+mesero.getNombre()+" "+mesero.getApellido()
                    +","+"00"+String.valueOf(numCuenta)
                    +","+mesa.getClienteAsociado().getNombre()+" "+mesa.getClienteAsociado().getApellido()
                    +","+String.valueOf(new BigDecimal((totalCuenta)).setScale(2,RoundingMode.HALF_EVEN).doubleValue())+"\n");
        }catch(IOException e){
            System.out.println("Error al escribir el archivo: " +e);
        }                    
    }
    
    
}
