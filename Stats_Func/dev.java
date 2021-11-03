package Stats_Func;

public class dev {
    
    //TODO: make array able to have nondoubles but ignore them

    public static void main(String[] args) {
        //This is basicaly a test, but I couldn't find the easy test method.
        
        double m;
        double[][] ar=new double [4][5];
        ar[0][0]=2;
        ar[1][0]=3;
        ar[2][0]=4;
        ar[3][0]=5;

        m= Var(ar,0,2,0);
        System.out.println(m);

        m= AllVar(ar,0);
        System.out.println(m);

        m= SD(ar,0,2,0);
        System.out.println(m);

        m= AllSD(ar,0);
        System.out.println(m);
    }

    public static double Var(double[][] ar, int rowMin, int rowMax, int col){
        //takes variance of a group of cells

        double result=0;
        double m=mean.Array(ar,rowMin,rowMax,col);

        for(int i=rowMin; i<=rowMax; i++){
            result=result+Math.pow(ar[i][col]-m,2);
        }

        return result/(rowMax-rowMin+1);
    }

    public static double AllVar(double[][] ar,int col){
        //this simplifyes the other method, just taking the full length of the col
        double total;
        total=Var(ar,0,ar.length-1,col);
        return total;

    }

    public static double SD(double[][] ar, int rowMin, int rowMax, int col){
        //takes the square root of the variance
        double result=0;
        result=Math.sqrt(Var(ar,rowMin,rowMax,col));
        return result;
    }

    public static double AllSD(double[][] ar,int col){
        //this simplifyes the other method, just taking the full length of the col
        double total;
        total=SD(ar,0,ar.length-1,col);
        return total;

    }

}
