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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Prueba de branch 2
public class Main2 extends Application {
    /**
     * La variable que controla si se ejecuta el Servidor o el Cliente.
     * Se debe ejecutar primero como true y despues como false. De manera que el Servidor se crea primero.
     * Si el cliente es creado antes del Servidor, la conexion falla.
     */
    private boolean Rol;

        /***
     * El cuadro de mensajeria del chat
     */
    private TextArea Mensajes = new TextArea();

    private ImageView imageView = new ImageView();

    private VBox root = new VBox();
    /**
     * La conexion detecta cuando es un Servidor, si no, crea un cliente
     */
    private Network comunicacion;

    /**
     * Parte de la interfaz y activadores del envio de mensajes
     * @return La ventana del chat(root)
     */
    private Parent ContenidoScene(){
        Mensajes.setPrefHeight(500);
        Mensajes.setId("textarea-messages");


        TextField input = new TextField();
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
        VBox root = new VBox(20, imageView,Mensajes,input);
        root.setPrefSize(600,600);
        Label but = new Label("Escriba su mensaje");
        root.getChildren().add(but);
        root.getStylesheets().add("Styles/style1.css");
        return root;
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
        VBox MenuVBox = new VBox(20,imageView,ButAnfitrion,LabInvitado,ButInvitado);
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
                                    stage2.setScene(new Scene(ContenidoScene()));
                                    stage2.setHeight(500);
                                    stage2.setWidth(300);
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
                scene2.getStylesheets().add("Styles/style1.css");
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
                                stage2.setScene(new Scene(ContenidoScene()));
                                stage2.setHeight(500);
                                stage2.setWidth(300);
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
                scene2.getStylesheets().add("Styles/style1.css");
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
        return new Servidor(p2, data->{
            Platform.runLater(() ->{
                Mensajes.appendText(data.toString()+"\n");
                Image image1 = new Image(getClass().getResourceAsStream("Images/E_Sucubo.png"));
                imageView.setImage(image1);
            });
        });
    }

    /**
     * El nuevo cliente
     * El contenido del mensaje se agrega en el espacio de mensajeria
     * @return utiliza este numero de puerto y la direccion ip para realizar la comunicacion
     */
    private Cliente createClient(String ip,int puerto){
        return new Cliente(ip, puerto, data->{
            Platform.runLater(() ->{
                Mensajes.appendText(data.toString()+"\n");
                Image image2 = new Image(getClass().getResourceAsStream("Images/E_Gandling.png"));
                imageView.setImage(image2);
            });
        });
    }
    public static void main(String[]args){
        launch(args);

    }
}