/**
 * Clase que se encarga de las cartas
 */
public class Carta {

    private int costo;
    private String imagen;
    private String accion;

    /**
     * Constructor de la clase
     * @param imagen nombre de la imagen a utilizar
     * @param costo  mana que utiliza la carta
     * @param accion accion que realiza esa carta
     */
    public Carta(String imagen,int costo,String accion){
        this.imagen=imagen;
        this.costo=costo;
        this.accion=accion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
