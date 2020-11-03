public class Carta {

    private int costo;
    private String imagen;
    private String accion;

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
