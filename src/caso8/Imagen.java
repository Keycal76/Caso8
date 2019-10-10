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
    public ArrayList<Punto> puntos=new ArrayList<Punto>();
    
    public Imagen(String name){
        File file = new File(name);
        System.out.println(file.canRead());
        try {
            img=ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Caso8.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public void analizarSector(int x, int y) {
        int R=0;
        int G=0;
        int B=0;
        
        int r=0,r2=0;
        for(int i=0;i<img.getHeight()/10*x-1;i+=r){
            for(int j=0;j<img.getWidth()/10*y-1;j+=r2){
                try{
                    Color color = new Color(img.getRGB(i,j));
                    R=color.getRed();
                    G=color.getGreen();
                    B=color.getBlue();
                    if(R<255 && G<255 && B<255){
                        puntos.add(new Punto(i,j,R,G,B));
                        //System.out.println("Rojo: "+R+" Verde: "+G+" Azul: "+B); 
                        //System.out.println("IMPRIMIENDO "+i+" "+j+" "+r+" "+r2);
                    }
                }catch(java.lang.ArrayIndexOutOfBoundsException e){
                        
                }catch(java.lang.NullPointerException e){
                    
                }
                r2=(int) (Math.random()*30)+5;
            }
        r=(int) (Math.random()*30)+5;
        }
    }
}
