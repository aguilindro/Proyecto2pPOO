/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Constantes.DisponibilidadMesa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import personas.Administrador;
import personas.Cliente;
import personas.Mesero;
import personas.Usuario;
import ventas.Pedido;
import ventas.Producto;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class Aplicacion {

    public static Set<Usuario> conjuntoUsers = new HashSet<>();
    public static List<Mesa> listaMesas = new ArrayList<>();
    public static List<Producto>listaProductos = new ArrayList<>();
    public static List<Mesa> mesasReservadas= new ArrayList<>();

    /**
     * Metodo que genera usuarios y los guarda en un archivo binario.
     */
    public void guardarDatos() {
        Usuario admin1 = new Administrador("adminuser", "adminuser", "theBest", "Boss");
        Usuario admin2 = new Administrador("adminuser2", "adminuser2", "Kevin", "Chevez");
        Usuario admin3 = new Administrador("adminuser3", "adminuser3", "Eugenio", "Derbez");
        Usuario admin4 = new Administrador("t", "t", "theBest", "Boss");
        Usuario mesero1 = new Mesero("meserouser", "meserouser", "Pepito", "Ramirez");
        Usuario mesero2 = new Mesero("meserouser2", "meserouser2", "Diego", "Silva");
        Usuario mesero3 = new Mesero("meserouser3", "meserouser3", "Pamela", "Crow");
        Usuario mesero4 = new Mesero("meserouser4", "meserouser4", "Angel", "Guale");
        Usuario mesero5 = new Mesero("meserouser5", "meserouser5", "Diego", "Sevilla");
        Usuario mesero6 = new Mesero("meserouser6", "meserouser6", "Patricia", "Valdivieso");
        Usuario mesero7 = new Mesero("m", "m", "Ramon", "Headshot");

        Set<Usuario> usersSet = new HashSet<>();usersSet.add(admin4);usersSet.add(mesero7);
        usersSet.add(admin1);usersSet.add(admin2);usersSet.add(admin3);
        usersSet.add(mesero1);usersSet.add(mesero2);usersSet.add(mesero3);
        usersSet.add(mesero4);usersSet.add(mesero5);usersSet.add(mesero6);
        
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("src/recursos/archivos/users.dat"))) {
            obj.writeObject(usersSet);
            System.out.println("Usuarios guardados");
        } catch (IOException e) {
            System.out.println("Error al guardar users" + e.getMessage());
        }  
        
        
        Producto p1 = new Producto("Cerveza Corona", "Bebidas Alcohólicas", 1.25, "/recursos/cervezacorona.jpg");
        Producto p2 = new Producto("Cerveza Heineken", "Bebidas Alcohólicas", 2.09,"/recursos/cervezaheineken.jpg");
        Producto p3 = new Producto("Cerveza Modelo", "Bebidas Alcohólicas", 1.19, "/recursos/cervezamodelo.jpg");
        Producto p4 = new Producto("Ensalada con Soda", "Ofertas/Combos", 4.99, "/recursos/ensaladasoda.jpg");
        Producto p5 = new Producto("Filete Pescado", "Platos a la carta", 4.15, "/recursos/filetepescado.jpg");
        Producto p6 = new Producto("Hamburguesa", "Comida Rapida", 2.55, "/recursos/hamburguesa.jpg");
        Producto p7 = new Producto("Hamburguesa con Soda", "Ofertas/Combos", 4.99, "/recursos/hamburguesasoda.jpg");
        Producto p8 = new Producto("Hotdog", "Comida Rápida", 1.25, "/recursos/hotdog.png");
        Producto p9 = new Producto("Jugo de Arandano", "Jugos", 1.75, "/recursos/jugoarandano.jpg");
        Producto p10 = new Producto("Jugo de Naranja", "Jugos", 2.12, "/recursos/jugonaranja.png");
        Producto p11 = new Producto("Lasaña", "Platos a la carta", 5.19, "/recursos/lasana.jpg");
        Producto p12 = new Producto("Limonada", "Jugos", 2.09, "/recursos/limonada.jpg");
        Producto p13 = new Producto("Pizza", "Comida Rápida", 7.57, "/recursos/pizza.png");
        Producto p14 = new Producto("Filete de Pollo", "Platos a la carta", 5.66, "/recursos/pollo.jpg");
        Producto p15 = new Producto("Postre de Chocolate", "Postres", 3.55, "/recursos/postrechocolate.jpg");
        Producto p16 = new Producto("Postre de Fresa", "Postres", 2.52, "/recursos/postrefresa.jpg");
        Producto p17 = new Producto("Postre de Oreo", "Postres", 3.19, "/recursos/postreoreo.png");
        Producto p18 = new Producto("Sanduche de Papas", "Comida Rápida", 2.59, "/recursos/sanduchepapas.jpg");
        Producto p19 = new Producto("Soda de Limon", "Sodas", 1.57, "/recursos/sodalimon.png");
        Producto p20 = new Producto("Soda de Mango", "Sodas", 2.15, "/recursos/sodamango.png");
        Producto p21 = new Producto("Soda de Naranja", "Sodas", 2.12, "/recursos/sodanaranja.png");
        Producto p22 = new Producto("Taco", "Comida Rápida", 3.75, "/recursos/taco.jpg");
        
        List<Producto> listProduct = new ArrayList<>(); listProduct.add(p1); listProduct.add(p2); listProduct.add(p3);
        listProduct.add(p4); listProduct.add(p5);listProduct.add(p6);listProduct.add(p7);listProduct.add(p8);listProduct.add(p9);
        listProduct.add(p10);listProduct.add(p11);listProduct.add(p12);listProduct.add(p13);listProduct.add(p14);listProduct.add(p15);
        listProduct.add(p16);listProduct.add(p17);listProduct.add(p18);listProduct.add(p19);
        listProduct.add(p20);listProduct.add(p21);listProduct.add(p22);
        
        try(ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("src/recursos/archivos/productos.dat"))){
            obj.writeObject(listProduct);
            System.out.println("Productos guardados");
        }catch(IOException e){
            System.out.println("Error al guardar productos " + e);
        }
        
        List<Producto> listP1 = new ArrayList<>(); listP1.add(p1); listP1.add(p1); listP1.add(p3);
        List<Producto> listP2 = new ArrayList<>(); listP1.add(p7); listP1.add(p10); listP1.add(p21);
        List<Producto> listP3 = new ArrayList<>(); listP1.add(p7); listP1.add(p5); listP1.add(p18);
        List<Producto> listP4 = new ArrayList<>(); listP1.add(p9); listP1.add(p17); listP1.add(p15);
        
        
        Mesa mesa1 = new Mesa(0,null,null,null,DisponibilidadMesa.DISPONIBLE, 100,215,45,1);
        Mesa mesa2 = new Mesa(25,new Cliente("Marcelo", "Perez"),new Pedido(null,listP1, 2, LocalDate.now(), (Mesero)mesero1),(Mesero)mesero1,DisponibilidadMesa.NO_DISPONIBLE, 600,215,45,2);
        mesa2.getPedido().setMesa(mesa2);
        Mesa mesa3 = new Mesa(40,new Cliente("Rocio", "Mera"),new Pedido(null,listP2, 3, LocalDate.now(), (Mesero)mesero7),(Mesero)mesero7,DisponibilidadMesa.NO_DISPONIBLE, 350,215,50,3);
        mesa3.getPedido().setMesa(mesa3);
        Mesa mesa4 = new Mesa(10,null,null,null,DisponibilidadMesa.DISPONIBLE, 225,115,60,4);
        Mesa mesa5 = new Mesa(0,new Cliente("Adriana", "Guilindro"),null,(Mesero)mesero5,DisponibilidadMesa.NO_DISPONIBLE, 475,115,60,5);
        Mesa mesa6 = new Mesa(0.50,null,null ,null,DisponibilidadMesa.DISPONIBLE, 475,315,38,6);
        Mesa mesa7 = new Mesa(1.25,new Cliente("Marco", "Tulio"), new Pedido(null,listP3, 7, LocalDate.now(), (Mesero)mesero7),(Mesero)mesero7,DisponibilidadMesa.NO_DISPONIBLE, 225,315,38,7);
        mesa7.getPedido().setMesa(mesa7);
        Mesa mesa8 = new Mesa(10,new Cliente("Kevin", "Guilindro"),new Pedido(null,listP4, 8, LocalDate.now(), (Mesero)mesero1),(Mesero)mesero1,DisponibilidadMesa.NO_DISPONIBLE, 350,365,38,8);
        mesa8.getPedido().setMesa(mesa8);
        List<Mesa> listMesas = new ArrayList<>();listMesas.add(mesa1);listMesas.add(mesa2);
        listMesas.add(mesa3);listMesas.add(mesa4);listMesas.add(mesa5);listMesas.add(mesa6);
        listMesas.add(mesa7);listMesas.add(mesa8);
        
        try (ObjectOutputStream obje = new ObjectOutputStream(new FileOutputStream("src/recursos/archivos/mesas.dat"))) {
            obje.writeObject(listMesas);
            System.out.println("Mesas guardadas");
        } catch (IOException e) {
            System.out.println("Error al guardar mesas " + e);
        }
    }

    /**
     * Metodo que lee un archivo binario, obtiene una lista y carga el Set
     * static conjuntoUsers de la clase.
     * @return devuelve un HashSet de usuarios.
     */
    public HashSet<Usuario> cargarUsuarios() {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("src/recursos/archivos/users.dat"))) {
            System.out.println("Usuarios Cargados");
            return (HashSet<Usuario>)obj.readObject();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        System.out.println("Algo anda mal en usuarios!!");
        return null;
    }
    
    
    /**
     * Metodo que lee un archivo binario, obtiene una lista y carga la 
     * lista static listMesas de la clase.
     * @return devuelve un ArrayList
     */
    public ArrayList<Mesa> cargarMesas(){
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("src/recursos/archivos/mesas.dat"))) {
            System.out.println("Mesas Cargadas");
            return (ArrayList<Mesa>)obj.readObject();
        } catch (IOException ex) {
            System.out.println("Error  tipo: "+ex);
        } catch (ClassNotFoundException e) {
            System.out.println("Error tipo: " + e);
        }
        System.out.println("Algo anda mal en mesas!!");
        return null;
    }
    
    
    /**
     * Metodo que lee un archivo binario, obtiene la lista y carga la lista
     * estática listaProductos perteneciente a la clase
     * @return ArrayList devuelve un arrglo de productos
     */
    public ArrayList<Producto> cargarProductos(){
        try(ObjectInputStream inp = new ObjectInputStream(new FileInputStream("src/recursos/archivos/productos.dat"))){
            System.out.println("Productos cargados");
            return (ArrayList<Producto>)inp.readObject();
        }catch(IOException e){
            System.out.println("Error tipo: " + e);
        }catch(ClassNotFoundException ex){
            System.out.println("Error  tipo: "+ex);
        }
        return null;
    }

    /**
     * Metodo para obtener usuario.
     *
     * @param usuario Recibe un tipo de dato String con el nombre del usuario a
     * buscar
     * @param clave Recibe un tipo de dato String con la contraseña del usuario
     * a buscar
     * @return user Devuelve un objeto tipo Usuario con las coincidencias
     * encontradas, si no encuentra nada retorna null.
     */
    public static Usuario obtenerUser(String usuario, String clave) {
        for (Usuario usr : conjuntoUsers) {
            if (usr.getUsuario().equals(usuario) && usr.getContrasena().equals(clave)) {
                return usr;
            }
        }
        return null;
    }
    
    /**
     * Metodo que verifica si en la listas de mesas se encuentra el mesero que 
     * fué pasado como parametro
     * @param mesero Recibe como parámetro un Mesero
     * @return devuelve un boolean indicando si se encuentra el mesero en la
     * lista o no.
     */
    public static boolean isMeseroInLista(Mesero mesero){
        boolean respuesta = false;
        for (Mesa mesa : listaMesas){
            if(mesa.getMeseroAsociado() != null){
                respuesta = mesa.getMeseroAsociado().equals(mesero);
            }
        }
        return respuesta;
    }
    
    /**
     * Metodo estático que crea un mapa con clave String categoria 
     * y Valor List de Producto
     * @return retorna un HashMap devuelve un mapa con clave un
     * String  y valor List
     */
    public static HashMap<String, List<Producto>> crearMapaCategoriaProduct(){
        HashMap<String, List<Producto>> mapaResponse = new HashMap<>();
        for(Producto p : Aplicacion.listaProductos){
            String categoria = p.getCategoria();
            if(mapaResponse.containsKey(categoria)){
                ArrayList<Producto> list = (ArrayList<Producto>)mapaResponse.get(categoria);
                list.add(p);
                mapaResponse.put(categoria, list);
            }else{
                List<Producto> lista = new ArrayList<>();
                lista.add(p);
                mapaResponse.put(categoria, lista);
            }
        }
        System.out.println(mapaResponse.toString());
        return mapaResponse;
    }
    /**
     * Metodo que borra productos.
     * @param nombre Recibe un String nombre
     * @param categoria Recibe un String categoria
     * @param valor Recibe un double con el valor del producto
     * @return devuelve un boolea para saber si se realizó la operacion
     */
    public static boolean borrarProducto(String nombre, String categoria, double valor){
        boolean borrado=false;
        for(Producto prod : listaProductos){
            if(prod.getNombre().equals(nombre)&&prod.getCategoria().equals(categoria)&&prod.getPrecio()==valor){
                listaProductos.remove(prod);
                borrado = true;               
            }
        }
        return borrado;
    }
    
    /**
     * Obtiene la mesas disponibles.
     * @return devuelve una Lista de mesa
     */
    public static List<Mesa> obtenerMesasDisponibles(){
        List<Mesa> mesasDisponibles = new ArrayList<>();
        for(Mesa m : listaMesas){
            if(m.getEstado().equals(DisponibilidadMesa.DISPONIBLE)){
                mesasDisponibles.add(m);
            }
        }
        return mesasDisponibles;
    }
    
    /**
     * Obtiene un mesero Aleatorio.
     * @return  devuelve un mesejo selccionana aleatroiamente
     */
    public static Mesero obtenerMeseroAleatorio(){
        Random rd = new Random();
        List<Mesero> listaMeseros = new ArrayList<>();
        for(Usuario u : conjuntoUsers){
            if(u instanceof Mesero){
                Mesero m = (Mesero)u;
                listaMeseros.add(m);
            }
        }
        return listaMeseros.get(rd.nextInt(listaMeseros.size()));
    }
    
    /**
     *
     * @return
     */
    public List<Mesa> cargarReservas(){
    try(ObjectInputStream ob= new ObjectInputStream(new FileInputStream("src/recursos/archivos/reservaciones.dat"))){
        return mesasReservadas=(ArrayList<Mesa>)ob.readObject();
    
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
}
