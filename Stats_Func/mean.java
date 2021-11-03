package Stats_Func;
public class mean{
    
    //TODO: make array able to have nondoubles but ignore them

    public static void main(String[] args) {
        //This is basicaly a test, but I couldn't find the easy test method.
        double m;
        double[][] ar=new double [4][5];
        ar[0][0]=2;
        ar[1][0]=3;
        ar[2][0]=4;
        ar[3][0]=5;

        m= Array(ar,0,2,0);
        System.out.println(m);

        m= All(ar,0);
        System.out.println(m);
    }

    public static double Array(double[][] ar, int rowMin, int rowMax, int col){
        //this takes the array, the min max rows, and the col and gets a mean of that area
        double total = 0;

        for(int i=rowMin; i<=rowMax; i++){
            total=total+ar[i][col];
        }

        return total/(rowMax-rowMin+1);
    }


    public static double All(double[][] ar,int col){
        //this simplifyes the other method, just taking the full length of the col
        double total;
        total=Array(ar,0,ar.length-1,col);
        return total;

    }
}
