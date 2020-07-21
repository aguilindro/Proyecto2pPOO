/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2pproyectopoo;

import application.Aplicacion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Modelos.VistaAdmin;
import Modelos.VistaMesero;
import javafx.scene.control.PasswordField;
import personas.Administrador;
import personas.Mesero;
import personas.Usuario;

/**
 *
 * @author adrig
 */
public class Inicio {
    public static BorderPane root = new BorderPane();
    private static VBox paneInicio;
    
    /**
     * Constructor que inicia la pantalla "Inicio" de la aplicacion.
     */
    public Inicio(){
        root.setPadding(new Insets(10));
        generarContenido();
    }
    
    /**
     * Metodo para generar todo el contenido de la aplicacion
     * va cambiando de acorde a la accion aplicada en la app.
     */
    public void generarContenido(){
        root.getChildren().clear();
        root.setStyle("-fx-background-color: #A6A7A7;");
            
        paneInicio = new VBox();
        paneInicio.setAlignment(Pos.CENTER);
        paneInicio.setPadding(new Insets(10));
        paneInicio.setSpacing(10);
        HBox cuadro1= new HBox();
        HBox cuadro2= new HBox();
        Button btnIngresar = new Button("INGRESAR");
        TextField usu= new TextField(); 
        PasswordField contra= new PasswordField(); 
        cuadro1.getChildren().addAll(new Label("Usuario: "),usu);
        cuadro1.setAlignment(Pos.CENTER);
        cuadro1.setSpacing(10);
        cuadro2.getChildren().addAll(new Label("Contrasena: "),contra);
        cuadro2.setAlignment(Pos.CENTER);
        cuadro2.setSpacing(10);
        paneInicio.getChildren().addAll(new Label("BIENVENIDO"),cuadro1,cuadro2,btnIngresar);
        root.setCenter(paneInicio);
               
        btnIngresar.setOnMouseClicked((e)->{
            System.out.println("Boton ingresar");
            String usuario = usu.getText();
            String contrasena = contra.getText();
            if((usuario != null) && (contrasena != null)){
                Usuario user = Aplicacion.obtenerUser(usuario, contrasena);
                if(user instanceof Administrador){
                    System.out.println("AADMINNN");
                    Administrador admin = (Administrador)user;
                    VistaAdmin vA = new VistaAdmin(admin);
                    }else if(user instanceof Mesero){
                    Mesero mesero = (Mesero)user;
                    VistaMesero vM = new VistaMesero(mesero);
                    System.out.println("MESEROOO");
                }else{
                    System.out.println("Ingreso ELSEE");
                    paneInicio.getChildren().clear();
                    Button btnIntentar = new Button("Intentar");
                    paneInicio.getChildren().addAll(new Label("Usuario no encontrado, vuelva a intentarlo"),btnIntentar);
                    btnIntentar.setOnMouseClicked((event)->{
                        paneInicio.getChildren().clear();
                        generarContenido();
                    });
                }          
                
            }
        });
    }
    
    /**
     * Metodo que devuelve el root de la vista inicio
     * @return root BorderPane
     */
    public BorderPane getRoot(){
        return root;
    }
}
