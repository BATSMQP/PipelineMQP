package Gen_Algo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class CutOff {
    static public List<Double[]> bellow(double[][] a,int colD,int colT,double cut){
        List<Double[]> d = new ArrayList<Double[]>();
        for(int i=0; i<a.length;i++){
            if(cut<=a[i][colD]){
                Double[] t = new Double[2];
                t[0]=a[i][colT];
                t[1]=a[i][colD];
                d.add(t);
            }
        }
        return d;
    }

    public static void main(String[] args) {
        double[][] t1=ReadFile.fromCSVtoD2("Data\\Test.csv", 0, 1);
        List<Double[]> d =bellow(t1,1,0,4);
        int s =d.size();
        for(int i=0;i < s;i++){
            Double[] t = d.get(i);
            System.out.println(ArrayUtils.toString(t));
        }
        
    }

}
