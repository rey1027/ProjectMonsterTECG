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

public class Test {

    public static void main(String[] args) throws Exception {


        ListaCircular listaCircular = new ListaCircular();

        System.out.println("<<-- Ejemplo de lista circular simple -->>\n");

        // Agregar al final de la lista circular
        listaCircular.agregarAlFinal("dhhajah");
        listaCircular.agregarAlFinal("222");
        listaCircular.agregarAlFinal("333");
        listaCircular.listar();

    }
}