package Stats_Func;
public class mean {
    public static double main(double[][] ar, int rowMin, int rowMax, int col){

        double total = 0;

        for(int i=rowMin; i<=rowMax; i++){
            total=total+ar[col][i];
        }

        return total/(rowMax-rowMin);
    }
    /*
    double m;
        double[][] ar=new double [5][5];
        ar[0][0]=2;
        ar[0][1]=3;
        ar[0][2]=4;
        ar[0][3]=5;
        m= mean.main(ar,0,2,0);
        System.out.println(m);
    */
}
