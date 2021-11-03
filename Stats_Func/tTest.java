package Stats_Func;

public class tTest {

    //TODO: make array able to have nondoubles but ignore them

    public static void main(String[] args) {
        
    }

    public static double paired(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;
        double m1=mean.Array(ar,rowMin1,rowMax1,col1);
        double m2=mean.Array(ar,rowMin2,rowMax2,col2);
        int n=rowMax1-rowMin1+1;

        double[] difAr= new double[n];

        for(int i=0; i<n;i++){
            difAr[i]=Math.abs(0); //placeholder should be matched value 1-2
        }

        return result;
    }

    public static double pairedAll(double[][] ar, int col1, int col2){
        double result=0;
        result=paired(ar, 0, ar.length, col1, 0, ar.length, col2);
        return result;
    }

    public static double variance(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;
        
        return result;
    }

    public static double vairanceAll(double[][] ar, int col1, int col2){
        double result=0;
        result=variance(ar, 0, ar.length, col1, 0, ar.length, col2);
        return result;
    }

    public static double unequal(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;
        
        return result;
    }

    public static double unequalAll(double[][] ar, int col1, int col2){
        double result=0;
        result=unequal(ar, 0, ar.length, col1, 0, ar.length, col2);
        return result;
    }
}
