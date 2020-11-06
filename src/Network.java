import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class Network {


    /**
     * Creacion del hilo
     */
    private Red_Hilo Conexion_Hilo = new Red_Hilo();
    /**
     * Función llamada cuando se recibe un mensaje
     */
    private Consumer<Serializable>onRecieveCallBack;

    /**
     * Se establece el constructor que tomará la función, permitiendo el envío de datos por la red.
     * @param onRecieveCallBack cuando se recibe un mensaje
     */
    public Network(Consumer<Serializable>onRecieveCallBack){
        this.onRecieveCallBack = onRecieveCallBack;
        Conexion_Hilo.setDaemon(true);
    }
    public void Iniciar_C() throws Exception{
        Conexion_Hilo.start();
    }
    /**
     * @param data corresponde al mensaje
     */
    public void send(Serializable data) throws Exception{
        Conexion_Hilo.out.writeObject(data);
    }

    /**
     *Cierre de conexion
     */
    public void Cerrar_C() throws Exception{
        Conexion_Hilo.socket.close();

    }

    /**
     *Identificar que es el Servidor o el Cliente
     */
    protected abstract boolean Rol();
    /**
     *Obtener la dirección Ip para la conexión
     */
    protected abstract String ObtenerIp();
    /**
     *Obtener el número de puerto para la conexión
     */
    protected abstract int ObtenerPuerto();
    /**
     * Hilo que permite leer y escribir simultaneamente
     */
    private class Red_Hilo extends Thread{
        private Socket socket;
        private ObjectOutputStream out;
        /**
         * Permite ejecutar el hilo(conexion)
         */
        @Override
        public void run() {

             //Se crea el socket e inicia un nuevo servidor con el puerto indicado
            //En el cliente se crea un nuevo socket con la direccion Ip y el puerto, tratando de realizar la conexion

            try(ServerSocket server = Rol() ? new ServerSocket(ObtenerPuerto()) : null;
                Socket socket = Rol() ? server.accept() : new Socket(ObtenerIp(), ObtenerPuerto());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);

                //Permite llamar a la funcion y transmitir los datos
                while(true){
                    Serializable data = (Serializable) in.readObject();
                    onRecieveCallBack.accept(data);
                }
            }
            catch (Exception e){
                onRecieveCallBack.accept("Conexion cerrada");


            }

        }
    }
}
