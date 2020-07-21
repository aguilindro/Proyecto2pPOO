/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import personas.Cliente;
import personas.Mesero;
import Constantes.DisponibilidadMesa;
import java.io.Serializable;
import javafx.scene.shape.Circle;
import ventas.Pedido;

/**
 *
 * @author Tyrone
 */
public class Mesa extends Circle implements Serializable{

    private double ventaTotalDiario;
    private Cliente clienteAsociado;
    private Mesero meseroAsociado;
    private DisponibilidadMesa estado;
    private Pedido pedido;
    private int numMesa;
    private double posX;
    private double posY;
    private double radio;

    public Mesa(double ventaTotalDiario, Cliente clienteAsociado, Pedido pedido,Mesero meseroAsociado, DisponibilidadMesa estado, double postX, double postY, double radio,int numMesa) {
        this.ventaTotalDiario = ventaTotalDiario;
        this.clienteAsociado = clienteAsociado;
        this.meseroAsociado = meseroAsociado;
        this.pedido = pedido;
        this.estado = estado;
        this.numMesa = numMesa;
        this.posX = postX;
        this.posY = postY;
        this.radio = radio;
    }
    
    /**
     * Metodo que aumenta el dinero de la venta hecha por la mesa en el día
     * @param dineroRecaudado Recibe un parámeto double con la cantidad de 
     * dinero a sumarle a la mesa.
     */
    public void sumarVentaDiaria(double dineroRecaudado){
        this.ventaTotalDiario+=dineroRecaudado;
    }

    public DisponibilidadMesa getEstado() {
        return estado;
    }

    public void setMeseroAsociado(Mesero meseroAsociado) {
        this.meseroAsociado = meseroAsociado;
    }

    public Mesero getMeseroAsociado() {
        return meseroAsociado;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setEstado(DisponibilidadMesa estado) {
        this.estado = estado;
    }

    public double getVentaTotalDiario() {
        return ventaTotalDiario;
    }

    public void setVentaTotalDiario(double ventaTotalDiario) {
        this.ventaTotalDiario = ventaTotalDiario;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
    @Override
    public String toString() {
        return "Mesa{" + "ventaTotalDiario=" + ventaTotalDiario + ", clienteAsociado=" + clienteAsociado + ", meseroAsociado=" + meseroAsociado + ", estado=" + estado + ", numMesa=" + numMesa + '}';
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
        final Mesa other = (Mesa) obj;
        if (this.numMesa != other.numMesa) {
            return false;
        }
        return true;
    }
    
    
}
