package Gen_Algo;

import java.util.ArrayList;
import java.util.List;

public class CutOff {
    public List<Double[]> bellow(double[][] a,int colD,int colT,double cut){
        List<Double[]> d = new ArrayList<Double[]>();

        for(int i=0; i<a.length;i++){
            if(cut<=a[i][colD]){
                Double[] t = new Double[2];
                t[0]=a[i][colT];
                t[2]=a[i][colD];
                d.add(t);
            }
        }

        return d;
    }

    public static void main(String[] args) {
        
    }

}
