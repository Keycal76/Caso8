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
    public ArrayList<Imagen> imagenes=new ArrayList<Imagen>();
    int largo;
    int ancho;
    
    public Caso8(Ventana pVista){
        vista=pVista;
        this.vista.jButton1.addActionListener(this);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    public void analizarImagenes() { //"src/caso8/IMG"+Integer.toString(3)+".jpg"
        imagenes.add(new Imagen("src/caso8/IMG"+Integer.toString(3)+".jpg"));
        for(int sectorX=0;sectorX<10;sectorX++){
            for(int sectorY=0;sectorY<10;sectorY++){
                imagenes.get(0).analizarSector(sectorX, sectorY);
            }
        }    
        iniciarVista(3);
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
        
        for(int i=0;i<imagenes.get(0).puntos.size();i++){
            g.drawOval(imagenes.get(0).puntos.get(i).x*500/1024, imagenes.get(0).puntos.get(i).y*500/1024, 2, 2);
            //System.out.println("IMPRIMIENDO "+puntos.get(i).x*800/ancho+" "+puntos.get(i).y*800/largo);
        }
        for(int i=0;i<600;i+=100){
            g.drawLine(i, 0, i, 500); 
            g.drawLine(0, i, 500, i); 
        }
        //vista.labelImagen.setText("ALGO");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paint(this.vista.panelImagen.getGraphics());
        vista.jButton1.setVisible(false);
    }
}