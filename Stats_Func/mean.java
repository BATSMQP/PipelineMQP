package Stats_Func;
public class mean{

    public static void main(String[] args) {
        double m;
        double[][] ar=new double [5][4];
        ar[0][0]=2;
        ar[0][1]=3;
        ar[0][2]=4;
        ar[0][3]=5;

        m= Array(ar,0,2,0);
        System.out.println(m);

        m= All(ar,0);
        System.out.println(m);
    }

    public static double Array(double[][] ar, int rowMin, int rowMax, int col){

        double total = 0;

        for(int i=rowMin; i<=rowMax; i++){
            total=total+ar[col][i];
        }

        return total/(rowMax-rowMin+1);
    }


    public static double All(double[][] ar,int col){

        double total;
        total=Array(ar,0,ar.length-1,col);
        return total;

    }
}
