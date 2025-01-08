// Interface de fila genérica
public interface IGenQ<T> {
    // Insere um item na fila.
    void put(T ch) throws QueueFullException;

    // Retira um item da fila
    T get() throws QueueEmptyException;



}
