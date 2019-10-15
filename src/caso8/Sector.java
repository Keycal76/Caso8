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
    double[] coloresHallados=new double[16];
    ArrayList<double[]> puntosXHallados=new ArrayList<double[]>();
    ArrayList<double[]> puntosYHallados=new ArrayList<double[]>();
    ArrayList<Figura> figuras=new ArrayList<Figura>();
    double totalPintado;
    
    int[] targetColor=new int[16];
    ArrayList<int[]> targetX=new ArrayList<int[]>();
    ArrayList<int[]> targetY=new ArrayList<int[]>();
    //0.BLACK
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
    //13.Cafe
    //14.Cafe claro
    //15.Amarillo piel
    
    public Sector(int pX, int pY, int pPosibilidad){
        x=pX;
        y=pY;
        posibilidad=pPosibilidad;
        colores.add(Color.BLACK);
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
        for(int i=0;i<coloresHallados.length;i++){
            coloresHallados[i]=0;
            puntosXHallados.add(new double[205]);
            puntosYHallados.add(new double[205]);
            targetX.add(new int[205]);
            targetY.add(new int[205]);
        }
        for(int i=0;i<puntosXHallados.size();i++){
            for(int j=0;j<puntosXHallados.get(i).length;j++){
                puntosXHallados.get(i)[j]=0;
                puntosYHallados.get(i)[j]=0;
                targetX.get(i)[j]=0;
                targetY.get(i)[j]=0;
            }
        }
    }
    
    public void anadirPunto(int x, int y, int R, int G, int B){
        puntos.add(new Punto(x,y,clasificarColor(R,G,B)));
    }
    
    public Color clasificarColor(int R, int G, int B){
        Color color=Color.BLACK;
        int f=0;
        int distancia=(int)(Math.pow(R-color.getRed(),2)+Math.pow(G-color.getGreen(),2)+Math.pow(B-color.getBlue(),2));
        distancia=(int)Math.pow(distancia,0.5);
        //System.out.println("Distancia 1: "+distancia);
        for(int i=0;i<colores.size();i++){
            int distanciaAux=(int)(Math.pow(colores.get(i).getRed()-R,2)+Math.pow(colores.get(i).getGreen()-G,2)+Math.pow(colores.get(i).getBlue()-B,2));
            distanciaAux=(int)Math.pow(distanciaAux,0.5);
            if(distanciaAux<distancia){
                distancia=distanciaAux;
                f=i;
                color=colores.get(i);
            }
        }
        coloresHallados[f]+=1;
        /*for(int k=0;k<coloresHallados.length;k++){
            System.out.println("HALLADO: "+colores.get(k).toString()+" "+k+"  "+coloresHallados[k]);
        }*/
        return color;
    }
    
    public void sacarInformacionInicial(){
        System.out.println("HEREEEEEEEE2");
        double total=0;
        double totalPuntos=0;
        for(int k=0;k<coloresHallados.length;k++){
            totalPuntos+=coloresHallados[k];
            coloresHallados[k]=(coloresHallados[k]/(204*204))*100;
            //System.out.println("PUNTOS "+k+":"+coloresHallados[k]);
            total+=coloresHallados[k];
        }
        for(int k=0;k<coloresHallados.length;k++){
            coloresHallados[k]=(coloresHallados[k]/total)*100;
            //System.out.println("PUNTOS "+k+":"+coloresHallados[k]);
        }
        /*System.out.println("TOTAL: "+total);
        System.out.println("TOTAL PUNTOS: "+totalPuntos);
        System.out.println("TOTAL PUNTOS 2: "+puntos.size());
        System.out.println("                              ");*/
        for(int k=0;k<puntosXHallados.size();k++){
            int temp=0;
            for(int i=0;i<puntos.size();i++){
                if(puntos.get(i).color.equals(colores.get(k))){
                    try{
                    puntosXHallados.get(k)[puntos.get(i).x-(this.x*204)]+=1;
                    puntosYHallados.get(k)[puntos.get(i).y-(this.y*204)]+=1;
                    temp++;
                    } catch(ArrayIndexOutOfBoundsException e){

                    }
                }
            }
            //System.out.println(temp);
            for(int i=0;i<puntosXHallados.get(k).length;i++){
                if(temp>0){
                    puntosXHallados.get(k)[i]=puntosXHallados.get(k)[i]/temp*100;
                    puntosYHallados.get(k)[i]=puntosYHallados.get(k)[i]/temp*100;
                    //System.out.println("Color: "+k+"   "+i+"  X: "+puntosXHallados.get(k)[i]+" Y: "+puntosYHallados.get(k)[i]);
                }
            }
        }
        crearRepresentacion();
    }
    
    private void crearRepresentacion(){
        int temp=0;
        for(int k=0;k<coloresHallados.length;k++){
            if(k+1==coloresHallados.length){
                targetColor[k]=255;
            }else{
                targetColor[k]=temp+(int)(coloresHallados[k]/100*255);//Guarda la dirección donde empieza el intervalo
            }
            //System.out.println(k+" Desde "+temp+" hasta "+targetColor[k]);
            temp+=(int)(coloresHallados[k]/100*255);
        }
        
        for(int k=0;k<puntosXHallados.size();k++){
            temp=0;
            int temp2=0;
            for(int i=0;i<puntosXHallados.get(k).length;i++){
                if(i+1==puntosXHallados.get(k).length){
                    targetX.get(k)[i]=255;
                    targetY.get(k)[i]=255;
                }else{
                    targetX.get(k)[i]=temp+(int)(puntosXHallados.get(k)[i]/100*255);
                    targetY.get(k)[i]=temp2+(int)(puntosYHallados.get(k)[i]/100*255);
                }
                //System.out.println(k+" X: "+i+" Desde "+temp+" hasta "+targetX.get(k)[i]);
                //System.out.println(k+" Y: "+i+" Desde "+temp2+" hasta "+targetY.get(k)[i]);
                //System.out.println("            ");
                temp+=(int)(puntosXHallados.get(k)[i]/100*255);
                temp2+=(int)(puntosYHallados.get(k)[i]/100*255);
            }
        }
        //puntos.clear();//---------------------------------------------------------------------------------------------------
        crearFigurasIniciales();
    }
    
    public void crearFigurasIniciales(){
        figuras.add(new Figura((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        figuras.add(new Figura((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
    }
    
    public void recibirFigurasIniciales(){
        
    }
    
    public void algoritmoGenetico(){
        while(evaluar()){
            int[] temp=new int[figuras.size()];//Temporal para guardar los que van a cruzarse
            for(int i=0;i<figuras.size();i++){
                
            }
            for(int i=0;i<temp.length/2;i++){
                
            }
        }//RECORDAR TENER TAMAÑO LIMITE PARA QUE LUEGO EN VEZ DE SER 4 HIJOS SEAN 2
    }
    
    private boolean fitness(){
        boolean sirve=false;
        return sirve;
    }
    
    private boolean evaluar(){
        boolean isFinal=false;
        //Margen de error de.... 2%?
        return isFinal;
    }
    
    private void cruce(int x1, int x2){
        //Validar si solo es uno o sí queda impar, para que se divida?
        //RECORDAR TENER TAMAÑO LIMITE PARA QUE LUEGO EN VEZ DE SER 4 HIJOS SEAN 2
    }
    
    private void cruce(int x1){
        //YA NO xd Validar si solo es uno o sí queda impar, para que se divida?
        //RECORDAR TENER TAMAÑO LIMITE PARA QUE LUEGO EN VEZ DE SER 4 HIJOS SEAN 2
    }
}
