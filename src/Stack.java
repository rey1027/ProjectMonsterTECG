/**
 *  * Contiene los diferentes metodos para el Stack
 *  *  * @author RACHEL
 *  *  * @version 1.0
 */
public class Stack {
    int MAX = 16;
    int top ;
    String deck[] = new String[MAX];

    /**
     *
     */
    Stack(){
        this.top = -1;
    }

    /**
     * Método para saber si esta vacío
     * @return forma vacia del stack
     */
    boolean isEmpty(){
        return (top < 0);
    }

    /**
     * Método para agregar archivos al Stack
     * @param carta es el deck de cartas
     * @return Valores booleanos para saber si se agrego o no a la pila
     */
    boolean push(String carta){
        if (top >= (MAX -1)){
            System.out.println("Stack Overflow");
            return false;
        }
        this.deck[++top] = carta;
        return true;
    }

    /**
     * Método para eliminar el ultimo elemento del Stack
     * @return si se encuentra vacia la pila "No hay cartas", pero si hay retonar la pila con los objetos restantes
     */
    String pop(){
        if (this.isEmpty()){
            System.out.println("Stack Underflow");
            return "no hay cartas";
        } else{
            String carta = this.deck[this.top--];
            return carta;
        }
    }

    /**
     * Método para observar el ultimo elemento de la pila sin eleminarlo
     * @return "no hay cartas" cuando esta vacía, de lo contrario se obtiene esa ultima carta ingresada
     */
    String peek() {
        if (this.isEmpty()) {
            System.out.println("Stack Underflow");
            return "no hay cartas";
        } else{
            return this.deck[this.top];
        }
    }

    /**
     * Metodo para imprimir de una forma ordenada el Stack
     * @return Stack con este formato |carta|
     */
    @Override
    public String toString() {
        String stack = "-----\n";
        for ( int i = top; i >=0; i--) {
            stack += "|" + this.deck[i] + "|\n";
        }
        stack += "-----\n";
        return stack;

    }

}

