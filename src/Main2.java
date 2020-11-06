
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Clase ejecutable del videojuego
 * @author RACHEL_BRYAN
 * @version 1.0
 */
public class Main2 extends Application {
    private static String C5 = "w";

    /**
     * Metodo para generar a las cartas del jugador 1 y 2
     * @param cantidad
     *            Cantidad de Cartas que se quieren generar .
     * @return un arreglo de String con las cartas aleatorias.
     */
    public static String[] generarCartasAleatorias1(int cantidad) {
        String[] cartaAleatoriaJ1 = new String[cantidad];

        String[] cartasJ1 = { "Gandling", "Gigante", "Gran", "Mal", "Micro", "Pescador", "Plastabot", "Reina",
                "Rey", "Sacerdotisa", "Congelar", "Almacen","Brote","Comunion","Hacerse","Lluvia","Lunar","CMana","Parejo","Quemadura","Solar","Curar", "Explosión", "Favor", "Forma", "Matriz", "PoderSupremo",
                "Portal", "Purificar", "Roba"};

        for (int i = 0; i < cantidad; i++) {
            cartaAleatoriaJ1[i] = cartasJ1[(int) (Math.floor(Math.random() * ((cartasJ1.length - 1) - 0 + 1) + 0))];
        }
        return cartaAleatoriaJ1  ;
    }


    /**
     * Variables de Prueba
     */
    final String Gandling = "Gandling severo";
    final String Gigante = "Gigante Arcano";
    final String Gran = "Gran Maligno Descomunal";
    final String Mal = "Mal'Ganis";
    final String Micro = "Micromomia";
    final String Pescador = "Pescador Fantasma";
    final String Plastabot = "Plastabot";
    final String Reina= "Reina de dolor";
    final String Rey = "El Rey Exámine";
    final String Sacerdotisa = "Sacerdotisa de Furia";


    /**
     * La variable que controla la ejecucion del Servidor o el Cliente.
     */
    private boolean Rol;

    /**
     * Variables utilizadas en la interfaz
     */
    private TextArea Mensajes = new TextArea();

    private ImageView IVspecial = new ImageView();

    private Label labVida = new Label();

    private Label labMana = new Label();

    /**
     * Instancia a un nuevo juegador
     */
    Jugador jugador = new Jugador();


    /**
     * Objeto de la clase Network para la conexión entre el servidor y el cliente
     */
    private Network comunicacion;

