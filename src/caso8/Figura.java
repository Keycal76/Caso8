/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.awt.Color;
/**
 *
 * @author 1001001222
 */
class Figura {
    Punto punto1;
    Punto punto2;
    Punto punto3;
    Color color;
    int[] Cromosoma=new int[3];
    
    public Figura(int C1, int C2, int C3){
        Cromosoma[0]=C1;
        Cromosoma[1]=C2;
        Cromosoma[2]=C3;
        System.out.println(Cromosoma[0]);
        System.out.println(Cromosoma[1]);
        System.out.println(Cromosoma[2]);
    }
    
    public void setDatos(Punto p1, Punto p2, Punto p3, Color pColor){
        punto1=p1;
        punto2=p2;
        punto3=p3;
        color=pColor;
    }
}
