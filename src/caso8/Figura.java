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
    
    public Figura(Punto p1, Punto p2, Punto p3, Color pColor){
        punto1=p1;
        punto2=p2;
        punto3=p3;
        color=pColor;
        //Cromosoma
    }
}
