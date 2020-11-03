import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import org.w3c.dom.Text;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane Contenedor = new AnchorPane();

        TextField input = new TextField();
        input.setMinSize(285,10);
        TextArea Mensajes = new TextArea();

        input.setMinSize(285,10);
        Mensajes.setPrefHeight(100);
        Mensajes.setId("textarea-messages");

        Label labVida = new Label("VIDA :");
        Label labMana = new Label("MANA :");

        Image image1 = new Image(getClass().getResourceAsStream("Images/E_Reina.png"));
        Image imageMano = new Image(getClass().getResourceAsStream("Images/E_Gigante.png"));
        Image imagePila = new Image(getClass().getResourceAsStream("Images/PilaDeCartas.png"));
        Image imageButleft = new Image(getClass().getResourceAsStream("Images/BotonA.png"));
        Image imageButright = new Image(getClass().getResourceAsStream("Images/BotonS.png"));

        ImageView imageView = new ImageView(image1);
        imageView.setFitHeight(285);
        imageView.setFitWidth(650);
        imageView.setX(-125);
        imageView.setY(5);

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

        Contenedor.getChildren().addAll(hBoxJugar1,hBoxJugar2,vBoxInfo,vBoxPila,input,imageView,IVmano);

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



        Scene scene = new Scene(Contenedor,550,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

        /*Parent SecondRoot = FXMLLoader.load(getClass().getResource("root2.fxml"));
        primaryStage.setTitle("title");
        AnchorPane Contenedor = new AnchorPane();
        Label LabAnfitrion = new Label("Crear partida");
        Button ButAnfitrion = new Button("Anfitrion");
        Label LabInvitado = new Label("Unirse a partida existente");
        Button ButInvitado = new Button("Buscar");
        VBox MenuVBox = new VBox(20,LabAnfitrion,ButAnfitrion,LabInvitado,ButInvitado);

        Image image1 = new Image(getClass().getResourceAsStream("Images/E_Sucubo.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(400);
        Contenedor.getChildren().addAll(MenuVBox,imageView1);
        AnchorPane.setTopAnchor(MenuVBox,100d);
        AnchorPane.setLeftAnchor(MenuVBox,100d);
        Scene scene = new Scene(Contenedor,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

         */

    public static void main(String[] args) {
        launch(args);
    }
}
