/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Constantes.DisponibilidadMesa;
import application.Aplicacion;
import application.Mesa;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import personas.Administrador;
import personas.Cliente;
import pkg2pproyectopoo.Inicio;

import java.lang.String;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import personas.Mesero;
import ventas.Pedido;
import ventas.Producto;

/**
 *
 * @author adrig
 */
public class VistaAdmin {

    private HBox botones;
    private Button monitoreo;
    private Button diseno;
    private Button gestion;
    private Button reporte;
    private Button atras;
    boolean validarPlano = false;
    boolean clickval = true;
    private Administrador adminActual;
    private boolean continuarHilo = true;
    Random rd = new Random();

    /**
     * Contructor que crea la pantalla que será mostrado al administrador de la
     * Aplicacion.
     *
     * @param admin recibe como parametro un administrador;
     */
    public VistaAdmin(Administrador admin) {
        Inicio.root.getChildren().clear();
        this.adminActual = admin;
        crearPanelTop();

    }

    /**
     *
     * Crea el panel para almacenar elementos del reporte en la parte top del
     * root.
     */
    public void crearPanelTop() {
        monitoreo = new Button("MONITOREO DEL RESTAURANTE");
        diseno = new Button("DISEÑO RESTAURANTE");
        gestion = new Button("GESTION DE MENU");
        reporte = new Button("VER REPORTE DE VENTAS");
        atras = new Button("SALIR");
        botones = new HBox();
        botones.getChildren().addAll(monitoreo, diseno, gestion, reporte, atras);
        botones.setAlignment(Pos.CENTER);
        botones.setStyle("-fx-background-color: #000000;");
        Inicio.root.setTop(botones);
        optionClick();
    }

