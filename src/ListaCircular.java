public class ListaCircular {
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    private Nodo inicio;
    // Puntero que indica el final de la lista o el ultimo nodo.
    private Nodo ultimo;
    // Variable para registrar el tamaño de la lista.
    private int tamanio;

    /**
     * Constructor por defecto.
     */
    public void Lista() {
        inicio = null;
        ultimo = null;
        tamanio = 0;
    }

    /**
     * Consulta si la lista esta vacia.
     *
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
    public boolean esVacia() {
        return inicio == null;
    }

    /**
     * Consulta cuantos elementos (nodos) tiene la lista.
     *
     * @return numero entero entre [0,n] donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Agrega un nuevo nodo al final de la lista circular.
     *
     * @param valor a agregar.
     */
    public void agregarAlFinal(int valor) {
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // De igual forma el ultimo nodo sera el nuevo.
            ultimo = nuevo;
            // Y el puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
            // Caso contrario el nodo se agrega al final de la lista.
        } else {
            // Apuntamos con el ultimo nodo de la lista al nuevo.
            ultimo.setSiguiente(nuevo);
            // Apuntamos con el nuevo nodo al inicio de la lista.
            nuevo.setSiguiente(inicio);
            // Como ahora como el nuevo nodo es el ultimo se actualiza
            // la variable ultimo.
            ultimo = nuevo;
        }
        // Incrementa el contador de tamaño de la lista
        tamanio++;
    }
}