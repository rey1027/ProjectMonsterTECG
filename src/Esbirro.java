/**
 * Clase que contiene lo que necesario para los Esbirros
 *
 */
public class Esbirro extends Carta{
    private int ataque;

    /**
     * Constructor de la clase
     * @param imagen imagen de la carta del esbirro ha utilizar
     * @param costo costo de mana que tiene ese esbirro
     * @param accion
     * @param ataque Cantidad de ataque que tiene para su oponente
     */
    public Esbirro(String imagen, int costo,String accion,int ataque) {
        super(imagen, costo, accion);
        this.ataque=ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}
