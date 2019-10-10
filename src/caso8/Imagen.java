/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author 1001001222
 */
public class Imagen {
    BufferedImage img;
    public ArrayList<Sector> sectores=new ArrayList<Sector>();
    
    public Imagen(String name){
        File file=new File(name);
        System.out.println(file.canRead());
        try {
            img=ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Caso8.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public void analizarSectores() {
        int R=0;
        int G=0;
        int B=0;
        for(int sectorX=0;sectorX<5;sectorX++){
            for(int sectorY=0;sectorY<5;sectorY++){
                sectores.add(new Sector(sectorX, sectorY,1));
            }
        }
        int r=0,r2=0;
        boolean salir=true;
        int d=0;
        while(salir){
            //System.out.println(analizarSector());
            if(d==25){
                d=0;
                salir=analizarSector();
            }
            System.out.println(d+" "+sectores.get(d).listo+" "+sectores.get(d).posibilidad);
            if(sectores.get(d).listo!=true){
                boolean encontrado=false;
                for(int i=img.getWidth()/5*sectores.get(d).x;i<img.getHeight()/5*(sectores.get(d).x+1);i+=10){//      -1?
                    for(int j=img.getWidth()/5*sectores.get(d).y;j<img.getWidth()/5*(sectores.get(d).y+1);j+=10){//   -1? 
                        try{
                            Color color=new Color(img.getRGB(i,j));
                            R=color.getRed();
                            G=color.getGreen();
                            B=color.getBlue();
                            if(R<255 && G<255 && B<255 && sectores.get(d).listo==false){
                                sectores.get(d).anadirPunto(i, j, R, G, B);
                                encontrado=true;
                                //System.out.println(sectores.get(d).listo+" "+sectores.get(d).posibilidad); 
                                //System.out.println("IMPRIMIENDO "+i+" "+j+" "+r+" "+r2);
                            }
                        }catch(java.lang.ArrayIndexOutOfBoundsException e){

                        }catch(java.lang.NullPointerException e){

                        }
                        //r2=(int) (Math.random()*30)+5;
                    }
                //r=(int) (Math.random()*30)+5;
                }
                if(encontrado){
                    sectores.get(d).listo=true;
                }else{
                    sectores.get(d).posibilidad-=0.2;
                }
            }
            d++;
        }
    }
    
    public boolean analizarSector() {
        boolean resp=false;
        for(int i=0;i<sectores.size();i++){
            if(sectores.get(i).listo==false && sectores.get(i).posibilidad>0.1 && sectores.get(i).posibilidad<1){
                System.out.println("HEREEEE "+i+" "+sectores.get(i).posibilidad);
                resp=true;
            }
        }
        return resp;
    }
}
