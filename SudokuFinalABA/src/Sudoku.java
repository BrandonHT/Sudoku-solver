/**
 *<pre>
 * Se define la clase Sudoku que contrendrá 3 atributos.
 * Sus atributos son:
 * -Una matriz de enteros donde se almacenarán los números.
 * -Un arreglo de tipo Conjunto que representará las 9 filas.
 * -Un arreglo de tipo Conjunto que representará las 9 columnas.
 * -Un arreglo de tipo Conjunto que representará los 9 recuadros de trabajo.
 * Cada clase Sudoku podrá resolverse por si sola.
 * Además cada Sudoku podrá identificar si existe algún error dentro de su estructura interna.
 * También podrá identificar la posición del recuadro donde se encuentra.
 * </pre>
 * @author Velasco Gallardo Amanda, Reyes Pérez Ana Silvia, Hernández Troncoso Brandon Francisco
 * @version 3.1
 */
public class Sudoku {
    //Se declaran los atributos necesarios.
    private int [][] sudoku;
    private int [][] aux;
    private ConjuntoA<Integer> []rows;
    private ConjuntoA<Integer> []columns;
    private ConjuntoA<Integer> []box;
    
    
    /**
     * Constructor por omisión privado.
     * Instanciará los conjuntos referentes a las filas, columnas y recuadros.
     * También instanciará la matriz interna que hará referencia al sudoku.
     * No se permitirá que el usuario mantenga relación con este constructor por motivos de seguridad.
     */
    private Sudoku(){
        //Se instancían los arreglos para las filas, columnas y recuadros con tamaño de 9.
        rows=new ConjuntoA[9];
        columns=new ConjuntoA[9];
        box=new ConjuntoA[9];
        for(int i=0; i<9; i++){
            rows[i]=new ConjuntoA();
            columns[i]=new ConjuntoA();
            box[i]=new ConjuntoA();
        }
        //Se instancía la matriz con el tamaño de 9 por 9.
        sudoku=new int[9][9];
    }
    
    /**
     * Constructor con un parametro
     * @param sudoku: int[][]
     * Se espera recibir una matriz para que se mande llamar al constructor por omisión y se realice una copia de la matriz recibida a la matriz del sudoku.
     */
    public Sudoku(int sudoku[][]){
        //Se manda llamar al constructor por omisión.
        this();
        //Se realiza la copia de de la matriz recibida con la matriz interna del Sudoku.
        for(int i=0; i<9; i++){
            for(int j=0;j<9;j++)
                this.sudoku[i][j]=sudoku[i][j];
        }
        
        aux=sudoku;
    }
    
    /**
     * Método que permite la búsqueda de algún error de número repetido o que sean mayores a 9
     * Se espera una respuesta de tipo: 
     * <ul>
     * <li>True si no existe algún error en la matriz.</li>
     * <li>False si existe algún número repetido en fila, columna o recuadro; o en caso de que sea un número fuera del intervalo [1,9].</li>
     * </ul>
     * En caso de ser true, agrega todos los números de la matriz a los respectivos conjuntos.
     * @return boolean(resp)
     */
    public boolean searchAnyError(){
        //Se declara una constante booleana como true.
        boolean resp=true;
        //Se declara un contador i inicializado en 0 que será el número de fila en el que se encuentra el buscador de errores.
        int i=0;
        //Se declara un contador j que representará el número de columna en el que se encuentra el buscador de errores.
        int j;
        while(i<9 && resp){
            //Se inicializa el contador j en 0 con la intención de que cada que de una vuelta el while anterior, j siempre valga 0 en la primer vuelta.
            j=0;
            //En caso de que el contador sea igual a 9 o la constante booleana sea falsa, tremina su ejecución.
            while(j<9 && resp){
                //Se utiliza un if que evalúa 4 condiciones para que proceda con su ejecución:
                //1)Que el número ingresado en una posición [i][j] sea menor a 0.
                //2)Que el número ingresado en la posición [i][j] no se encuentre en la fila[i]
                //3)Que el número ingresado en la posición [i][j] no se encuentre en la columna[j]
                //4)Que el número ingresado en la posición [i][j] no se encuentre en el recuadro[pisitionBox(i,j)]
                //En caso de que no se cumplan estas condiciones, puede cumplir con la siguiente condición para que se ejecuten las intrucciones del if:
                //**Que el número en la posición [i][j] sea igual a 0.
                if((aux[i][j]!=0 && sudoku[i][j]>0 && sudoku[i][j] <10 && (!rows[i].contiene(sudoku[i][j]) && !columns[j].contiene(sudoku[i][j]) && !box[positionBox(i,j)].contiene(sudoku[i][j]))) || sudoku[i][j]==0){
                    rows[i].agrega(sudoku[i][j]);
                    columns[j].agrega(sudoku[i][j]);
                    box[positionBox(i,j)].agrega(sudoku[i][j]);
                } 
                //Se regresa falso pues la lógica del programa dice que si encuentra una coincidencia en fila, columna o recuadro, el sudoku ya no es resolvible correctamente.
                else
                    resp=false;
                //Se aumenta el contador de las columnas +1 para seguir con el proceso.
                j++;
            }
            //Se aumenta el contador de las filas +1 para seguir con el proceso.
            i++;
        }
        //Se regresa la respuesta. Regresa true si es correcto el Sudoku y regresa false si hay un número repetido.
        return resp;
    }
    
