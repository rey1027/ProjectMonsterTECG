import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Clase cliente, contiene lo necesario para la creacion del
 * @author RACHEL_BRYAN
 * @version 1.0
 */
public class Cliente extends Network {
    private String ip;
    private int NumPuerto;

    /**
     * Constructor que funcióna permitiendo el envío de datos por la red.
     * @param onRecieveCallBack cuando se recibe un mensaje
     * @param ip dirección ip para el servidor
     * @param NumPuerto puerto de comunicación
     */
    public Cliente(String ip, int NumPuerto, Consumer<Serializable> onRecieveCallBack) {
        super(onRecieveCallBack);
        this.ip=ip;
        this.NumPuerto=NumPuerto;
    }
    /**
     * Booleano que determina el rol que cumple
     * @return False indica que es el Cliente
     */
    @Override
    protected boolean Rol() {
        return false;
    }
    /**
     * El cliente necesita saber la direccion ip del servidor
     * @return La direccion ip
     */
    @Override
    protected String ObtenerIp() {
        return ip;
    }
    /**
     * El cliente necesita saber el numero de puerto
     * @return El numero de puerto
     */
    @Override
    protected int ObtenerPuerto() {
        return NumPuerto;
    }
}
