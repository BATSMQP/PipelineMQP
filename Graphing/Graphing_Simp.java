package Graphing;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

//https://www.codespeedy.com/plot-graph-in-java/

//TODO:Make the scalling extend to X
//TODO: Less urgent make Y able to be negitive scaling wise

public class Graphing_Simp extends JPanel{
    public static double [][] coordinates=new double[100][2];
    int mar=50;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(mar,mar,mar,height-mar));
        g1.draw(new Line2D.Double(mar,height-mar,width-mar,height-mar));
        double x=(double)(width-2*mar)/(coordinates.length-1);
        double scale=(double)(height-2*mar)/getMax();
        g1.setPaint(Color.BLUE);

        for(int i=0;i<coordinates.length;i++){
            double x1=mar+coordinates[i][0]*x;
            double y1=height-mar-scale*coordinates[i][1];
            g1.fill(new Ellipse2D.Double(x1-2,y1-2,4,4));
        }
        
        
        
    }
    private double getMax(){
        double max=-Integer.MAX_VALUE;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i][1]>max)
                max=coordinates[i][1];
           
        }return max;
    }       
        
    public static void main(String args[]){
        JFrame frame =new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Graphing_Simp.coordinates[0][0]= 3;
    Graphing_Simp.coordinates[0][1]= 4;
    Graphing_Simp.coordinates[1][0]= 6;
    Graphing_Simp.coordinates[1][1]= 4;
    Graphing_Simp.coordinates[2][0]= 6;
    Graphing_Simp.coordinates[2][1]= 2;
    frame.add(new Graphing_Simp());
    frame.setSize(400,400);
    frame.setLocation(200,200);
    frame.setVisible(true);
    }

}