    /**
     * Método que permite mandar llamar al método recursivo para resolver el Sudoku.
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>int[][] si el sudoku fue resuelto con éxito.</li>
     * <li>Null si el sudoku no pudo ser resuelto por el método recursivo.</li>
     * </ul>
     * @return int[][](sudoku) or null
     */
    public int[][] solve(){
        //Se realiza una condición para que en caso de ser true, se mande como respuesta el Sudoku resuelto.
        if(solve(0,0,false))
            return sudoku;
        //Caso contrario se manda un null.
        else
            return null;
    }
    
    /**
     * Método privado recursivo que permite resolver el sudoku de acuerdo a los números ingresados por el usuario.
     * @param row: int
     * @param column: int
     * @param aux: boolean
     * Se espera una respuesta de tipo:
     * <ul>
     * <li>True si el Sudoku pudo ser rellenado de manera correcta.</li>
     * <li>False si el sudoku ha fallado en el intento de resolver.</li>
     * </ul>
     * @return boolean(resp)
     */
    private boolean solve(int row, int column, boolean aux){
        //Se declara una constante num inicializada en 1 que representará el número a querer agregar.
        int num=1;
        //En caso de que no se cumpla la condición para saber que el sudoku terminó de su intento de resolver, se ejecuta el bloque de instrucciones.
        if(!solved(row,column)){
            //Si la casilla del sudoku contiene algún numero ya implementado que sea diferente de 0 se ejecuta el bloque de instrucciones.
            if(sudoku[row][column]!=0){
                //Si el indicador de columna es menor a 8, se regres el método recursivo con el mismo indicador de fila, el indicador de columna más 1 y la respuesta booleana.
                if(column<8)
                    return solve(row,column+1, aux);
                //En caso de que se encuentre en la poisición 8 de columnas, se regresa el método recursivo emprezando en la siguiente fila, la columna 0 y la respuesta booleana.
                else
                    return solve(row+1,0, aux);
            }
            //Se ejecuta este bloque se instrucciones en caso de que el número que contenga la matriz en posición [fila][columna] sea igual a 0.
            else{
                //Se prueba cada número del 1 al 9 en el sudoku para saber si puede ser agregado a la solución.
                while(num<10 && !aux){
                    //En caso de que no se encuentre el número en la columna, en la fila y en el recuadro, lo agrega a la solución y a los respectivos conjuntos.
                    if(!rows[row].contiene(num) && !columns[column].contiene(num) && !box[positionBox(row,column)].contiene(num)){
                        sudoku[row][column]=num;
                        rows[row].agrega(num);
                        columns[column].agrega(num);
                        box[positionBox(row,column)].agrega(num);
                        //Si la columna en la que se encuentra es menor a la columna 8, se regresa el método recursivo con una el indicador de fila, el indicador de columna más 1 y la respuesta booleana.
                        if(column<8)
                            aux=solve(row,column+1,aux);
                        //En caso de que se encuentre en la columna número 8, regresa el método recursivo con la siguiente fila, la columna iniciada en 0 y la respuesta booleana.
                        else
                            aux=solve(row+1,0,aux);
                        //En caso de que el número no haya podido ser agregado por coincidir, se quita de todos los conjuntos y de la matriz del sudoku.
                        //Una vez eliminado el número, se intenta con el siguiente para saber si ése número corresponde al indicado.f
                        if(!aux){
                            rows[row].quita(num);
                            columns[column].quita(num);
                            box[positionBox(row,column)].quita(num);
                            sudoku[row][column]=0;
                        }
                    }
                    //Se incrementa el número para probar con otro diferente y ver si el número corresponde a la solución.
                    num++;
                }
                //Se regresa la respuesta booleana.
                return aux;
            }
        }
        //En caso de ser rellenado hasta el último, regresa una respuesta de true.
        else
            return true;
    }
    
    /**
     * Método que permite saber si el Sudoku ya fue completado exitosamente hasta la última casilla.
     * @param row:int
     * @param column: int
     * Se espera una respuesta de tipo: 
     * <ul>
     * <li>True si el método recursivo de resolver llegó al final.</li>
     * <li>False si el método recursivo no ha recorrido todas las casillas.</li>
     * </ul>
     * @return boolean(comparación)
     */
    private boolean solved(int row, int column){
        //Se realiza la comparación. En caso de que el indicador de fila se encuentre en 9 y la columna en 0, significa que el programa ya recorrió hasta el final la matriz.
        return row==9 && column==0;
    }
    
    /**
     * Método que permite obtener la posición del recuadro en el que se encuentra el programa a la hora de la ejecución de sus métodos correspondientes.
     * @param row: int
     * @param column: int
     * @return int(número del recuadro donde se encuentran los métodos en la ejecución de sus acciones).
     */
    private int positionBox(int row, int column){
        //Se declara una variable entera que almacenará el número de recuadro dependiendo la condición cumplida.
        int res;
        //Si la columna es menor a 3.
        if(column<3)
            //Si la fila es menor a 3.
            if(row<3)
                res=0;
            //Si la fila es menor a 6.
            else if(row<6)
                res=3;
            //Si la fila es menor a 9.
            else
                res=6;
        
        //Si la columna es menor a 6.
        else if(column<6)
            //Si la fila es menor a 3.
            if(row<3)
                res=1;
            //Si la fila es menor a 6.
            else if(row<6)
                res=4;
            //Si la fila es menor a 9.
            else
                res=7;
        
        //Si la columna es menor a 9.
        else
            //Si la fila es menor a 3.
            if(row<3)
                res=2;
            //Si la fila es menor a 6.
            else if(row<6)
                res=5;
            //Si la fila es menor a 9.
            else
                res=8;
        //Regresa la respuesta con el índice del recuadro.
        return res;
    }
}
