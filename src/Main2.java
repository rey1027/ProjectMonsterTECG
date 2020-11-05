
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
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


//Prueba de branch 2
public class Main2 extends Application {
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
    final String Gran = "Gigante maligno descomunal";
    final String Mal = "Mal Ganis";
    final String Micro = "Micromomia";
    final String Pescador = "Pescador Fantasmal";
    final String Plastabot = "Plastabot";
    final String Reina= "Reina de dolor";
    final String Rey = "El Rey Exanime";
    final String Sacerdotisa = "Sacerdotisa de furia";


    /**
     * La variable que controla la ejecucion del Servidor o el Cliente.
     *
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
    private Scene ContenidoScene(){
        Stage primaryStage = new Stage();
        AnchorPane Contenedor = new AnchorPane();
        TextField input= new TextField();
        input.setMinSize(285,10);
        Mensajes.setPrefHeight(25);
        Mensajes.setId("textarea-messages");

        Image image1 = new Image(getClass().getResourceAsStream("Images/E_Reina.png"));
        Image imageMano = new Image(getClass().getResourceAsStream("Images/E_Gigante.png"));
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

        ImageView IVmano = new ImageView(imageMano);
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
            /**
             * Se mantiene en constante comunicacion cada vez que se envia un mensaje, si falla, un mensaje de error sera enviado
             */
            try {
                comunicacion.send(mensaje);                  //   ===== Excepcion 1 : Si el mensaje no encuentra destinatario el envío falla =====

            } catch (Exception e) {
                Mensajes.appendText("Fallo del envio"+"\n");
            }

        });
        Scene scene = new Scene(Contenedor,550,500);
        primaryStage.setScene(scene);
        

        return scene;
    }
    /**
     * Iniciador de la concexion
     */

    @Override
    /**
     * Las ventanas principales(Stages), las cuales se crean al ejecutar el programa.
     * Al crear el Servidor, una ventana previa pide al usuario ingresar el numero de puerto de la conexion
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
                     *Evento que toma el puerto escrito en la ventana activada por el Servidor
                     */
                    public void handle(ActionEvent e)
                    {
                        try {                          //   ===== Excepcion 5 : Si al iniciar el server se ingresa un valor con carácteres no numéricos como puerto =====
                            String text=introPort.getText();
                            int number = Integer.parseInt(text);
                            try {                                   //   ===== Excepcion 3 : Si al escribir el puerto se recibe un valor mayor al máximo de puertos de red(65536) =====
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
                     *Evento que toma el puerto escrito en la ventana activada por el Servidor
                     */
                    public void handle(ActionEvent e)
                    {

                        try {                               //   ===== Excepcion 4 : Si el cliente ingresa una ip en la que alguno de los numeros sea mayor a 255 o menor a 1 =====
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
     * El nuevo servidor
     * El contenido del mensaje se agrega en el espacio de mensajeria
     * @return utiliza este numero de puerto para realizar la comunicacion
     */
    private Servidor createServer(int p2){

        labVida.setText("VIDA: "+jugador.getVida());
        labMana.setText("MANA: "+jugador.getMana());
        return new Servidor(p2, data->{
            Platform.runLater(() ->{
                //IVspecial.setImage(imagePrueba);
                Mensajes.appendText(data.toString()+"\n");



            });
        });
    }

    /**
     * El nuevo cliente
     * El contenido del mensaje se agrega en el espacio de mensajeria
     * @return utiliza este numero de puerto y la direccion ip para realizar la comunicacion
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
    public static void main(String[]args){

        Stack deckJ1 = new Stack();
        Stack deckJ2 = new Stack();

        try{
            String json;

            BufferedReader br = new BufferedReader(new FileReader("cards.json"));
            try{
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while(line != null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                json = sb.toString();
            } finally {
                br.close();
            }


            // PARSE JSON TO STRING
            String[] cartasGeneradasJ1 = generarCartasAleatorias1(16);
            String[] cartasGeneradasJ2 = generarCartasAleatorias1(16);

            for (int i = 0; i < cartasGeneradasJ1.length; i++) {
                deckJ1.push(cartasGeneradasJ1[i]);

            }
            System.out.println(deckJ1);

            for (int i = 0; i < cartasGeneradasJ2.length; i++) {
                deckJ2.push(cartasGeneradasJ2[i]);

               try{
                    JsonNode node = Json.parse(json);
                    String j2 = cartasGeneradasJ2[i];
                    String C2 = node.get("Cards").get(j2).get("Mana").asText();
                    //System.out.println(C2);
                }catch (Exception e){
                    e.printStackTrace();
                   System.out.println("No Funciona JSon");
                }

            }
            System.out.println(deckJ2);

            String cartaJ1 = deckJ1.peek();
            deckJ1.pop();
            System.out.println("La primera carta es: " + cartaJ1);
            System.out.println(deckJ1);
            cartaJ1 = deckJ1.peek();
            deckJ1.pop();
            System.out.println("La primera carta es: " + cartaJ1);
            System.out.println(deckJ1);
            cartaJ1 = deckJ1.peek();
            deckJ1.pop();
            System.out.println("La primera carta es: " + cartaJ1);
            System.out.println(deckJ1);
            cartaJ1 = deckJ1.peek();
            deckJ1.pop();
            System.out.println("La primera carta es: " + cartaJ1);
            System.out.println(deckJ1);


        }
        catch (Exception e){
            e.printStackTrace();
        }

        launch(args);

    }
}