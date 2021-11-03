package Stats_Func;

public class tTest {

    //TODO: make array able to have nondoubles but ignore them

    public static void main(String[] args) {
        
    }

    public static double correlated(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        double result=0;

        return result;
    }

    public static double correlatedAll(double[][] ar, int col1, int col2){
        double result=0;
        result=correlated(ar, 0, ar.length, col1, 0, ar.length, col2);
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
