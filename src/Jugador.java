/**
 *  * Clase encargada del jugador
 *  * @author RACHEL_BRYAN
 *  * @version 1.0
 */
public class Jugador {

    public int vida;
    public int mana;

    /**
     * Constructor de la clase
     */
    public Jugador(){
        this.vida = 1000;
        this.mana = 200;
    }

    /**
     * Metodo para que el ataque afecte la vida
     * @param ataque valor del ataque que se le resta a la vida
     */
    public void recibirAtaque(int ataque){
        this.vida -= ataque;
    }

    /**
     * Metodo para regenerar la mana del jugador
     */
    public void regenMana(){
        this.mana+=(this.mana*0.25);
    }

    /**
     * Metodo para bajar la mana cuando se invoca una carta
     * @param costo valor que cuesta invocar la carta
     */
    public void gastarMana(int costo) { this.mana -= costo; }

    /**
     * Metodo para actualizar la vida
     * @return el valor de la vida
     */
    public String getVida() {
        String vidaString = Integer.toString(vida);
        return vidaString;
    }

    /**
     * Metodo para obtener la vida
     * @param vida valor de la vida actual
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Metodo para actualizar la mana
     * @return el valor de la mana
     */
    public String getMana() {
        String manaString = Integer.toString(mana);
        return manaString;
    }

    /**
     * Metodo para obtener el valor de mana
     * @param mana valor de la mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }
}
