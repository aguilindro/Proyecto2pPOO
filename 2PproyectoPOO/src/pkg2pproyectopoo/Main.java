/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2pproyectopoo;

import application.Aplicacion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Monica Garces
 */
public class Main extends Application {
    public static Scene scene;
    
    /**
     * Metodo que antes de iniciar la application lee y carga todos los datos
     * del restaurante. Si dentro de la carpeta recursos.archivos no hay nada
     * entonces dentro del constructor luego de haber inicializado la app
     * poner app.guardarDatos(); generar el archivo .dat, luego eliminarlo.
     */
    @Override
    public void init(){
        Aplicacion app = new Aplicacion();
        app.guardarDatos();
        Aplicacion.conjuntoUsers = app.cargarUsuarios();
        Aplicacion.listaMesas = app.cargarMesas();
        Aplicacion.listaProductos = app.cargarProductos();
        System.out.println("Listado de mesas!!"+Aplicacion.listaMesas);
    }
    
    @Override
    public  void start(Stage primaryStage) {
        Inicio i = new Inicio();
        scene = new Scene(i.getRoot(), 800, 500);
        primaryStage.setTitle("App Restaurante G6!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void stop(){
        System.exit(0);
    }

    /**
     * @param args the command line arguments.
     * Método que inicia la aplicación.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
