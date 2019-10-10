/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.util.ArrayList;

/**
 *
 * @author 1001001222
 */
public class Sector {
    public ArrayList<Punto> puntos=new ArrayList<Punto>();
    int x;
    int y;
    double posibilidad;
    boolean listo=false;
    
    public Sector(int pX, int pY, int pPosibilidad){
        x=pX;
        y=pY;
        posibilidad=pPosibilidad;
    }
    
    public void anadirPunto(int x, int y, int R, int G, int B){
        puntos.add(new Punto(x,y,R,G,B));
    }
}
