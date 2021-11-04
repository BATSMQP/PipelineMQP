package Stats_Func;

import org.apache.commons.math3.distribution.TDistribution;

public class tTest {

    //TODO: make array able to have nondoubles but ignore them
    //I'm trying to make it so that we use the minimum amount of imported code so the user can look at the code direcly if they want to. Thinks like table downloads are fine, but most math should be editable and clear. This is as of now a design consideration I am trying to keep to.
    public static void main(String[] args) {
        //This is basicaly a test, but I couldn't find the easy test method.
         double m[];
        double[][] ar=new double [4][5];
        ar[0][0]=2;
        ar[1][0]=3;
        ar[2][0]=4;
        ar[3][0]=5;
        
        ar[0][1]=5;
        ar[1][1]=6;
        ar[2][1]=7;
        ar[3][1]=3; 
        /*
        ar[0][1]=2;
        ar[1][1]=3;
        ar[2][1]=4;
        ar[3][1]=4;*/

        m= paired(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        m= pairedAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        double b = tDist(-(Math.abs(m[0])),m[1])*2;
        System.out.printf("The p value is %f",b);    //It works! MWAHAHAHAHA!
    }

    public static double[] paired(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        //Paired t test for comparing datapoints that are accociated. First element in array is t-value, second is degrees of freedom
        
        double[] resultar=new double[2];
        double result=0;
        double m1=mean.Array(ar,rowMin1,rowMax1,col1);
        double m2=mean.Array(ar,rowMin2,rowMax2,col2);
        int n=rowMax1-rowMin1+1;

        double[][] difAr= new double[n][1];

        for(int i=0; i<n;i++){
            difAr[i][0]=Math.abs(ar[rowMin1+i][col1]-ar[rowMin2+i][col2]); 
        }

        double SD=dev.SDAll(difAr, 0);

        result=(m1-m2)/(SD/Math.sqrt(n));
        if (!(result>0||result<0)){
            result=0;
        }

        resultar[0]=result;
        resultar[1]=n-1;

        return resultar;
    }

    public static double[] pairedAll(double[][] ar, int col1, int col2){
        double[] result;
        result=paired(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double variance(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;
        
        return result;
    }

    public static double vairanceAll(double[][] ar, int col1, int col2){
        double result=0;
        result=variance(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double unequal(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;
        
        return result;
    }

    public static double unequalAll(double[][] ar, int col1, int col2){
        double result=0;
        result=unequal(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double tDist(double t, double n){
        double result=1.01;

        TDistribution r = new TDistribution(n);
        result=r.cumulativeProbability(t);

        return result;
    }

}
