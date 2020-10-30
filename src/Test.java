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
        TextArea Mensajes = new TextArea();
        Mensajes.setPrefHeight(100);
        Mensajes.setId("textarea-messages");
        Image image1 = new Image(getClass().getResourceAsStream("Images/E_Gandling.png"));
        Image image2 = new Image(getClass().getResourceAsStream("Images/E_Sucubo.png"));
        Image image3 = new Image(getClass().getResourceAsStream("Images/PilasDeCartas.png"));

        ImageView imageView = new ImageView(image1);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        imageView.setX(100);
        imageView.setY(100);

        ImageView pilaCartas = new ImageView(image3);
        ImageView imageView2 = new ImageView(image2);

        Button butleft = new Button("<<<");
        Button butright = new Button(">>>");

        Button invocar = new Button("Invocar");
        Button pila = new Button("Tomar carta");
        Button historial = new Button("Historial");
        Button salto = new Button("Saltar Turno");
        //salto.setGraphic(imageView);

        HBox hBoxJugar1 = new HBox(70,butleft,butright);

        HBox hBoxJugar2 = new HBox(30,invocar,salto);

        VBox vBoxInfo = new VBox(30,historial);

        VBox vBoxPila = new VBox(15,pila);

        Contenedor.getChildren().addAll(hBoxJugar1,hBoxJugar2,vBoxInfo,vBoxPila);

        AnchorPane.setLeftAnchor(hBoxJugar1,25d);
        AnchorPane.setBottomAnchor(hBoxJugar1,25d);
        AnchorPane.setLeftAnchor(hBoxJugar2,35d);
        AnchorPane.setBottomAnchor(hBoxJugar2,10d);

        Scene scene = new Scene(Contenedor,500,400);
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
