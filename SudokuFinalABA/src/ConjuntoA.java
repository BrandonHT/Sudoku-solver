

import java.util.Iterator;

/**
 * <pre>
 *Se define la clase Conjunto A que implementará la interfaz ConjuntoADT.
 * La clase contará con 3 atributos:
 * -Un arreglo de tipo T que almacenará los datos del conjunto.
 * -Un entero que indicará la cantidad de elementos en el conjunto.
 * -Un entero privado que fijará la cantidad máxima de elementos a ingresar con opción a ser incrementado con un método propio.
 * Cada clase ConjuntoA tendrá la posibilidad de regresar una respuesta booleana indicando si se encuentra con elementos o no.
 * Puede regresar la cantidad de elementos con los que cuenta en el conjunto.
 * Tiene la posibilidad de verificar si un dato ingresado por el usuario está contenido en el conjunto.
 * La clase puede agregar o quitar elementos al conjunto antes indicados por el usuario.
 * Cada ConjuntoA puede generar un iterador para recorrer cada elemento del conjunto con ayuda de la clase IteradorArreglo.
 * Puede realizar las operaciones básicas de conjuntos como son unión, intersección y diferencia de conjuntos.
 * Contará con funcionalidad mínima requerida como un toString que regresará los datos del conjunto en forma de cadena y se podrá comparar con otro ConjuntoA para saber si son iguales o no.
 * </pre>
 * @author Velasco Gallardo Amanda, Reyes Pérez Ana Silvia, Hernández Troncoso Brandon Francisco
 * @version 1.3
 * 
 */
public class ConjuntoA <T> implements ConjuntoADT <T> {
    //Se declaran los atributos necesarios.
    private T [] conjunto;
    private int cardinalidad;
    private final int MAX=50;
    
    /**
     * Constructor por omisión.
     * Instanciará el arreglo de tipo T e iniciará la cardinalidad en 0.
     */
    public ConjuntoA(){
        conjunto=(T[])new Object[MAX];
        cardinalidad=0;
    }
    
    /**
     * Constructor con parametro para definir el máximo de objetos a agregar con opción a expandirse.
     * @param max: int 
     */
    public ConjuntoA(int max){
        conjunto=(T[])new Object[max];
        cardinalidad=0;
    }
    
    /**
     * Método booleano que permite saber si el conjunto contiene o no datos.
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si el conjunto no tiene algún elemento almacenado.</li>
     * <li>False si el conjunto contiene al menos un elemento almacenado.</li>
     * </ul>
     * @return boolean(comparación cardinalidad con 0)
     */
    public boolean estaVacio(){
        return cardinalidad==0;  
    }
    
    /**
     * Método que permite regresar la cantidad de elementos(cardinalidad) que tiene el conjunto.
     * @return int(cardinalidad)
     */
    public int getCardinalidad(){
        return cardinalidad;
    }
    
    /**
     * Método booleano que permite saber si un elemento se encuentra o no en el conjunto.
     * @param dato: T
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si el dato recibido se encuentra en el conjunto.</li>
     * <li>False si el dato recibido no se encuentra en el conjunto</li>
     * </ul>
     * @return boolean(resp)
     */
    public boolean contiene(T dato){
        boolean resp=false;
        //Se instancía el iterador de la clase para recorrer todos los elementos del conjunto.
        Iterator <T> it=iterator();
        T aux;
        //Mientas el iterador tenga elementos y no haya sido encontrado el elemento buscado se seguirá recorriendo el arreglo.
        while(it.hasNext() && !resp){
            aux=it.next();
            resp=aux.equals(dato);
        }
        return resp;
    }
    
    /**
     * Método booleano que permite agregar elementos al conjunto si no lo contiene.
     * @param dato: T
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si el elemento fue agregado con éxito</li>
     * <li>False si el elemento no puedo ser agregado</li>
     * </ul>
     * @return boolean(resp)
     */
    public boolean agrega(T dato){
        boolean resp=false;
        //Si el dato no es nulo y el conjunto aún no lo contiene se porcede a ser agregado.
        if(dato != null && !contiene(dato)){
            resp=true;
            if(cardinalidad==conjunto.length-1)
                expande(conjunto.length*2);
            conjunto[cardinalidad]=dato;
            cardinalidad++;
        }
        return resp;
    }
    
    /**
     * Método de tipo T que permite quitar de manera definitiva un elemento recibido del conjunto.
     * @param dato: T
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>T si el objeto fue encontrado y removido con éxito</li>
     * <li>Null si el objeto no fue encontrado</li>
     * </ul>
     * @return T(dato buscado) o Null(si no se encontró el dato).
     */
    public T quita(T dato){
        //Se declara una variable Null para que sea regresada en caso de que no sea encontrado el dato buscado.
        T resul=null;
        //Se declara un contador que servirá de guía para que no sobrepase la cantidad de elementos en el arreglo.
        int i=0;
        //Mientras no sobrepase el contador la cantidad de elementos en el conjunto y no se encuentre el dato en el conjunto se seguirá buscando.
        while(i<cardinalidad && !conjunto[i].equals(dato))
            i++;
        //Si el contador no llegó a la cantidad de elementos en el arreglo, significa que encontró el elemento buscado.
        if(i<cardinalidad){
            resul=conjunto[i];
            cardinalidad --;
            conjunto[i]=conjunto[cardinalidad];
            conjunto[cardinalidad]=null;
        }
        return resul;
    }
    
