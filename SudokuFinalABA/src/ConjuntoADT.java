
import java.util.Iterator;

/**
 *
 * @author Velasco Gallardo Amanda, Reyes Pérez Ana Silvia, Hernández Troncoso Brandon Francisco.
 */

/**
 * Se define la clase abstracta ConjuntoADT que extenderá la clase Iterable.
 * La clase contendrá los métodos necesarios para que la clase ConjuntoA implemente la interfaz y pueda realizar exitosamente su funcionamiento. 
 * @author Velasco Gallardo Amanda, Reyes Pérez Ana Silvia, Hernández Troncoso Brandon Francisco.
 */
public interface ConjuntoADT <T> extends Iterable <T>{
    public boolean estaVacio();
    public int getCardinalidad();
    public T quita(T dato);
    public boolean agrega(T dato);
    public boolean contiene(T dato);
    public ConjuntoADT<T> union(ConjuntoADT<T> otro);
    public ConjuntoADT<T> intersección(ConjuntoADT<T> otro);
    public ConjuntoADT<T> diferencia (ConjuntoADT<T> otro);
    public Iterator <T> iterator();
    
}
