package Graphing;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

//https://www.codespeedy.com/plot-graph-in-java/

//Make the scalling extend to X. Done!
//TODO: Less urgent make Y able to be negitive scaling wise

public class Graphing_Simp extends JPanel{
    public static double [][] coordinates=new double[100][2];
    static int mar=50;
    boolean notplaced=true;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(mar,mar,mar,height-mar));
        g1.draw(new Line2D.Double(mar,height-mar,width-mar,height-mar));
        //double x=(double)(width-2*mar)/(coordinates.length-1);
        double scaleY = (double)(height-2*mar)/getMax(1);
        double scaleX = (double)(width-2*mar)/getMax(0);
        g1.setPaint(Color.BLUE);
        

        for(int i=0;i<coordinates.length;i++){
            double x1=mar+coordinates[i][0]*scaleX;
            double y1=height-mar-scaleY*coordinates[i][1]; 
            g1.fill(new Ellipse2D.Double(x1-2,y1-2,3,3));
        }
        
        String s =""+getMax(0);

        JLabel jlabel = new JLabel(s);
        jlabel.setFont(new Font("Verdana",1,10));
        //jlabel.setLocation(width-mar, height-mar+10);
        jlabel.setLocation(0,0);
        
        if(notplaced){
            this.add(jlabel); 
            notplaced = false;
        }
        
    }

    public double getMax(int dim){
        double max=-Integer.MAX_VALUE;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i][dim]>max)
                max=coordinates[i][dim];
           
        }return max;
    }       

    public static void printThisD2(double[][] d , JFrame j ){
        //JFrame j =new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graphing_Simp.coordinates=d;
        j.add(new Graphing_Simp());
        j.setSize(400,400);
        j.setLocation(200,200);
        j.setVisible(true);
        j.setTitle(j.getName());

        
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

}