    /**
     * Método void que permite expandir la capacidad de elementos para agregar del conjunto.
     * @param tam: int
     */
    private void expande(int tam){
        T nuevo []=(T[])new Object [tam];
        for (int i=0; i<cardinalidad; i++)
            nuevo[i]=conjunto[i];
        conjunto=nuevo;
    }
    
    /**
     * Método que permite crear un iterador para el arreglo de elementos T en el conjunto con ayuda de la clase IteradorArreglo.
     * @return Iterator(iterador de la clase)
     */
    public Iterator <T> iterator(){
        return new IteradorArreglo(conjunto, cardinalidad);
    }
    
    /**
     * Método de tipo ConjuntoADT que permite juntar los elementos del conjutno con otro conjunto recibido.
     * La unión consiste en crear un nuevo conjunto que contenga los elementos de un conjunto con los de otro conjunto sin repetirse en el nuevo conjunto.
     * @param otro: ConjuntoADT
     * @return ConjuntoADT(Un nuevo conjunto resultante de la unión del actual con el recibido)
     */
    public ConjuntoADT<T> union(ConjuntoADT<T> otro){
        ConjuntoADT<T> resul=new ConjuntoA();
        Iterator<T>it=this.iterator();
        while(it.hasNext())
            resul.agrega(it.next());
        it=otro.iterator();
        while(it.hasNext())
            resul.agrega(it.next());
        return resul;
    }
    
    /**
     * Método de tipo ConjuntoADT que permite realizar una intersección de los elementos.
     * La intersección consiste en crear un nuevo conjunto que contenga los datos que coinciden en dos conjuntos.
     * @param otro:ConjuntoADT
     * @return ConjuntoADT(Un nuevo conjunto resultante de la intersección del actual con el recibido)
     */
    public ConjuntoADT<T> intersección (ConjuntoADT<T> otro){
        ConjuntoADT<T> resul=new ConjuntoA();
        if(otro!=null){
            T aux;
            Iterator <T> it=this.iterator();
            while(it.hasNext()){
                aux=it.next();
            if(otro.contiene(aux))
            resul.agrega(aux);
            }
       }
    return resul;
    }   
    
    /**
     * Método de tipo ConjuntoADT que permite realizar la diferencia de los elementos.
     * La diferencia consiste en crear un nuevo conjunto que contenga los datos del conjunto inicial eliminando los que coinciden con el conjunto recibido.
     * @param otro: ConjuntoADT
     * @return ConjuntoADT(Un nuevo conjunto resultante de la diferencia del actual con el recibido)
     */
    public ConjuntoADT<T> diferencia (ConjuntoADT <T> otro){
        ConjuntoADT<T> resul=new ConjuntoA();
        if(otro!=null){
            T aux;
            Iterator<T> it=this.iterator();
            while(it.hasNext()){
                aux=it.next();
            if(!otro.contiene(aux))
                resul.agrega(aux);
            }
        }
        return resul;
    }
    
    /**
     * Método de tipo String revursivo que permite regresar en cadena de caracteres la información de los elementos en el conjunto.
     * Con ayuda del método privado se almacena la información y se regresa.
     * @return String(cadena con la información contenida)
     */
    public String toString(){
        Iterator <T> it=this.iterator();
        return toString(it);
    }
    
    /**
     * Método privado recursivo que permite generar la cadena de caracteres de los datos que contiene el conjunto.
     * @param it: Iterator
     * @return String(cadena de caracteres con la información)
     */
    private String toString(Iterator <T> it){
        if(!it.hasNext())
            return "";
        else{
         return it.next()+toString(it);
        }
    }
    
    /**
     * Método booleano que permite determinar si el conjunto es igual a un objeto recibido.
     * @param otro: Object
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si el conjunto es igual al conjunto recibido.</li>
     * <li>False si el conjunto no es igual al objeto recibido.</li>
     * </ul>
     * @return boolean(resp)
     */
    public boolean equals(Object otro){
        boolean resp=false;
        if(otro!=null && otro instanceof ConjuntoA){
            ConjuntoA<T> c=(ConjuntoA)otro;
            if(cardinalidad==c.getCardinalidad())
                resp=equals(0, c);
        }
        return resp;
    }
    
    private boolean equals(int i, ConjuntoA<T> c){
        if(i==cardinalidad)
            return true;
        else
            if(c.contiene(conjunto[i]))
                return equals(i+1, c);
        else
                return false;
    }
    
    
    
}
