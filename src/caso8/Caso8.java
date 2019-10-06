/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Caso8 {

    Ventana vista;
    public Caso8(Ventana pVista){
        vista=pVista;
    }
    
    public void analizarImagenes() {
        int R=0;
        int G=0;
        int B=0;
        File file = new File("src/caso8/IMG"+Integer.toString(1)+".jpg");
        System.out.println(file.canRead());
        BufferedImage img;
        try {
            img=ImageIO.read(file);
            /*System.out.println("Alto: "+img.getHeight());
            System.out.println("Ancho: "+img.getWidth());
            for(int i=0;i<img.getHeight()-1;i++){
                for(int j=0;j<img.getWidth()-1;j++){
                    try{
                        Color color = new Color(img.getRGB(i,j));
                        R = color.getRed();
                        G = color.getGreen();
                        B = color.getBlue();
                        System.out.println("Rojo: "+R+" Verde: "+G+" Azul: "+B);
                    }catch(java.lang.ArrayIndexOutOfBoundsException e){
                        
                    }catch(java.lang.NullPointerException e){
                    
                    }
                }
            }*/
            iniciarVista(1);
        } catch (IOException ex) {
            Logger.getLogger(Caso8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void iniciarVista(int x){
        ImageIcon imagen = new ImageIcon("src/caso8/IMG"+Integer.toString(x)+".jpg");
        vista.labelImagen.setIcon(imagen);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(vista.labelImagen.getWidth(), vista.labelImagen.getHeight(), Image.SCALE_DEFAULT));
        vista.labelImagen.setIcon(icono);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
