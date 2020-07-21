/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Constantes.DisponibilidadMesa;
import application.Aplicacion;
import static application.Aplicacion.crearMapaCategoriaProduct;
import application.Mesa;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;


import java.util.TreeSet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import personas.Cliente;
import pkg2pproyectopoo.Inicio;
import personas.Mesero;
import ventas.Pedido;
import ventas.Producto;

/**
 * Clase Mesero donde se crea la interfaz mesero.
 */
public class VistaMesero{

    private Pane panelTop;
    private Mesero meseroActual;
    private Mesa mesaActual;
    private Button sodas;
    private Button postres;
    private Button platos;
    private Button jugos;
    private Button combos;
    private Button comidaRapida;
    private Button beers;
    private Pedido pedido;

    /**
     *
     * Constructor VistaMesero que inica la interfaz mesero.
     * @param mesero Contructor de VistaMesero que recibe al mesero
     */
    public VistaMesero(Mesero mesero) {
        Inicio.root.getChildren().clear();
        this.meseroActual = mesero;
        Restaurante rtMesero = new Restaurante(mesero);
        crearPanelTop();
        iterarCirculos();
        }
    
    /**
     * Constructor vacio.
     */
    public VistaMesero(){
        
    }

    /**
     *
     * Crea el panel Top dentro de BorderPane.
     */
    public void crearPanelTop() {
        panelTop = new Pane();

        Label txtNombreMesero = new Label("Mesero : "+meseroActual.getNombre() + " " + meseroActual.getApellido());
        txtNombreMesero.setLayoutX(300);
        txtNombreMesero.setLayoutY(0);
        txtNombreMesero.setFont(new Font(20));
        txtNombreMesero.setTextFill(Color.WHITE);
        Button btSalir = new Button("SALIR");
        btSalir.setLayoutX(725);
        btSalir.setLayoutY(3);

        panelTop.getChildren().addAll(txtNombreMesero,btSalir);
        btSalir.setOnMouseClicked((e) -> {
            Inicio.root.getChildren().clear();
            VistaAdmin.resetearEvent();
            Inicio i = new Inicio();
            
        });
        
        
        Inicio.root.setTop(panelTop);
        Inicio.root.getTop().setStyle("-fx-background-color: #000000;");
    }
/**
 * Crea el panel Top Menu en el root.
 */
    public void crearPanelTopMenu() {
        panelTop = new Pane();
        Label txtNombreCliente = new Label("Mesa " + mesaActual.getNumMesa() + ",Cliente " + mesaActual.getClienteAsociado().getNombre() + " " + mesaActual.getClienteAsociado().getApellido());
        txtNombreCliente.setLayoutX(315);
        txtNombreCliente.setLayoutY(0);
        txtNombreCliente.setFont(new Font(20));
        txtNombreCliente.setTextFill(Color.WHITE);
        panelTop.getChildren().add(txtNombreCliente);
        
        Inicio.root.setTop(panelTop);
        Inicio.root.getTop().setStyle("-fx-background-color: #000000;");
    }
/**
 * Crea el panel Ledft Menu en el root.
 */
    public void crearPanelLeftMenu() {
        Pane pane = new Pane();
        
        VBox vBoxGen = new VBox();
        VBox vb1 = new VBox();
        vb1.setStyle("-fx-background-color: #FFFFFF");
        if(mesaActual.getPedido() != null){
            pedido = mesaActual.getPedido();
            if(mesaActual.getPedido().getProductos() != null && !(mesaActual.getPedido().getProductos().isEmpty())){
                System.out.println("La mesa actual tiene productos");
                System.out.println(mesaActual.getPedido().getProductos());
                ArrayList<Producto> list = (ArrayList<Producto>)mesaActual.getPedido().getProductos();
                TreeSet<Producto> set = new TreeSet<>(list);
                
        
                for(Producto p : set){
                    
                    int cont = 0;
                    for(Producto prod :list){
                        if(prod.equals(p)){
                            cont+=1;
                        }
                    }

                        System.out.println("Lista contiene :"+p.getNombre());
                        VBox prodSelecVstPrevia = new VBox();


                        Pane encabezado = new Pane();
                        HBox parteNombre = new HBox();
                        parteNombre.setAlignment(Pos.TOP_LEFT);
                        Label lblNombre = new Label(p.getNombre());                       
                        lblNombre.setFont(new Font(13));
                        parteNombre.getChildren().add(lblNombre);

                        HBox parteMoney = new HBox();
                        parteMoney.setAlignment(Pos.TOP_RIGHT);
                        Label lblMoney = new Label(String.valueOf(new BigDecimal((p.getPrecio()*cont)).setScale(2,RoundingMode.HALF_EVEN).doubleValue())+" $");
                        lblMoney.setFont(new Font(13));
                        parteMoney.getChildren().add(lblMoney);
                        parteMoney.setLayoutX(175);
                        encabezado.getChildren().addAll(parteNombre,parteMoney);


                        HBox pie = new HBox();
                        Label pieProd = new Label(String.valueOf(cont)+" Unidad(es) de "+String.valueOf(new BigDecimal((p.getPrecio())).setScale(2,RoundingMode.HALF_EVEN).doubleValue())+" $/Unidad(s)");
                        pieProd.setFont(new Font(10));
                        pieProd.setTextFill(Color.BLACK);
                        pie.setPadding(new Insets(0, 4, 0, 4));
                        pie.getChildren().add(pieProd);

                        prodSelecVstPrevia.getChildren().addAll(encabezado,pie);
        

                        vb1.getChildren().add(prodSelecVstPrevia);
                        vb1.setSpacing(7);
         
                    
                }
                HBox partTotal = new HBox();
                partTotal.setAlignment(Pos.TOP_RIGHT);
                
                VBox partInternaTotal = new VBox();
                partInternaTotal.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 0, 0, 0))));
                
                mesaActual.getPedido().setTotalCuenta(pedido.calcularTotalCuenta());
                Label lbl = new Label("Total: "+String.valueOf(new BigDecimal((pedido.calcularTotalCuenta())).setScale(2,RoundingMode.HALF_EVEN).doubleValue())+" $");
                lbl.setFont(new Font(17));
                
                HBox partPropina = new HBox();
                partPropina.setAlignment(Pos.CENTER);
                partPropina.setSpacing(3);
                Label lblPropina = new Label("Taxes: 3.43 $");
                lblPropina.setFont(new Font(10));
                partPropina.getChildren().add(lblPropina);
                
                partInternaTotal.getChildren().addAll(lbl,partPropina);
                partTotal.getChildren().add(partInternaTotal);
                
                vb1.getChildren().add(partTotal);
                
            }else{
                Label txt = new Label("Usted aún no tiene\nproductos seleccionados");
                vb1.getChildren().addAll(txt);
                vb1.setAlignment(Pos.CENTER);
            }
        }else{
            System.out.println("Mesa no tiene pedido");
            Label txt = new Label("Usted no tiene pedido, ERROR");
            vb1.getChildren().addAll(txt);
            vb1.setAlignment(Pos.CENTER);
        }
        
        VBox vb2 = new VBox();
        
        Button finalizar = new Button("Finalizar Orden");
        finalizar.setMaxSize(100,0);        
        Button regresar = new Button("Regresar");
        regresar.setMaxSize(100,0);
        vb2.getChildren().addAll(finalizar, regresar);
        vb2.setSpacing(10);
        vb1.setAlignment(Pos.CENTER_LEFT);
        vb2.setAlignment(Pos.CENTER);
        
        vBoxGen.setMinSize(200, 500);
        
        vBoxGen.getChildren().addAll(vb1,vb2);
        vBoxGen.setSpacing(20);
        pane.getChildren().addAll(vBoxGen);
        Inicio.root.setLeft(pane);
        
        regresar.setOnMouseClicked((e) -> {
            Inicio.root.getChildren().clear();

            VistaAdmin.resetearEvent();     
            VistaMesero vm = new VistaMesero(meseroActual);
        });
        finalizar.setOnMouseClicked((e) -> {
            Inicio.root.getChildren().clear();
            VBox parteExterna = new VBox();
            HBox Anuncio = new HBox(); 
            Label lb = new Label("Desea finalizar Orden");
            lb.setFont(new Font(25));
            lb.setTextFill(Color.WHITE);
            Button btnSi = new Button("Si"); 
            btnSi.setMaxSize(150, 0);
            btnSi.setFont(new Font(15));
            Button btnNo = new Button("No");
            btnNo.setFont(new Font(15));
            btnNo.setMaxSize(150, 0);  
            Anuncio.getChildren().addAll(lb);
            Anuncio.setAlignment(Pos.CENTER);
            HBox parteBoton = new HBox(btnSi,btnNo);
            parteBoton.setSpacing(30);
            parteBoton.setAlignment(Pos.CENTER);
            parteExterna.getChildren().addAll(Anuncio,parteBoton);
            parteExterna.setAlignment(Pos.CENTER);
            parteExterna.setSpacing(10);
                        
            btnSi.setOnMouseClicked((event) -> {
                System.out.println("Creando archivo");
                mesaActual.getPedido().generarReporteVenta();
                mesaActual.setEstado(DisponibilidadMesa.DISPONIBLE);
                mesaActual.setClienteAsociado(null);
                mesaActual.setMeseroAsociado(null);
                mesaActual.setPedido(null);
                
                mesaActual.sumarVentaDiaria(pedido.calcularTotalCuenta());
                VistaAdmin.resetearEvent();
                System.out.println(pedido.calcularTotalCuenta());
                Inicio i = new Inicio();
                
                
            });
            btnNo.setOnMouseClicked((event) -> {
                Inicio.root.getChildren().clear();
                VistaAdmin.resetearEvent();
                crearPanelMenu();
                
            });
            Inicio.root.setCenter(parteExterna);
            
            
        });
    }

    
    /**
     * Metodo para iterar y validar las mesas. 
     */
    public void iterarCirculos() {
        System.out.println(meseroActual.getNombre());
        Iterator<Mesa> it = (Aplicacion.listaMesas).iterator();
        while (it.hasNext()) {
            Mesa m = it.next();
            if (m.getMeseroAsociado() == null && m.getEstado() == DisponibilidadMesa.DISPONIBLE) {
                m.setOnMouseClicked((e) -> {
                    System.out.println("Mesa vacia");
                    m.setEstado(DisponibilidadMesa.NO_DISPONIBLE);
                    m.setMeseroAsociado(meseroActual);                    
                    Inicio.root.getChildren().clear();
                    asignarClienteAMesa(m,false);
                });
            } else if (m.getEstado()!= DisponibilidadMesa.RESERVADA && m.getMeseroAsociado().equals(meseroActual) && m.getEstado() == DisponibilidadMesa.NO_DISPONIBLE) {
                m.setOnMouseClicked((e) -> {
                    System.out.println("Ingreso mesa verde, se debe presentar menu y los prod de la mesa");
                    Inicio.root.getChildren().clear();
                    mesaActual = m;
                    crearPanelMenu();
                    System.out.println(Aplicacion.listaMesas);
                });
                
            //sustentacion                        
            } else if(m.getMeseroAsociado()!=meseroActual && m.getEstado()== DisponibilidadMesa.RESERVADA){
                m.setOnMouseClicked((ev) -> {
                    System.out.println("Mesa RESERVADA");
                });
            }
            
            else {
                m.setOnMouseClicked((ev) -> {
                    System.out.println("Mesa Ocupada");
                });
            }
        }
    }
    
    
    /**
     * Crea el panel centro del Menu en el root. 
     * @return retorna un VBox con el panel central del menu
     */
    public VBox crearPanelCenterMenu() {
        VBox parteProductos = new VBox();
        
        HBox parteBuscador = new HBox();
        ImageView imagenBus = new ImageView(new Image("/recursos/buttonSearch.png"));
        imagenBus.setFitHeight(25);imagenBus.setFitWidth(25);
        TextField buscador = new TextField();
        buscador.setPromptText("Buscar Productos");
        buscador.setFocusTraversable(false);
        imagenBus.setOnMouseClicked((e)->{
            System.out.println("caracter: "+buscador.getText());  
            VBox parteProduct =crearPanelCenterMenu();
            parteProduct.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorLetra(buscador.getText());
            parteProduct.getChildren().add(flowpane);
            Inicio.root.setCenter(null);
            Inicio.root.setCenter(parteProduct);
        });
        parteBuscador.getChildren().addAll(imagenBus,buscador);
        parteBuscador.setAlignment(Pos.TOP_RIGHT);
        parteProductos.getChildren().add(parteBuscador);
        
        sodas = new Button("Sodas");
        postres = new Button("Postres");
        platos = new Button("Platos a la carta");
        beers = new Button("Bebidas Alcohólicas");
        jugos = new Button("Jugos");
        combos = new Button("Ofertas/Combos");
        comidaRapida = new Button("Comida Rápida");
        HBox botones = new HBox();
        botones.getChildren().addAll(sodas, postres, platos, beers, jugos, combos, comidaRapida);
        botones.setAlignment(Pos.TOP_CENTER);
        parteProductos.getChildren().add(botones);       
        Inicio.root.setCenter(parteProductos);
        optionClick();
        return parteProductos;
    }
    
    /**
     * Metodo que inicializa los eventos del mouse.
     */
    public void optionClick() {
        sodas.setOnMouseClicked((e) -> {           
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Sodas");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);
        });
        postres.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Postres");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);

        });
        platos.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Platos a la carta");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);

        });
        beers.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Bebidas Alcohólicas");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);

        });
        jugos.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Jugos");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);

        });
        combos.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Ofertas/Combos");
            parteProductos.getChildren().add(flowpane);
     
            Inicio.root.setCenter(parteProductos);

        });
        comidaRapida.setOnMouseClicked((e) -> {
            VBox parteProductos = crearPanelCenterMenu();
            parteProductos.setSpacing(20);
            FlowPane flowpane = dibujarProductosPorCate("Comida Rápida");
            parteProductos.getChildren().add(flowpane);

            Inicio.root.setCenter(parteProductos);

        });

    }
    
    /**
     * Crea el panel menu dentro del root, llamando a los otos metodos.
     */
    public void crearPanelMenu() {
        Inicio.root.getChildren().clear();

        crearPanelTopMenu();
        crearPanelCenterMenu();
        crearPanelLeftMenu();

    }
    
    /**
     * Metodo para asignar los clientes a las mesas.
     * @param mesa recibe como parametro una Mesa 
     * @param valor y un valor que luego debe modificar
     */
    
    public void asignarClienteAMesa(Mesa mesa, boolean valor){
        
        Inicio.root.getChildren().clear();
        VBox parteExterna = new VBox();
        HBox partNombre = new HBox();        
        HBox parteApellido =  new HBox();
        
        if(valor == true){
            HBox parteRepe = new HBox();
            parteRepe.setAlignment(Pos.CENTER);
            Label text = new Label("Datos incorrectos, vuelva a intentarlo.");
            text.setFont(new Font(15));
            text.setTextFill(Color.RED);
            parteExterna.getChildren().add(text);
        }
        
        Label msg1 = new Label("Ingrese el nombre del Cliente: ");
        TextField textNom = new TextField();
        
        Label msg2 = new Label("Ingrese el apellido del Cliente: ");
        TextField textApe = new TextField();
        
        partNombre.getChildren().addAll(msg1,textNom);
        parteApellido.getChildren().addAll(msg2,textApe);
        partNombre.setAlignment(Pos.CENTER);
        parteApellido.setAlignment(Pos.CENTER);
        Button btnContinuar = new Button("Continuar");       
        btnContinuar.setOnMouseClicked((e)->{
            if (!textApe.getText().trim().isEmpty() && !textNom.getText().trim().isEmpty()) {
                Cliente cliente = new Cliente(textNom.getText(), textApe.getText());
                Pedido pedi = new Pedido(mesa, null, mesa.getNumMesa(), LocalDate.now(), meseroActual);
                mesa.setPedido(pedi);
                mesa.setClienteAsociado(cliente);
                System.out.println("Cliente seteado");
                VistaMesero vm = new VistaMesero(meseroActual);
                
            } else {
                System.out.println("Vacio, vuelva a intentarlo");
                asignarClienteAMesa(mesa,true);
            }
        });
        HBox parteBoton = new HBox(btnContinuar);
        parteBoton.setAlignment(Pos.CENTER);
        parteExterna.getChildren().addAll(partNombre,parteApellido,parteBoton);
        parteExterna.setAlignment(Pos.CENTER);
        Inicio.root.setCenter(parteExterna);
    }

    
    /**
     * Metodo que con base a un Mapa dibuja los productos dentro de la lista
     * que tiene como clave el mapa.
     * @param categoria Recib un String categoria que sirve como clave del mapa
     * y presenta solo la lista de la categoria pasada como parametro
     * @return FlowPane devuelve un nodo flowPane que contiene todos los
     * productos dibujados de la lista Productos.
     */
    public FlowPane dibujarProductosPorCate(String categoria){
        
        HashMap<String, List<Producto>> mapa = crearMapaCategoriaProduct();
        ArrayList<Producto> lista = (ArrayList<Producto>)mapa.get(categoria);
        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(20);
        flowpane.setVgap(20);
        for (Producto p : lista){
            VBox principal = new VBox();
            
            HBox parteTop = new HBox();
            Label precio = new Label(String.valueOf(p.getPrecio())+" $");
            precio.setTextFill(Color.WHITE);
            precio.setFont(new Font("Berlin Sans FB", 13));

            precio.setPadding(new Insets(3, 5, 3, 5));
            precio.setStyle("-fx-background-color: #9370DB");
            parteTop.getChildren().add(precio);
            parteTop.setAlignment(Pos.TOP_RIGHT);      
            
            HBox parteCentro = new HBox();
            ImageView verImagen = new ImageView(new Image(p.getPath()));           
            verImagen.setFitHeight(60);
            verImagen.setFitWidth(60);
            parteCentro.setAlignment(Pos.CENTER);
            parteCentro.getChildren().add(verImagen);
            
            HBox parteBaja = new HBox();
            Label nombre = new Label(p.getNombre());
            nombre.setFont(new Font("Berlin Sans FB", 13));
            nombre.setPadding(new Insets(3, 5, 3, 5));
            nombre.setTextFill(Color.WHITE);

            parteBaja.getChildren().add(nombre);
            parteBaja.setAlignment(Pos.BOTTOM_LEFT);
            parteBaja.setStyle("-fx-background-color: #9370DB");
            
            principal.getChildren().addAll(parteTop,parteCentro,parteBaja);            

            principal.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.50))));
            principal.setSpacing(5);
            principal.setPadding(new Insets(5, 10, 5, 10));
            principal.setStyle("-fx-background-color: #DEB887;");
            
            principal.setOnMouseClicked((e)->{                
                List<Producto> productsSeleccionados = new ArrayList<>();
                productsSeleccionados.add(p);
                if(mesaActual.getPedido().getProductos() != null){
                    System.out.println("Producto agregado al pedido de la mesa");
                    Pedido pedido = mesaActual.getPedido();                   
                    pedido.getProductos().add(p);
                    System.out.println(pedido.getProductos().toString());
                }else{
                    System.out.println("Pedidos creados en la mesa");
                    Pedido pedido = mesaActual.getPedido();
                    pedido.setProductos(productsSeleccionados);
                    System.out.println(pedido.getProductos().toString());
                }
                crearPanelLeftMenu();
            });
            
            flowpane.getChildren().add(principal);
            flowpane.setAlignment(Pos.CENTER);
            
        }        
        return flowpane;
    }
    
    /**
     * Metodo que dibujar productos por letra.
     * @param letra Recibe como parametros un String
     * @return devuelve un FlowPane
     */
    public FlowPane dibujarProductosPorLetra(String letra){
        
        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(20);
        flowpane.setVgap(20);
        for (Producto p : Aplicacion.listaProductos){
            if(!(letra.isEmpty())&&(p.getNombre().trim().length()>=letra.length())&&(p.getNombre().substring(0, letra.length()).toUpperCase().equals(letra.toUpperCase()))){
                System.out.println("ENTROOOOO");
                VBox principal = new VBox();

                HBox parteTop = new HBox();
                Label precio = new Label(String.valueOf(p.getPrecio())+" $");
                precio.setTextFill(Color.WHITE);
                precio.setFont(new Font("Berlin Sans FB", 13));

                precio.setPadding(new Insets(3, 5, 3, 5));
                precio.setStyle("-fx-background-color: #9370DB");
                parteTop.getChildren().add(precio);
                parteTop.setAlignment(Pos.TOP_RIGHT);      

                HBox parteCentro = new HBox();
                ImageView verImagen = new ImageView(new Image(p.getPath()));           
                verImagen.setFitHeight(60);
                verImagen.setFitWidth(60);
                parteCentro.setAlignment(Pos.CENTER);
                parteCentro.getChildren().add(verImagen);

                HBox parteBaja = new HBox();
                Label nombre = new Label(p.getNombre());
                nombre.setFont(new Font("Berlin Sans FB", 13));
                nombre.setPadding(new Insets(3, 5, 3, 5));

                parteBaja.getChildren().add(nombre);
                parteBaja.setAlignment(Pos.BOTTOM_LEFT);
                parteBaja.setStyle("-fx-background-color: #9370DB");

                principal.getChildren().addAll(parteTop,parteCentro,parteBaja);            

                principal.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.50))));
                principal.setSpacing(5);
                principal.setPadding(new Insets(5, 10, 5, 10));
                principal.setStyle("-fx-background-color: #DEB887;");

                principal.setOnMouseClicked((e)->{                
                    List<Producto> productsSeleccionados = new ArrayList<>();
                    productsSeleccionados.add(p);
                    if(mesaActual.getPedido().getProductos() != null){
                        System.out.println("Producto agregado al pedido de la mesa");
                        Pedido pedido = mesaActual.getPedido();                   
                        pedido.getProductos().add(p);
                        System.out.println(pedido.getProductos().toString());
                    }else{
                        System.out.println("Pedidos creados en la mesa");
                        Pedido pedido = mesaActual.getPedido();
                        pedido.setProductos(productsSeleccionados);
                        System.out.println(pedido.getProductos().toString());
                    }
                    crearPanelLeftMenu();
                });
                flowpane.getChildren().add(principal);
                flowpane.setAlignment(Pos.CENTER);
            }
        }
        if(flowpane.getChildren().size()==0){  
            
            System.out.println("No se encontro nada");
            HBox contMsg = new HBox();
            contMsg.setAlignment(Pos.CENTER);
            Label msg = new Label("Lo sentimos, no se ha encontrado el producto.");
            msg.setFont(new Font("Berlin Sans FB", 18));
            msg.setTextFill(Color.RED);
            contMsg.getChildren().add(msg);

            flowpane.getChildren().add(contMsg);
            flowpane.setAlignment(Pos.CENTER);
        }
        return flowpane;
    }
  
    /**
     * Metodo que suma la cantidad total de la mesa pasada como parametro.
     * @param dineroActual recibe un double y que verifique siki kis gafoos lotakc
     */
    public void sumarcantidadMesa(double dineroActual){
        Iterator<Mesa> it = (Aplicacion.listaMesas).iterator();
        while (it.hasNext()) {
           Mesa m = it.next();
           if(m.equals(mesaActual)){
           double ventaDiaria = m.getVentaTotalDiario() + dineroActual;
           m.setVentaTotalDiario(ventaDiaria);
           }
           }
    }
    
    
}
