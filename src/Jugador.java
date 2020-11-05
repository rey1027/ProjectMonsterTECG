public class Jugador {

    public int vida;
    public int mana;

    public Jugador(){
        this.vida = 1000;
        this.mana = 200;
    }

    public void recibirAtaque(int ataque){
        this.vida -= ataque;
    }

    public void regenMana(){
        this.mana+=(this.mana*0.25);
    }

    public void gastarMana(int costo) { this.mana -= costo; }

    public String getVida() {
        String vidaString = Integer.toString(vida);
        return vidaString;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getMana() {
        String manaString = Integer.toString(mana);
        return manaString;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
