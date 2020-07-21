/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Constantes.DisponibilidadMesa;
import application.Aplicacion;
import application.Mesa;
import java.util.ArrayList;

import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import personas.Administrador;

import personas.Mesero;
import pkg2pproyectopoo.Inicio;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author adrig
 */
public class Restaurante {

    private Mesero meseroActual;
    private Administrador adminActual;
    private Pane paneMesas;
    private VBox paneRight;
    static ArrayList<Label> labels; 

    /**
    Constructor que recibe el parametro Mesero.
     * @param mesero precibe como parametro
    */
    public Restaurante(Mesero mesero) {
        this.meseroActual = mesero;
        crearPanelCentro();
        crearPanelRight();
    }
  
    
    /**
     * Constructor que recibe el parametro Administrador.
     * @param admin para pode identificar quien es-
    */
    public Restaurante(Administrador admin){
        this.adminActual = admin;
        crearPanelCentro();
        crearPanelRight();
    }


    /**
     * Crea el panel Central dentro del BorderPane.
     */
    public void crearPanelCentro() { 
        paneMesas = new Pane();
        labels = new ArrayList<>();
        for (Mesa m : Aplicacion.listaMesas) {
            if (m.getMeseroAsociado() == null && m.getEstado() == DisponibilidadMesa.DISPONIBLE) {
                m.setFill(Color.YELLOW);
                Label lb = new Label(String.valueOf(m.getNumMesa()));
                lb.setLayoutX(m.getPosX()-8);
                lb.setLayoutY(m.getPosY()-15);
                lb.setFont(new Font(25));
                lb.setTextFill(Color.WHITE);
                m.setLayoutX(m.getPosX());
                m.setLayoutY(m.getPosY());
                m.setRadius(m.getRadio());
                m.setStyle(String.valueOf(m.getNumMesa()));
                paneMesas.getChildren().addAll(m,lb);
                labels.add(lb);

            } else if ((m.getEstado() == DisponibilidadMesa.NO_DISPONIBLE) && (m.getMeseroAsociado().equals(meseroActual))) {
                m.setFill(Color.GREEN);
                Label lb = new Label(String.valueOf(m.getNumMesa()));
                lb.setLayoutX(m.getPosX()-7);
                lb.setLayoutY(m.getPosY()-12);
                lb.setFont(new Font(25));
                lb.setTextFill(Color.WHITE);
                m.setLayoutX(m.getPosX());
                m.setLayoutY(m.getPosY());
                m.setRadius(m.getRadio());
                paneMesas.getChildren().addAll(m,lb);
                labels.add(lb);

            }else if((m.getEstado() == DisponibilidadMesa.NO_DISPONIBLE) && !((m.getMeseroAsociado().equals(meseroActual)))){
                m.setFill(Color.RED);
                Label lb = new Label(String.valueOf(m.getNumMesa()));
                lb.setLayoutX(m.getPosX()-5);
                lb.setLayoutY(m.getPosY()-12);
                lb.setFont(new Font(25));
                lb.setTextFill(Color.WHITE);
                m.setLayoutX(m.getPosX());
                m.setLayoutY(m.getPosY());
                m.setRadius(m.getRadio());
                paneMesas.getChildren().addAll(m,lb);
                labels.add(lb);
             //SUSTENTACION   
            }else if(m.getEstado() == DisponibilidadMesa.RESERVADA && m.getMeseroAsociado()==null){
                m.setFill(Color.AQUA);
                Label lb = new Label(String.valueOf(m.getNumMesa()));
                lb.setLayoutX(m.getPosX()-5);
                lb.setLayoutY(m.getPosY()-12);
                lb.setFont(new Font(25));
                lb.setTextFill(Color.WHITE);
                m.setLayoutX(m.getPosX());
                m.setLayoutY(m.getPosY());
                m.setRadius(m.getRadio());
                paneMesas.getChildren().addAll(m,lb);
                labels.add(lb);
            }else{}
            
        } 
  
    Inicio.root.setCenter(paneMesas);
    }

    /**
    Crea un Vbox para almacenar los elementos en la parte derecha del root.
    */
    
    public void crearPanelRight() {
        paneRight = new VBox();
        HBox union = new HBox();
        Label cocina = new Label("Cocina");
        cocina.setFont(new Font(23));
        cocina.setRotate(270);
        Rectangle rct = new Rectangle(30, 400, Color.WHITE);
        union.getChildren().addAll(rct, cocina);
        union.setAlignment(Pos.CENTER);
        paneRight.getChildren().add(union);
        paneRight.setAlignment(Pos.CENTER);
        Inicio.root.setRight(paneRight);

    }

    public Pane getPaneMesas() {
        return paneMesas;
    }

    public void setPaneMesas(Pane paneMesas) {
        this.paneMesas = paneMesas;
    }
    
    
    }
