/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Caso8 extends Frame implements ActionListener{

    public Ventana vista;
    public ArrayList<Punto> puntos=new ArrayList<Punto>();
    int largo;
    int ancho;
    public Caso8(Ventana pVista){
        vista=pVista;
        this.vista.jButton1.addActionListener(this);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
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
            largo=img.getHeight();
            ancho=img.getWidth();
            System.out.println("Alto: "+img.getHeight());
            System.out.println("Ancho: "+img.getWidth());
            int r=10,r2=10;
            for(int i=0;i<img.getHeight()-1;i+=r){
                for(int j=0;j<img.getWidth()-1;j+=r2){
                    try{
                        Color color = new Color(img.getRGB(i,j));
                        R = color.getRed();
                        G = color.getGreen();
                        B = color.getBlue();
                        if(R<255 && G<255 && B<255){
                            puntos.add(new Punto(i,j,R,G,B));
                            //System.out.println("Rojo: "+R+" Verde: "+G+" Azul: "+B); 
                            //System.out.println("IMPRIMIENDO "+i+" "+j+" "+r+" "+r2);
                        }
                    }catch(java.lang.ArrayIndexOutOfBoundsException e){
                        
                    }catch(java.lang.NullPointerException e){
                    
                    }
                    r2=(int) (Math.random()*90)+10;
                }
            r=(int) (Math.random()*90)+10;
            }
        } catch (IOException ex) {
            Logger.getLogger(Caso8.class.getName()).log(Level.SEVERE, null, ex);
        }
        iniciarVista(1);
    }
    
    public void iniciarVista(int x){
        ImageIcon imagen = new ImageIcon("src/caso8/IMG"+Integer.toString(x)+".jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(vista.labelImagen.getWidth(), vista.labelImagen.getHeight(), Image.SCALE_DEFAULT));
        vista.labelImagen.setIcon(icono);
        
        //for(int i=0;i<100000000;i++){}
        paint(this.vista.panelImagen.getGraphics());
    }
    
    public void paint(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.BLACK);
        for(int i=0;i<900;i+=100){
            g.drawLine(i, 0, i, 800); 
            g.drawLine(0, i, 800, i); 
        }
        for(int i=0;i<puntos.size();i++){
            g.drawOval(puntos.get(i).x*800/ancho, puntos.get(i).y*800/largo, 2, 2);
            //System.out.println("IMPRIMIENDO "+puntos.get(i).x*800/ancho+" "+puntos.get(i).y*800/largo);
        }
        //vista.labelImagen.setText("ALGO");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paint(this.vista.panelImagen.getGraphics());
        vista.jButton1.setVisible(false);
    }
}