    /**
     * Metodo que inicializa los eventos del mouse
     */
    public void optionClick() {
        diseno.setOnMouseClicked((e) -> {
            continuarHilo = false;
            Inicio.root.getChildren().clear();
            Restaurante rtDiseño = new Restaurante(adminActual);
            Pane p = (Pane) Inicio.root.getCenter();
            Pane salir = new Pane();
            Button btSalir = new Button("Salir");
            btSalir.setLayoutX(20);
            btSalir.setLayoutX(40);

            salir.getChildren().add(btSalir);
            Inicio.root.getChildren().add(salir);
            btSalir.setOnMouseClicked((even) -> {
                Inicio.root.getChildren().clear();
                resetearEvent();
                crearPanelTop();
            });
            Iterator<Mesa> it = (Aplicacion.listaMesas).iterator();
            while (it.hasNext()) {
                Mesa m = it.next();
                for (Label lb : Restaurante.labels) {
                    if (String.valueOf(m.getNumMesa()).equals(lb.getText())) {
                        m.setOnMouseDragged((event) -> {
                            m.setLayoutX(event.getSceneX());
                            m.setLayoutY(event.getSceneY());
                            m.setPosX(event.getSceneX());
                            m.setPosY(event.getSceneY());
                            lb.setLayoutX(event.getSceneX() - 5);
                            lb.setLayoutY(event.getSceneY() - 12);
                        });

                        lb.setOnMouseDragged((event) -> {
                            m.setLayoutX(event.getSceneX());
                            m.setLayoutY(event.getSceneY());
                            m.setPosX(event.getSceneX());
                            m.setPosY(event.getSceneY());
                            lb.setLayoutX(event.getSceneX() - 5);
                            lb.setLayoutY(event.getSceneY() - 12);
                        });

                        m.setOnMouseClicked((eve) -> {
                            
                            if (eve.isStillSincePress()) {
                                clickval = false;
                                Inicio.root.getChildren().clear();
                                eliminarMesa(m);
                            }
                        });
                        lb.setOnMouseClicked((eve) -> {
                            
                            if (eve.isStillSincePress()) {
                                clickval = false;
                                Inicio.root.getChildren().clear();
                                eliminarMesa(m);
                            }
                        });

                    }

                }

            }
            Inicio.root.getCenter().setOnMouseClicked((ev) -> {
                if (ev.isStillSincePress() && clickval) {
                    crearMesa(ev.getX(), ev.getY());

                }
            });

        });
        monitoreo.setOnMouseClicked((e) -> {
            continuarHilo = true;
            List<Mesa> lis = Aplicacion.listaMesas;
            Thread th1 = new Thread(new HiloMonitoreo(lis));
            th1.start();
            //SUSTENTACION
            creaPanelReporte();
            reservarMesa(lis);
            
            
        });
        gestion.setOnMouseClicked((e) -> {
            continuarHilo = false;
            Inicio.root.getChildren().clear();
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);

            Button gestionarMenu = new Button("  Gestionar Menú  ");
            gestionarMenu.setOnMouseClicked((e2) -> {
                dibujarProdAdmin();
                
                
                
            });

            Button agregarMenu = new Button("  Agregar Plato  ");
            agregarMenu.setOnMouseClicked((e3) -> {
                pedirDatosProd("Producto registrado exitosamente", false);
            });

            Button eliminarMenu = new Button("  Eliminar Plato  ");
            eliminarMenu.setOnMouseClicked((e4) -> {
                pedirDatosProd("Producto eliminado exitosamente", true);
            });

            Button salir = new Button("          SALIR");
            salir.setOnMouseClicked((e1) -> {
                VistaAdmin vtA = new VistaAdmin(adminActual);
            });
            hbox.getChildren().addAll(gestionarMenu, agregarMenu, eliminarMenu, salir);

            VBox msg = new VBox();
            msg.setAlignment(Pos.CENTER);
            Label mgj = new Label("Seleccione alguna opción");
            mgj.setTextFill(Color.RED);
            mgj.setFont(new Font(22));
            msg.getChildren().add(mgj);

            Inicio.root.setTop(hbox);
            Inicio.root.setCenter(msg);

        });
        reporte.setOnMouseClicked((e) -> {
            continuarHilo = false;
            Inicio.root.getChildren().clear();
            verReporte();
            creaPanelReporte();

        });
        atras.setOnMouseClicked((e) -> {
            continuarHilo = false;
            Inicio.root.getChildren().clear();
            Inicio in = new Inicio();
        });

    }

    /**
     * Crea el panel para almacenar elementos del reporte en la parte derecha
     * del root.
     */
    public void creaPanelReporte() {

        Button atras = new Button("SALIR");
        botones = new HBox();
        botones.setAlignment(Pos.CENTER);
        botones.setStyle("-fx-background-color: #000000;");
        Label txtNombreAdmin = new Label("Administador : " + adminActual.getNombre() + " " + adminActual.getApellido());
        txtNombreAdmin.setFont(new Font(20));
        txtNombreAdmin.setTextFill(Color.WHITE);
        botones.getChildren().addAll(txtNombreAdmin, atras);
        botones.setSpacing(10);
        Inicio.root.setTop(botones);

        atras.setOnMouseClicked((event) -> {
            Inicio.root.getChildren().clear();
            crearPanelTop();
        });

    }

    /**
     * Metodo que valida y verifica la creacion de una mesa en el plano.
     *
     * @param x valor fuera del ragon de la matriz mn
     * @param y recibe como arametro una posicion en elje de las "Y"
     */
    public void crearMesa(double x, double y) {
        VBox parteExterna = new VBox();
        HBox partNombre = new HBox();
        HBox parteApellido = new HBox();
        HBox parteAdvertencia = new HBox();
        if (validarPlano) {
            Label msg3 = new Label("Datos incorrectos!");
            msg3.setTextFill(Color.YELLOW);
            parteAdvertencia.getChildren().add(msg3);
            parteAdvertencia.setAlignment(Pos.CENTER);
            parteExterna.getChildren().add(parteAdvertencia);
            Inicio.root.setCenter(parteExterna);
        }
        validarPlano = false;
        Inicio.root.getChildren().clear();
        Label msg1 = new Label("Capacidad de la Mesa: ");
        TextField txtCapacidad = new TextField();

        Label msg2 = new Label("Numero de la mesa: ");
        TextField textNum = new TextField();

        partNombre.getChildren().addAll(msg1, txtCapacidad);
        parteApellido.getChildren().addAll(msg2, textNum);
        partNombre.setAlignment(Pos.CENTER);
        parteApellido.setAlignment(Pos.CENTER);
        Button btnContinuar = new Button("Continuar");

        HBox parteBoton = new HBox(btnContinuar);
        parteBoton.setAlignment(Pos.CENTER);
        parteExterna.getChildren().addAll(partNombre, parteApellido, parteBoton);

        parteExterna.setAlignment(Pos.CENTER);
        parteExterna.setSpacing(15);
        Inicio.root.setCenter(parteExterna);
        ArrayList<String> e = new ArrayList<>();
        for (Label l : Restaurante.labels) {
            e.add(l.getText());
        }
        btnContinuar.setOnMouseClicked((event) -> {
            if (esNumerico(txtCapacidad.getText().trim()) && esNumerico(textNum.getText().trim()) && textNum.getText().trim().isEmpty() != true && txtCapacidad.getText().trim().isEmpty() != true
                    && Double.parseDouble(txtCapacidad.getText()) < 10 && Double.parseDouble(txtCapacidad.getText()) > 1 && !e.contains(textNum.getText().trim())) {
                System.out.println("ENTRO AL MOUSE");
                Mesa mesita = new Mesa(0.0, null, null, null, DisponibilidadMesa.DISPONIBLE,
                        x, y, Double.parseDouble(txtCapacidad.getText()) * 10, Integer.parseInt(textNum.getText()));
                Aplicacion.listaMesas.add(mesita);
                Inicio.root.getChildren().clear();
                resetearEvent();
                crearPanelTop();

            } else {
                System.out.println("no creado");
                validarPlano = true;
                crearMesa(x, y);
            }
        });

    }

    /**
     * Metodo que genera la tabla de los reportes de acuerdo al rango de fechas
     * pasado como parametro
     */
    public void verReporte() {
        Inicio.root.getChildren().clear();
        VBox vistareporte = new VBox();
        HBox buscador = new HBox();
        TextField fechaini = new TextField();
        fechaini.setPromptText("yyyy-mm-dd");
        fechaini.setFocusTraversable(false);
        TextField fechafin = new TextField();
        fechafin.setPromptText("yyyy-mm-dd");
        fechafin.setFocusTraversable(false);
        TableView<Pedido> tabla = new TableView<>();
        Button buscar = new Button("BUSCAR");

        TableColumn fecha = new TableColumn("Fecha");
        TableColumn mesa = new TableColumn("Mesa");
        TableColumn mesero = new TableColumn("Mesero");
        TableColumn numcuenta = new TableColumn("#Cuenta");
        TableColumn cliente = new TableColumn("Cliente");
        TableColumn total = new TableColumn("Total");
        tabla.getColumns().addAll(fecha, mesa, mesero, numcuenta, cliente, total);

        buscador.getChildren().addAll(new Label("Fecha incio  "), fechaini, new Label("Fecha final"), fechafin, buscar);
        buscador.setSpacing(20);
        buscador.setAlignment(Pos.CENTER);

        vistareporte.getChildren().addAll(buscador, tabla);
        vistareporte.setSpacing(20);
        vistareporte.setAlignment(Pos.CENTER);

        Inicio.root.setCenter(vistareporte);

        buscar.setOnMouseClicked((MouseEvent e) -> {
            try (BufferedReader bf = new BufferedReader(new FileReader("src/recursos/archivos/ventas.txt"))) {
                LocalDate inicio = LocalDate.parse(fechaini.getText());
                LocalDate tope = LocalDate.parse(fechafin.getText());
                List<Pedido> pedidos = new ArrayList<>();
                String line = "";

     
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);
                    String[] data = line.trim().split(",");
                    LocalDate fpedido = LocalDate.parse(data[0]);
                    int nummesa = Integer.parseInt(data[1]);
                    String namemesero = data[2];
                    String ncuenta = data[3];
                    String namecliente = data[4];
                    double venta = Double.parseDouble(data[5]);
                    pedidos.add(new Pedido(String.valueOf(fpedido), nummesa, namemesero, ncuenta, namecliente, venta));
                }
                ObservableList<Pedido> datos = FXCollections.observableArrayList(pedidos);
                //inicio.getDayOfMonth() >= p.getFecha().getDayOfMonth() && p.getFecha().getDayOfMonth() <= tope.getDayOfMonth()
                List<Pedido> pedidosValidados = new ArrayList<>();
                for (Pedido p : datos) {
                    if (p.getFecha().isEqual(tope) || p.getFecha().isEqual(inicio) || (p.getFecha().isBefore(tope) && p.getFecha().isAfter(inicio))) {
                        pedidosValidados.add(p);
                    }
                }
                ObservableList<Pedido> datosvalidados = FXCollections.observableArrayList(pedidosValidados);
                fecha.setCellValueFactory(new PropertyValueFactory<Pedido, String>("pfecha"));
                fecha.setMinWidth(100);
                mesa.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("numMesa"));
                mesa.setMinWidth(100);
                mesero.setCellValueFactory(new PropertyValueFactory<Pedido, String>("nameMesero"));
                mesero.setMinWidth(200);
                numcuenta.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("numeroCuenta"));
                numcuenta.setMinWidth(100);
                cliente.setCellValueFactory(new PropertyValueFactory<Pedido, String>("nameCliente"));
                cliente.setMinWidth(200);
                total.setCellValueFactory(new PropertyValueFactory<Pedido, Double>("total"));
                total.setMinWidth(100);
                tabla.getItems().clear();
                tabla.setItems(datosvalidados);
         
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Ocurrio algo...");
            } catch (NullPointerException ex) {
                System.out.println("Una vaina rara esta pasando...");
                System.out.println(ex);
            } catch (DateTimeParseException ex) {
                System.out.println("Vazio");
                System.out.println(ex);

                HBox fuera = new HBox();
                fuera.setAlignment(Pos.CENTER);
                VBox parte = new VBox();
                Label lb = new Label("Datos incorrectos!, Vuelva a ingresar");
                parte.setAlignment(Pos.CENTER);
                lb.setFont(new Font(24));
                lb.setTextFill(Color.RED);
                lb.setAlignment(Pos.CENTER);
                Button continuar = new Button("  Continuar  ");
                continuar.setAlignment(Pos.CENTER);
                continuar.setOnMouseClicked((eventoCont) -> {
                    verReporte();
                    creaPanelReporte();
                });
                parte.getChildren().addAll(lb, continuar);
                fuera.getChildren().add(parte);
                Inicio.root.setCenter(fuera);

            }
        });

    }

    /**
     * Metodo que valida si el String ingresado es un numero.
     * @param valor recibe un String
     * @return boolean sobre de condimente;
     */
    public static boolean esNumerico(String valor) {
        try {
            if (valor != null) {
                Integer.parseInt(valor);
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para resetear el evento que ocurre al arrastrar las mesas.
     */
    public static void resetearEvent() {
        for (Mesa m : (Aplicacion.listaMesas)) {
            for (Label lb : Restaurante.labels) {
                if (String.valueOf(m.getNumMesa()).equals(lb.getText())) {
                    m.setOnMouseDragged((event) -> {
                    });
                    m.setOnMouseDragged((event) -> {
                    });
                }
                m.setOnMouseClicked((evet) -> {
                });
                m.setOnMouseClicked((evet) -> {
                });
            }

        }

    }

    /**
     * Hilo que actualiza la vista del monitoreo deo admin cada 5 min
     */
    public class HiloMonitoreo implements Runnable {

        private List<Mesa> listaMesas;

        public HiloMonitoreo(List<Mesa> listaMesas) {
            this.listaMesas = listaMesas;
        }

        @Override
        public void run() {
            while(continuarHilo){
            Platform.runLater(() -> {
                Pane contenedorGen = new Pane();
                Inicio.root.getChildren().clear();
                Restaurante rtmonitero = new Restaurante(adminActual);
                Pane paneMesas = rtmonitero.getPaneMesas();
                for (Mesa m : listaMesas) {
                    m.setOnMouseEntered((event) -> {
                        double posX = event.getSceneX();
                        double posY = event.getSceneY();

                        contenedorGen.setLayoutX(posX);
                        contenedorGen.setLayoutY(posY);
                        contenedorGen.setMinSize(100, 80);
                        contenedorGen.toFront();

                        contenedorGen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5))));
                        contenedorGen.setStyle("-fx-background-color: #ffffff;");

                        VBox vbox = new VBox();
                        vbox.toFront();
                        vbox.setPadding(new Insets(4, 4, 4, 8));

                        vbox.setAlignment(Pos.CENTER);
                        Label lbl = new Label("Disponibilidad: " + m.getEstado());
                        String nameCliente = null;
                        String apellidCliente = null;
                        if (m.getClienteAsociado() == null) {
                            nameCliente = "No tiene";
                            apellidCliente = "";
                        } else {
                            nameCliente = m.getClienteAsociado().getNombre();
                            apellidCliente = m.getClienteAsociado().getApellido();
                        }
                        Label lbl2 = new Label("Cliente: " + nameCliente + " " + apellidCliente);
                        String nameMesero = null;
                        String apellidMesero = null;
                        if (m.getClienteAsociado() == null) {
                            nameMesero = "No tiene";
                            apellidMesero = "";
                        } else {
                            nameMesero = "";
                            apellidMesero = "";
                        }
                        Label lbl3 = new Label("Mesero: " + nameMesero + " " + apellidMesero);
                        Label lbl4 = new Label("Total Diario: " + String.valueOf(m.getVentaTotalDiario()));

                        vbox.getChildren().addAll(lbl, lbl2, lbl3, lbl4);
                        contenedorGen.getChildren().add(vbox);

                        paneMesas.getChildren().add(contenedorGen);

                    });

                        m.setOnMouseExited((eventoFuera) -> {
                            contenedorGen.getChildren().clear();
                            paneMesas.getChildren().remove(contenedorGen);
                        });
                    }
                    crearPanelTop();
                });

                try { 
                    Thread.sleep(120000);
                    ArrayList<Mesa> mesasDisponibles = (ArrayList<Mesa>)Aplicacion.obtenerMesasDisponibles();
                    ArrayList<Cliente> clientes = new ArrayList<>();
                    clientes.add(new Cliente("Javier", "Dilon"));clientes.add(new Cliente("Danilo", "Baltraz"));
                    clientes.add(new Cliente("Hiuston", "Alvarez"));clientes.add(new Cliente("Lenin", "Molina"));
                    clientes.add(new Cliente("Maria", "Corrales"));clientes.add(new Cliente("Mayra", "Zapata"));

                    Mesero meseroSelected = Aplicacion.obtenerMeseroAleatorio();
                    int ind2 = rd.nextInt(clientes.size());
                    int ind1 = rd.nextInt(mesasDisponibles.size());

                    Mesa mesaSelected = mesasDisponibles.get(ind1);
                    Cliente clienteSelected = clientes.get(ind2);
                    mesaSelected.setEstado(DisponibilidadMesa.NO_DISPONIBLE);
                    mesaSelected.setClienteAsociado(clienteSelected);
                    mesaSelected.setMeseroAsociado(meseroSelected);
                    mesaSelected.sumarVentaDiaria(12.30);               

                } catch (Exception ex) {
                    System.out.println(ex); 
                }
             
            }            
        }
    }
    /**
     * Dibuja el panel productos admin.
     */
    public void dibujarProdAdmin() {
        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(12);
        flowpane.setVgap(12);
        for (Producto p : Aplicacion.listaProductos) {
            VBox principal = new VBox();

            HBox parteTop = new HBox();
            Label precio = new Label(String.valueOf(p.getPrecio()) + " $");
            precio.setTextFill(Color.WHITE);
            precio.setFont(new Font("Berlin Sans FB", 10));

            precio.setPadding(new Insets(3, 4, 3, 4));
            precio.setStyle("-fx-background-color: #9370DB");
            parteTop.getChildren().add(precio);
            parteTop.setAlignment(Pos.TOP_RIGHT);

            HBox parteCentro = new HBox();
            ImageView verImagen = new ImageView(new Image(p.getPath()));
            verImagen.setFitHeight(35);
            verImagen.setFitWidth(35);
            parteCentro.setAlignment(Pos.CENTER);
            parteCentro.getChildren().add(verImagen);

            HBox parteBaja = new HBox();
            Label nombre = new Label(p.getNombre());
            nombre.setFont(new Font("Berlin Sans FB", 11));
            nombre.setPadding(new Insets(3, 4, 3, 4));
            nombre.setTextFill(Color.WHITE);

            parteBaja.getChildren().add(nombre);
            parteBaja.setAlignment(Pos.BOTTOM_LEFT);
            parteBaja.setStyle("-fx-background-color: #9370DB");

            principal.getChildren().addAll(parteTop, parteCentro, parteBaja);

            principal.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.50))));
            principal.setSpacing(3);
            principal.setPadding(new Insets(3, 4, 3, 4));
            principal.setStyle("-fx-background-color: #DEB887;");
            
            principal.setOnMouseClicked((click)->{
                HBox interno = new HBox();
                VBox externo = new VBox();
                
                externo.setAlignment(Pos.CENTER);
                externo.setSpacing(13);
                interno.setAlignment(Pos.CENTER);
                interno.setSpacing(5);
                Label mensaje = new Label("Ingrese el nuevo precio: ");
                mensaje.setFont(new Font(15));
                
                TextField ingresoValor = new TextField();
                ingresoValor.setPromptText("Ingrese el precio");
                ingresoValor.setFocusTraversable(false);
                
                Button continuar = new Button("  Continuar  ");
                Label error =new Label("");
                try{
                    continuar.setOnMouseClicked((cont)->{
                        error.setText("");
                        if(isNumeric(ingresoValor.getText()) == true ){
                            p.setPrecio(Double.valueOf(ingresoValor.getText()));
                            dibujarProdAdmin();
                        }else{
                            ingresoValor.clear();
                            error.setText("Debe ingresar un valor numérico.");
                            error.setTextFill(Color.RED);
                            Inicio.root.setCenter(externo);
                        }
                    });
                }catch(Exception e){
                    ingresoValor.clear();
                    error.setText("Debe ingresar un valor numérico.");
                    error.setTextFill(Color.RED);
                    Inicio.root.setCenter(externo);
                }
                interno.getChildren().addAll(mensaje,ingresoValor);
                externo.getChildren().addAll(interno,continuar,error);
                Inicio.root.setCenter(externo);

            });

            flowpane.getChildren().add(principal);
            flowpane.setAlignment(Pos.CENTER);
            Inicio.root.setCenter(flowpane);
        }
    }

    /**
     * Metodo que valida si es un numero.
     * @param cad recibe un String de las categorias
     * @return devuelve un boolean
     */
    public static boolean isNumeric(String cad) {
        try {
            Double.parseDouble(cad);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
/**
 * Metodo para gestionar la gestion de productos para eliminar o borrar los productos.
 * @param mensaje recibe como parametros un String mensaje
 * @param borrar recibe como parametro un boolean y un String 
 */
    public void pedirDatosProd(String mensaje, boolean borrar) {

        HBox principal = new HBox();
        principal.setAlignment(Pos.CENTER);

        VBox interno = new VBox();
        interno.setAlignment(Pos.CENTER);
        interno.setSpacing(10);

        HBox partNombre = new HBox();
        partNombre.setSpacing(5);

        Label lblNombre = new Label("Nombre del Producto:     ");
        TextField textNombre = new TextField();
        textNombre.setPrefWidth(175);
        textNombre.setPromptText("Ingrese nombre");
        textNombre.setFocusTraversable(false);
        partNombre.getChildren().addAll(lblNombre, textNombre);

        HBox partCate = new HBox();
        partCate.setSpacing(5);
        Label lblCate = new Label("Nombre de la Categoría: ");
        TextField textCate = new TextField();
        textCate.setPrefWidth(175);
        textCate.setPromptText("Ingrese nombre");
        textCate.setFocusTraversable(false);
        partCate.getChildren().addAll(lblCate, textCate);

        HBox partPrecio = new HBox();
        partPrecio.setSpacing(5);
        Label lblPrecio = new Label("Precio del producto:        ");
        TextField textPrecio = new TextField();
        textPrecio.setPrefWidth(175);
        textPrecio.setPromptText("Ingrese cantidad");
        textPrecio.setFocusTraversable(false);
        partPrecio.getChildren().addAll(lblPrecio, textPrecio);
        if (!borrar) {
            HBox partPath = new HBox();
            partPath.setSpacing(5);
            Label lblPath = new Label("Path del producto:           ");
            TextField textPath = new TextField();
            textPath.setPrefWidth(175);
            textPath.setPromptText("/recursos/nombreImagen.jpg");
            textPath.setFocusTraversable(false);
            partPath.getChildren().addAll(lblPath, textPath);

            Button continuar = new Button("  Continuar  ");

            continuar.setOnMouseClicked((eventAgre) -> {
                try {
                    Image imagenPruebaErr = new Image(textPath.getText());
                    if (isNumeric(textPrecio.getText()) == true && !textNombre.getText().isEmpty() && !textCate.getText().isEmpty() && !textPrecio.getText().isEmpty() && !textPath.getText().isEmpty()) {
                        Producto prod = new Producto(textNombre.getText(), textCate.getText(), Double.parseDouble(textPrecio.getText()), textPath.getText());
                        Aplicacion.listaProductos.add(prod);
                        Label tetx = new Label(mensaje);
                        tetx.setTextFill(Color.GREEN);
                        HBox partResp = new HBox(tetx);
                        partResp.setAlignment(Pos.CENTER);
                        interno.getChildren().add(partResp);
                        textNombre.clear();
                        textCate.clear();
                        textPrecio.clear();
                        textPath.clear();
                    } else if (isNumeric(textPrecio.getText()) == false) {
                        Label msg = new Label("Debe ingresar un número!.");
                        msg.setAlignment(Pos.CENTER);
                        msg.setFont(new Font(20));
                        msg.setTextFill(Color.RED);
                        Inicio.root.setCenter(msg);
                    } else {
                        System.out.println("Campos vacios");
                        Label msgVacio = new Label("Existen campos vacios\nDebe llenarlos todos.");
                        msgVacio.setAlignment(Pos.CENTER);
                        msgVacio.setFont(new Font(20));
                        msgVacio.setTextFill(Color.RED);
                        Inicio.root.setCenter(msgVacio);
                    }
                } catch (Exception exe) {
                    System.out.println(exe);
                    Label msgVacio = new Label("           Path ingresado incorrecto\n"
                            + "Verifique la direccion de la imagen del producto.");
                    msgVacio.setAlignment(Pos.CENTER);
                    msgVacio.setFont(new Font(20));
                    msgVacio.setTextFill(Color.RED);
                    Inicio.root.setCenter(msgVacio);
                }
            });
            interno.getChildren().addAll(partNombre, partCate, partPrecio, partPath, continuar);
        } else {
            Button continuar = new Button("  Continuar  ");
            continuar.setOnMouseClicked((borr -> {
                if (isNumeric(textPrecio.getText()) == true && !textNombre.getText().isEmpty() && !textCate.getText().isEmpty() && !textPrecio.getText().isEmpty()) {
                    boolean borrado = Aplicacion.borrarProducto(textNombre.getText(), textCate.getText(), Double.parseDouble(textPrecio.getText()));
                    if (borrado) {
                        Label tetx = new Label(mensaje);
                        tetx.setTextFill(Color.GREEN);
                        HBox partResp = new HBox(tetx);
                        partResp.setAlignment(Pos.CENTER);
                        interno.getChildren().add(partResp);
                        textNombre.clear();
                        textCate.clear();
                        textPrecio.clear();
                    } else {
                        Label tetx = new Label("No se encontró el producto con las caracteristicas proporcionadas\n"
                                + "                                         Vuelva a intentarlo.");
                        tetx.setTextFill(Color.RED);
                        HBox partResp = new HBox(tetx);
                        partResp.setAlignment(Pos.CENTER);
                        interno.getChildren().add(partResp);
                        textNombre.clear();
                        textCate.clear();
                        textPrecio.clear();
                    }
                } else if (isNumeric(textPrecio.getText()) == false) {
                    Label msg = new Label("Debe ingresar un número!.");
                    msg.setAlignment(Pos.CENTER);
                    msg.setFont(new Font(20));
                    msg.setTextFill(Color.RED);
                    Inicio.root.setCenter(msg);
                } else {
                    System.out.println("Campos vacios");
                    Label msgVacio = new Label("Existen campos vacios\nDebe llenarlos todos.");
                    msgVacio.setAlignment(Pos.CENTER);
                    msgVacio.setFont(new Font(20));
                    msgVacio.setTextFill(Color.RED);
                    Inicio.root.setCenter(msgVacio);
                }
            }));
            interno.getChildren().addAll(partNombre, partCate, partPrecio, continuar);
        }

        principal.getChildren().add(interno);
        Inicio.root.setCenter(principal);
    }

    /**
     * Metodo que modifica para eliminarla
     *
     * @param m recibe un booleano que indica eliminar si es true o
     * agregar si es false.
     */
    public void eliminarMesa(Mesa m) {
        VBox parteExterna = new VBox();
        HBox Anuncio = new HBox();
        Label lb = new Label("Desea eliminar la mesa: " + m.getNumMesa());
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
        HBox parteBoton = new HBox(btnSi, btnNo);
        parteBoton.setSpacing(30);
        parteBoton.setAlignment(Pos.CENTER);
        parteExterna.getChildren().addAll(Anuncio, parteBoton);
        parteExterna.setAlignment(Pos.CENTER);
        parteExterna.setSpacing(10);

        btnSi.setOnMouseClicked((event) -> {
            Inicio.root.getChildren().clear();
            System.out.println("Eliminando mesa");
            Aplicacion.listaMesas.remove(m);
            resetearEvent();
            clickval = true;
            crearPanelTop();

        });
        btnNo.setOnMouseClicked((event) -> {
            Inicio.root.getChildren().clear();
            resetearEvent();
            clickval = true;
            crearPanelTop();

        });
        Inicio.root.setCenter(parteExterna);

    }
    //SUSTENTACION
    public void reservarMesa(List<Mesa> mesas){
        Inicio.root.getChildren().clear();
        VBox menu= new VBox();
        TextField cliente= new TextField();
        HBox pedido= new HBox(new Label("Ingrese el nombre del cliente"));
        HBox apellido= new HBox(new Label("Ingrese el apellido del cliente"));
        TextField LastName= new TextField();
        apellido.getChildren().add(LastName);
        apellido.setSpacing(20);
        pedido.getChildren().add(cliente);
        pedido.setSpacing(20);
        Button confirma= new Button("RESERVAR");
        menu.getChildren().addAll(new Label("RESERVAR MESA"),pedido,apellido, confirma);
        List<Mesa> reservaciones= new ArrayList<>();
        for(Mesa m: mesas){
           if(Aplicacion.obtenerMesasDisponibles().contains(m)){
               m.setOnMouseClicked((e)->{
                   Inicio.root.setCenter(menu);
                   String nombre= cliente.getText();
                   String last= LastName.getText();
                   confirma.setOnMouseClicked((event)->{
                       m.setClienteAsociado(new Cliente(nombre,last));
                       m.setEstado(DisponibilidadMesa.RESERVADA);
                       Restaurante rtmonitero = new Restaurante(adminActual);
                       Pane paneMesas = rtmonitero.getPaneMesas();
                       Pane contenedorGen = new Pane();
                       double posX = event.getSceneX();
                        double posY = event.getSceneY();

                        contenedorGen.setLayoutX(posX);
                        contenedorGen.setLayoutY(posY);
                        contenedorGen.setMinSize(100, 80);
                        contenedorGen.toFront();

                        contenedorGen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5))));
                        contenedorGen.setStyle("-fx-background-color: #ffffff;");

                        VBox vbox = new VBox();
                        vbox.toFront();
                        vbox.setPadding(new Insets(4, 4, 4, 8));

                        vbox.setAlignment(Pos.CENTER);
                        Label lbl = new Label("Disponibilidad: " + m.getEstado());
                        String nameCliente = null;
                        String apellidCliente = null;
                        if (m.getClienteAsociado() == null) {
                            nameCliente = "No tiene";
                            apellidCliente = "";
                        } else {
                            nameCliente = m.getClienteAsociado().getNombre();
                            apellidCliente=m.getClienteAsociado().getApellido();
                       
                        }
                        Label lbl2 = new Label("Cliente: " + nombre + " "+ last);
                        String nameMesero = null;
                        String apellidMesero = null;
                        if (m.getClienteAsociado() == null) {
                            nameMesero = "No tiene";
                            apellidMesero = "";
                        } else {
                            nameMesero = "MESA RESERVADA";
                            apellidMesero = "";
                        }
                        Label lbl3 = new Label("Mesero: " + nameMesero + " " + apellidMesero);
                        Label lbl4 = new Label("Total Diario: " + String.valueOf(m.getVentaTotalDiario()));

                        vbox.getChildren().addAll(lbl, lbl2, lbl3, lbl4);
                        contenedorGen.getChildren().add(vbox);

                        paneMesas.getChildren().add(contenedorGen);
                        
                        try(ObjectOutputStream  ob= new ObjectOutputStream(new FileOutputStream("src/recursos/archivos/reservaciones.dat"))){
                            reservaciones.add(m);
                            ob.writeObject(m);
                        
                        } catch (FileNotFoundException ex) {
                           Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (IOException ex) {
                           Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                   });
                   
               });
           }
       
    }

    }}