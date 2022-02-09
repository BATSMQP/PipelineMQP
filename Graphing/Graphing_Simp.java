package Graphing;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Container;
//import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//https://www.codespeedy.com/plot-graph-in-java/

//Make the scalling extend to X. Done!
//TODO: Less urgent make Y able to be negitive scaling wise

public class Graphing_Simp extends JPanel{
    //public static final String DEST4 = "C:/Users/Toshiba/Desktop/testjframe.png";
    public static double [][] coordinates=new double[100][2];
    static int mar=50;
    boolean notplaced=true;
    public static String title;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(mar,mar,mar,height-mar));
        g1.draw(new Line2D.Double(mar,height-mar,width-mar,height-mar));
        //double x=(double)(width-2*mar)/(coordinates.length-1);

        double MaxX = getMax(0);
        double MaxY = getMax(1);

        double scaleY = (double)(height-2*mar)/MaxY;
        double scaleX = (double)(width-2*mar)/MaxX;
        g1.setPaint(Color.BLUE);
        

        for(int i=0;i<coordinates.length;i++){
            double x1=mar+coordinates[i][0]*scaleX;
            double y1=height-mar-scaleY*coordinates[i][1]; 
            g1.fill(new Ellipse2D.Double(x1-2,y1-2,3,3));
        }
        
        String sX =""+MaxX;
        String sY =""+MaxY;
        sX=cutDec(sX);
        sY=cutDec(sY);
        


        g1.setFont(new Font("Verdana",1,10));
        g1.setPaint(Color.BLACK);
        //System.out.print(s);
        
        g1.drawString(sX, width-mar, height-mar+10); 

        String s="0";
        g1.drawString(s, mar-10, height-mar+10);

        
        g1.drawString(sY, mar-10, mar-10);
        
        g1.drawString(title,width/2-70,mar-10);
    }

    public String cutDec(String s){
        int startIndex = s.indexOf(".");
        int endIndex = s.length()-1;
        if(startIndex<0||startIndex+2>=endIndex){
            return s;
        }
        String replacement = "";
        String toBeReplaced = s.substring(startIndex + 2, endIndex);
        return s.replace(toBeReplaced, replacement);
    }

    public double getMax(int dim){
        double max=-Integer.MAX_VALUE;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i][dim]>max)
                max=coordinates[i][dim];
           
        }return max;
    }       

    public static File printThisD2(double[][] d, String path){
        JFrame j =new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graphing_Simp.coordinates=d;
        Graphing_Simp.title= path.replace("_lowpassGraph.png", " Lowpassed");
        j.add(new Graphing_Simp());
        j.setSize(400,400);
        j.setLocation(200,200);
        j.setVisible(true);
        j.setTitle(path.replace("_lowpassGraph.png", " Lowpassed"));
        File file=savePic(j,path);
        j.dispose();
        return file;
        
    }
        
    public static void main(String args[]){
        JFrame j =new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graphing_Simp.coordinates[0][0]= 3;
        Graphing_Simp.coordinates[0][1]= 4;
        Graphing_Simp.coordinates[1][0]= 6;
        Graphing_Simp.coordinates[1][1]= 4;
        Graphing_Simp.coordinates[2][0]= 6;
        Graphing_Simp.coordinates[2][1]= 2;
        j.add(new Graphing_Simp());
        j.setSize(400,400);
        j.setLocation(200,200);
        j.setVisible(true);
    }

    public static File savePic(JFrame jf, String path){  
        //Get Window Content Panel  
        Container content=jf.getContentPane();  
        //Create Buffered Picture Object  
        BufferedImage img=new BufferedImage( jf.getWidth(),jf.getHeight(),BufferedImage.TYPE_INT_RGB);  
        //Get Graphic Objects  
        Graphics2D g2d = img.createGraphics();  
        //Output Window Content Panel to Graphic Object  
        content.printAll(g2d);  
        //Save as Picture  
        File file =new File( path);
        try{
            ImageIO.write(img, "jpg", file);
        }catch (IOException e){
            e.printStackTrace();
        }
        //Release graphic objects  
        g2d.dispose(); 
        return file;
    }  
}