    /**
     * Parte de la interfaz y activadores del envio de mensajes
     * @return La ventana del chat(root)
     */
    private Scene ContenidoScene() throws Exception{

        //Prueba del Json
        while (true){
            Stack deckJ1 = new Stack();
            ListaCircular listaCircular = new ListaCircular();
            //Stack deckJ2 = new Stack();
            try {
                String jsonp;

                BufferedReader br = new BufferedReader(new FileReader("cards.json"));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }

                    jsonp = sb.toString();
                } finally {
                    br.close();
                }



                String[] cartasGeneradasJ1 = generarCartasAleatorias1(16);

                // Ingresa las cartas generadas a la pila

                for (int i = 0; i < cartasGeneradasJ1.length; i++) {
                    deckJ1.push(cartasGeneradasJ1[i]);
                }
                //Imprime la pila creada
                System.out.println(deckJ1);

                // PARSE JSON TO STRING
                JsonNode node = Json.parse(jsonp);

                //Ingreso de las cartas a la mano
                String cartaJ1 = deckJ1.peek();
                listaCircular.agregarAlFinal(cartaJ1);
                deckJ1.pop();
                System.out.println("La primera carta es: " + cartaJ1);
                System.out.println(deckJ1);
                cartaJ1 = deckJ1.peek();
                listaCircular.agregarAlFinal(cartaJ1);
                deckJ1.pop();
                System.out.println("La primera carta es: " + cartaJ1);
                System.out.println(deckJ1);
                cartaJ1 = deckJ1.peek();
                listaCircular.agregarAlFinal(cartaJ1);
                deckJ1.pop();
                System.out.println("La primera carta es: " + cartaJ1);
                System.out.println(deckJ1);
                cartaJ1 = deckJ1.peek();
                listaCircular.agregarAlFinal(cartaJ1);
                deckJ1.pop();
                System.out.println("La primera carta es: " + cartaJ1);
                System.out.println(deckJ1);
                listaCircular.listar();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Variables de para la interfaz

            Stage primaryStage = new Stage();
            AnchorPane Contenedor = new AnchorPane();
            TextField input= new TextField();
            input.setMinSize(285,10);
            Mensajes.setPrefHeight(25);
            Mensajes.setId("textarea-messages");
            String a;
            String b;
            String c;
            String d;
            String C2;
            String C3;
            String C4;
            String C1;
            AtomicInteger indiceMano = new AtomicInteger();
            a = listaCircular.getValor(indiceMano.get());
            indiceMano.getAndIncrement();
            b = listaCircular.getValor(indiceMano.get());
            indiceMano.getAndIncrement();
            c = listaCircular.getValor(indiceMano.get());
            indiceMano.getAndIncrement();
            d = listaCircular.getValor(indiceMano.get());
            System.out.println(a);
            String json;

            BufferedReader br = new BufferedReader(new FileReader("cards.json"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                json = sb.toString();
            } finally {
                br.close();
            }
            JsonNode node = Json.parse(json);
            C2 = node.get("Cards").get(b).get("Imagen").asText();
            C1 = node.get("Cards").get(a).get("Imagen").asText();
            C3 = node.get("Cards").get(c).get("Imagen").asText();
            C4 = node.get("Cards").get(d).get("Imagen").asText();
            Image image1 = new Image(getClass().getResourceAsStream("Images/S_Brote.png"));

            Image imageMano2 = new Image(getClass().getResourceAsStream(C2));
            Image imageMano1 = new Image(getClass().getResourceAsStream(C1));
            Image imageMano3 = new Image(getClass().getResourceAsStream(C3));
            Image imageMano4 = new Image(getClass().getResourceAsStream(C4));
            Image imagePila = new Image(getClass().getResourceAsStream("Images/PilaDeCartas.png"));
            Image imageButleft = new Image(getClass().getResourceAsStream("Images/BotonA.png"));
            Image imageButright = new Image(getClass().getResourceAsStream("Images/BotonS.png"));

            IVspecial.setFitHeight(285);
            IVspecial.setFitWidth(650);
            IVspecial.setX(-125);
            IVspecial.setY(5);

            ImageView pilaCartas = new ImageView(imagePila);
            pilaCartas.setFitWidth(130);
            pilaCartas.setFitHeight(120);

            ImageView IVmano = new ImageView(imageMano1);
            IVmano.setFitHeight(185);
            IVmano.setFitWidth(430);
            IVmano.setX(-19);
            IVmano.setY(300);

            ImageView IVanterior = new ImageView(imageButleft);
            IVanterior.setFitHeight(30);
            IVanterior.setFitWidth(60);

            ImageView IVsiguiente = new ImageView(imageButright);
            IVsiguiente.setFitHeight(30);
            IVsiguiente.setFitWidth(60);


            Button butleft = new Button();
            butleft.setGraphic(IVanterior);

            Button butright = new Button();
            butright.setGraphic(IVsiguiente);

            Button invocar = new Button("Invocar");
            invocar.setMinSize(85,37);

            Button pila = new Button("Tomar carta");
            pila.setMinSize(24,37);

            Button historial = new Button("Historial");
            historial.setMinSize(80,37);

            Button salto = new Button("Saltar Turno");
            //salto.setGraphic(imageView);
            salto.setMinSize(24,37);

            HBox hBoxJugar1 = new HBox(128,butleft,butright);

            HBox hBoxJugar2 = new HBox(128,invocar,salto);

            VBox vBoxInfo = new VBox(30,historial,labVida,labMana);

            VBox vBoxPila = new VBox(15,pila,pilaCartas);

            Contenedor.getChildren().addAll(hBoxJugar1,hBoxJugar2,vBoxInfo,vBoxPila,input,IVspecial,IVmano,Mensajes);

            AnchorPane.setLeftAnchor(Mensajes,42d);
            AnchorPane.setTopAnchor(Mensajes,40d);

            AnchorPane.setLeftAnchor(hBoxJugar1,53d);
            AnchorPane.setBottomAnchor(hBoxJugar1,110d);

            AnchorPane.setLeftAnchor(hBoxJugar2,42d);
            AnchorPane.setBottomAnchor(hBoxJugar2,40d);

            AnchorPane.setRightAnchor(vBoxPila,15d);
            AnchorPane.setBottomAnchor(vBoxPila,15d);

            AnchorPane.setLeftAnchor(input,48d);
            AnchorPane.setBottomAnchor(input,205d);

            AnchorPane.setTopAnchor(vBoxInfo,50d);
            AnchorPane.setRightAnchor(vBoxInfo,70d);

            input.setOnAction(event ->{
                String mensaje = Rol ? "Servidor: " : "Cliente: ";
                mensaje+=input.getText();
                input.clear();

                Mensajes.appendText(mensaje+"\n");
                try {
                    comunicacion.send(mensaje);

                } catch (Exception e) {
                    Mensajes.appendText("Fallo del envio"+"\n");
                }

            });

            invocar.setOnAction(event ->{
                try {
                    String invocada= listaCircular.getValor(indiceMano.get());
                    String C5 = node.get("Cards").get(invocada).get("Imagen").asText();
                    String C6 = node.get("Cards").get(invocada).get("Tipo").asText();
                    String C7 = node.get("Cards").get(invocada).get("Nombre").asText();

                    String mensaje = Rol ? "Servidor: " : "Cliente: ";
                    mensaje+=C7;
                    Mensajes.appendText(mensaje+" ha sido invocado(a)"+"\n");
                    try {
                        comunicacion.send(mensaje);

                    } catch (Exception e) {
                        Mensajes.appendText("Fallo del envio"+"\n");
                    }

                    Image imageMano5 = new Image(getClass().getResourceAsStream(C5));
                    IVspecial.setImage(imageMano5);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            });

            butright.setOnAction(event ->{
                int max = listaCircular.getTamanio();
                indiceMano.getAndIncrement();
                if (indiceMano.get()==max){
                    indiceMano.set(0);
                }
                System.out.println(indiceMano);
                String e = null;
                try {
                    e = listaCircular.getValor(indiceMano.get());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                C5 = node.get("Cards").get(e).get("Imagen").asText();
                Image ne = new Image(C5);
                IVmano.setImage(ne);


            });
            butleft.setOnAction(event ->{

                int max = listaCircular.getTamanio();
                indiceMano.getAndDecrement();
                if (indiceMano.get()<0){
                    indiceMano.set(max-1);
                }
                System.out.println(indiceMano);
                String e = null;
                try {
                    e = listaCircular.getValor(indiceMano.get());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                C5 = node.get("Cards").get(e).get("Imagen").asText();
                Image ne = new Image(C5);
                IVmano.setImage(ne);


            });

            pila.setOnAction(event ->{
                String cartaJ1 = deckJ1.peek();
                listaCircular.agregarAlFinal(cartaJ1);
                deckJ1.pop();
            });

            Scene scene = new Scene(Contenedor,550,500);
            primaryStage.setScene(scene);


            return scene;
        }
    }



    @Override
    /**
     * Metodo de inicio
     * Donde se crea el Stages al ejecutar el programa.
     * Al crear el Servidor, una ventana previa pide al usuario ingresar el numero de puerto de la conexion
     * Al crear el Cliente , contiene una ventana previa para ingresar el puerto y la ip
     */
    public void start(Stage Menu) throws Exception {
        Menu.setTitle("Menu");

        AnchorPane Contenedor = new AnchorPane();
        Label LabAnfitrion = new Label("Crear partida");
        Button ButAnfitrion = new Button("Anfitrion");
        Label LabInvitado = new Label("Unirse a partida existente");
        Button ButInvitado = new Button("Buscar");

        VBox MenuVBox = new VBox(20,LabAnfitrion,ButAnfitrion,LabInvitado,ButInvitado);
        Contenedor.getChildren().add(MenuVBox);
        AnchorPane.setTopAnchor(MenuVBox,100d);
        AnchorPane.setLeftAnchor(MenuVBox,100d);
        EventHandler<ActionEvent> evento = new EventHandler<ActionEvent>() {

            /**
             * Metodo para pedirle al servidor un puerto para iniciar la partida y presenta la ventana del puerto
             * @param actionEvent es utilizado para inicializar el evento cuando se crea el Anfitrión
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                Rol = true;

                Stage MainStage = new Stage();
                MainStage.setTitle("Indique el puerto");
                MainStage.setWidth(200);
                MainStage.setHeight(118);
                VBox root2 = new VBox();
                Label label1 = new Label("Ingrese el numero de puerto");
                Button continuar = new Button("Continuar");
                TextField introPort = new TextField();
                root2.getChildren().addAll(label1,introPort,continuar);
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    /**
                     * Evento donde se verifica que el puerto funcione
                     * @param e utilizada para ejecutar el evento
                     */
                    public void handle(ActionEvent e)
                    {
                        try {
                            String text=introPort.getText();
                            int number = Integer.parseInt(text);
                            try {
                                String p=introPort.getText();
                                int p2 = Integer.parseInt(p);
                                if (p2>65536){
                                    int pf = p2/0;

                                }else{

                                    label1.setText("Puerto conectado: "+introPort.getText());
                                    comunicacion = createServer(p2);
                                    try {
                                        comunicacion.Iniciar_C();
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }

                                    Stage stage2 = new Stage();
                                    stage2.setScene((ContenidoScene()));
                                    stage2.setTitle("Servidor");
                                    stage2.show();
                                }
                            } catch (ArithmeticException ex){
                                label1.setText("El puerto debe ser menor a 65536 y no debe estar ocupado");


                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        catch(NumberFormatException ex){
                            label1.setText("El puerto debe tener valores numericos");

                        }
                    }
                };
                continuar.setOnAction(event);
                Scene scene2 = new Scene(root2);

                MainStage.setScene(scene2);
                MainStage.show();
            }

        };
        ButAnfitrion.setOnAction(evento);

        EventHandler<ActionEvent> evento2 = new EventHandler<ActionEvent>() {
            /**
             * Metódo para efectuar el evento del Cliente y crear la ventana con el puerto y la direccion ip
             * @param actionEvent Utilizada para ejecutar el evento correspondiente
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                Rol = false;

                Stage MainStage = new Stage();
                MainStage.setTitle("Dirección IP del Servidor");
                MainStage.setWidth(300);
                MainStage.setHeight(118);
                VBox root2 = new VBox();
                Label label1 = new Label("Ingrese una dirección ip válida");
                Button continuar = new Button("Continuar");
                TextField introIP = new TextField();
                Label label2 = new Label("Ingrese una puerto válido");
                TextField introPort = new TextField();
                root2.getChildren().addAll(label1,introIP,label2,introPort,continuar);
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                    /**
                     * Metódo donde se verifica la ip y el puerto
                     * @param e Utilizada para efectuar el evento
                     */
                    public void handle(ActionEvent e)
                    {

                        try {
                            String ip=introIP.getText();
                            int puerto = Integer.parseInt(introPort.getText());
                            String[] parts = ip.split("[.]");
                            String part1 = parts[0];
                            String part2 = parts[1];
                            String part3 = parts[2];
                            String part4 = parts[3];
                            int p1 = Integer.parseInt(part1);
                            int p2 = Integer.parseInt(part2);
                            int p3 = Integer.parseInt(part3);
                            int p4 = Integer.parseInt(part4);
                            int min=-1;
                            int max=256;
                            if (p1<max&&p1>min && p2<max&&p2>min && p3<max&&p3>min && p4<max&&p4>min){
                                comunicacion = createClient(ip,puerto);
                                try {
                                    comunicacion.Iniciar_C();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                Stage stage2 = new Stage();
                                stage2.setScene((ContenidoScene()));

                                stage2.setTitle("Cliente");
                                stage2.show();
                            }else{
                                int pf = p2/0;

                            }
                        } catch (ArithmeticException ex){
                            label1.setText("Direccion ip invalida");


                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                };
                continuar.setOnAction(event);
                Scene scene2 = new Scene(root2);
                MainStage.setScene(scene2);
                MainStage.show();
            }


        };
        ButInvitado.setOnAction(evento2);
        Scene scene3 = new Scene(Contenedor,300,400);
        Menu.setScene(scene3);
        Menu.show();
    }

    /**
     *Cerrar el hilo(comunicacion)
     */
    public void stop() throws Exception{
        comunicacion.Cerrar_C();
    }



    /**
     * Metodo para crear el servidor y se especifican los diferentes eventos de ataques que se obtienen de la carta
     * El contenido del mensaje se agrega en el espacio de mensajeria
     * @return utiliza este numero de puerto para realizar la comunicacion y los eventos ataques
     */
    private Servidor createServer(int p2){

        labVida.setText("VIDA: "+jugador.getVida());
        labMana.setText("MANA: "+jugador.getMana());
        return new Servidor(p2, data->{
            Platform.runLater(() ->{

                switch(data.toString())
                {
                    //Monstruos
                    case "Cliente: "+Gandling:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(140);
                        break;
                    case "Cliente: "+Gigante:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(200);
                        break;
                    case "Cliente: "+Gran:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(170);
                        break;
                    case "Cliente: "+Mal:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(510);
                        break;
                    case "Cliente: "+Micro:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(70);
                        break;
                    case "Cliente: "+Pescador:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(100);
                        break;
                    case "Cliente: "+Plastabot:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(50);
                        System.out.println(jugador.getMana());
                        break;
                    case "Cliente: "+Reina:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(150);
                        break;
                    case "Cliente: "+Rey:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(300);
                        break;
                    case "Cliente: "+Sacerdotisa:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(110);
                        break;

                    default:
                        System.out.println("holi");
                }
                //IVspecial.setImage(imagePrueba);
                Mensajes.appendText(data.toString()+"\n");



            });
        });
    }

    /**
     * Metodo para crear el cliente y se especifican los diferentes eventos de ataques que se obtienen de la carta
     * El contenido del evento se agrega en el espacio de texto
     * @return utiliza este numero de puerto y la direccion ip para realizar la comunicacion y los eventos ataques
     */
    private Cliente createClient(String ip,int puerto){
        labVida.setText("VIDA: "+jugador.getVida());
        labMana.setText("MANA: "+jugador.getMana());
        return new Cliente(ip, puerto, data->{
            Platform.runLater(() ->{

                //IVspecial.setImage(imagePrueba);

                switch(data.toString())
                {
                    //Monstruos
                    case "Servidor: "+Gandling:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(140);
                        break;
                    case "Servidor: "+Gigante:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(200);
                        break;
                    case "Servidor: "+Gran:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(170);
                        break;
                    case "Servidor: "+Mal:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(510);
                        break;
                    case "Servidor: "+Micro:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(70);
                        break;
                    case "Servidor: "+Pescador:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(100);
                        break;
                    case "Servidor: "+Plastabot:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(50);
                        System.out.println(jugador.getMana());
                        break;
                    case "Servidor: "+Reina:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(150);
                        break;
                    case "Servidor: "+Rey:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(300);
                        break;
                    case "Servidor: "+Sacerdotisa:
                        Mensajes.appendText(data.toString()+" invocado"+"\n");
                        jugador.recibirAtaque(110);
                        break;

                    default:
                        System.out.println("holi");
                }
                if (data.equals("Servidor: sss")){
                    System.out.println("YIIII");
                    jugador.recibirAtaque(200);
                    jugador.gastarMana(100);
                    jugador.regenMana();


                }

                labVida.setText("VIDA: "+jugador.getVida());
                labMana.setText("MANA: "+jugador.getMana());




            });
        });
    }

    /**
     * Inicializa la ventana principal
     * @param args utilizada para inicializar la interfaz
     */
    public static void main(String[]args){
        launch(args);

    }
}