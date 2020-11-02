//Se importan las clases necesarias para ejecutar las instrucciones deseadas.
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *Se define la clase IteradorArreglo que permitirá simular un iterador para recorrer un arreglo de una clase.
 * La clase contiene 3 atributos que son:
 * -Un arreglo de tipo T que almacenará los elementos
 * -Un entero que tendrá el total de elementos contenidos en el arreglo del iterador.
 * -Un entero que será el número de la posición actual donde se encuentra el iterador.
 * @author Velasco Gallardo Amanda, Reyes Pérez Ana Silvia, Hernández Troncoso Brandon Francisco.
 */
public class IteradorArreglo <T> implements Iterator <T>{
    private T [] coleccion;
    private int total;
    private int actual;
    
    /**
     * Constructor con dos parámetros
     * @param arre:T[]
     * @param tam: int
     * Se espera recibir un arreglo y el tamaño del arreglo.
     */
    public IteradorArreglo(T arre[], int tam){
        //Se asigna el arreglo al iterador instanciado, el tamaño del arreglo y se define como posicón actual el primer elemento(0).
        coleccion=arre;
        total=tam;
        actual=0;
    }
    
    /**
     * Método booleano que permite saber si el iterador aún cuenta con elementos en su arreglo.
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si tiene al menos un elemento en el arreglo por recorrer.</li>
     * <li>False si ya no existen elementos por recorrer en el arreglo</li>
     * </ul>
     * @return boolean(comparación posición actual con el total de elementos)
     */
    public boolean hasNext(){
        return actual< total;
    }
    
    /**
     * Método de tipo T que regresa el siguiente elemento disponible en el arreglo si es que aún no se han recorrido todos en la colección.
     * @return T(resul)
     */
    public T next(){
        if(!hasNext())
            throw new NoSuchElementException("-");
        else{
            T resul=coleccion[actual];
            actual++;
            return resul;
        }
    }
    
    /**
     * Método que permite eliminar elementos de la colección.
     * Método pendiente. 7u7
     */
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}
