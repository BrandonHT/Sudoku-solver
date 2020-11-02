/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brandon
 */
public class Main {

    public static String imprimeMatriz(int arre[][], int tam){
        StringBuilder cad = new StringBuilder();
        String matriz;
        matriz=imprimeMatriz(arre,tam,0,0,cad);
        return matriz;
    }
    
    private static String imprimeMatriz(int arre[][], int tam, int i, int j, StringBuilder cad){
        if(i==tam)
            return cad.toString();
        else{
            if(j==tam){
                cad.append("\n");
                return imprimeMatriz(arre,tam,i+1,0,cad);
            }
            else{
                cad.append(arre[i][j]+" ");
                return imprimeMatriz(arre,tam,i,j+1,cad);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int aux[][]=new int[9][9];
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                aux[i][j]=0;
        aux[0][0]=9;
        aux[0][1]=1;
        aux[0][2]=8;
        aux[0][3]=2;
        aux[0][4]=7;
        aux[0][5]=3;
        aux[0][6]=6;
        aux[0][7]=4;
        aux[1][1]=5;
        
        Sudoku nuevo= new Sudoku(aux);
        System.out.println(nuevo.searchAnyError());
        aux=nuevo.solve();
        System.out.println(imprimeMatriz(aux, 9));
    }
    
}
