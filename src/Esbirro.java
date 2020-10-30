public class Esbirro extends Carta{
    private int ataque;

    public Esbirro(String imagen, int costo,String accion,int ataque) {
        super(imagen, costo, accion);
        this.ataque=ataque;
    }

}
