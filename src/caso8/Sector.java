/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.util.ArrayList;
import java.awt.Color;

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
    ArrayList<Color> colores=new ArrayList<Color>();
    
    //1.GRAY
    //2.LIGHT_GRAY
    //3.DARK_GRAY
    //4.RED
    //5.PINK
    //6.ORANGE
    //7.YELLOW
    //8.GREEN
    //9.MAGENTA
    //10.CYAN
    //11.BLUE
    //12.Azul claro
    
    public Sector(int pX, int pY, int pPosibilidad){
        x=pX;
        y=pY;
        posibilidad=pPosibilidad;
        colores.add(Color.GRAY);
        colores.add(Color.LIGHT_GRAY);
        colores.add(Color.DARK_GRAY);
        colores.add(Color.RED);
        colores.add(Color.PINK);
        colores.add(Color.ORANGE);
        colores.add(Color.YELLOW);
        colores.add(Color.GREEN);
        colores.add(Color.MAGENTA);
        colores.add(Color.CYAN);
        colores.add(Color.BLUE);
        colores.add(new Color(60,100,160));//Azul claro
        colores.add(new Color(100,70,50));//Cafe
        colores.add(new Color(120,60,40));//Cafe claro
        colores.add(new Color(230,220,115));//Amarillo piel
    }
    
    public void anadirPunto(int x, int y, int R, int G, int B){
        puntos.add(new Punto(x,y,clasificarColor(R,G,B)));
    }
    
    public Color clasificarColor(int R, int G, int B){
        Color color=Color.BLACK;
        int distancia=(int)(Math.pow(R-color.getRed(),2)+Math.pow(G-color.getGreen(),2)+Math.pow(B-color.getBlue(),2));
        distancia=(int)Math.pow(distancia,0.5);
        //System.out.println("Distancia 1: "+distancia);
        for(int i=0;i<colores.size();i++){
            int distanciaAux=(int)(Math.pow(colores.get(i).getRed()-R,2)+Math.pow(colores.get(i).getGreen()-G,2)+Math.pow(colores.get(i).getBlue()-B,2));
            distanciaAux=(int)Math.pow(distanciaAux,0.5);
            if(distanciaAux<distancia){
                distancia=distanciaAux;
                color=colores.get(i);
            }
        }
        //System.out.println(color);
        return color;
    }
}
