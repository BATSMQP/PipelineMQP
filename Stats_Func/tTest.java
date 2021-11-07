package Stats_Func;

import org.apache.commons.math3.distribution.TDistribution;

public class tTest {

    //TODO: make array able to have nondoubles but ignore them
    //I'm trying to make it so that we use the minimum amount of imported code so the user can look at the code direcly if they want to. Thinks like table downloads are fine, but most math should be editable and clear. This is as of now a design consideration I am trying to keep to.
    public static void main(String[] args) {
        //This is basicaly a test, but I couldn't find the easy test method.
        double m[];
        double b;
        double[][] ar=new double [4][5];
        ar[0][0]=2;
        ar[1][0]=3;
        ar[2][0]=4;
        ar[3][0]=5;
        /*
        ar[0][1]=5;
        ar[1][1]=6;
        ar[2][1]=7;
        ar[3][1]=3; 
        */
        ar[0][1]=2;
        ar[1][1]=3;
        ar[2][1]=4;
        ar[3][1]=4;
        

        /* m= paired(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        m= pairedAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);  */   //It works! MWAHAHAHAHA!


        /* m= sameSize(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= sameSizeAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b); */  
        
        
        m= unequal(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= unequalAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);
    }

    public static double[] paired(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        //Paired t test for comparing datapoints that are accociated. First element in array is t-value, second is degrees of freedom, and third is p val
        
        double[] resultar=new double[3];
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
        resultar[2]=tDist(-(Math.abs(resultar[0])),resultar[1])*2;

        return resultar;
    }

    public static double[] pairedAll(double[][] ar, int col1, int col2){
        //all version
        double[] result;
        result=paired(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double[] sameSize(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        //t-test for two groups with similar variances or equal populations.

        double[] resultar=new double[3];
        double result=0;
        double m1=mean.Array(ar,rowMin1,rowMax1,col1);
        double m2=mean.Array(ar,rowMin2,rowMax2,col2);
        double v1=dev.var(ar, rowMin1, rowMax1, col1);
        double v2=dev.var(ar, rowMin2, rowMax2, col2);
        double n1=rowMax1-rowMin1+1;
        double n2=rowMax2-rowMin2+1;
        double free=n1+n2-2;

        result=(m1-m2)/((((n1-1)*Math.pow(v1,2)+(n2-1)*Math.pow(v2,2))/free)*Math.sqrt(1/n1+1/n2));
        if (!(result>0||result<0)){
            result=0;
        } 

        resultar[0]=result;
        resultar[1]=free;
        resultar[2]=tDist(-(Math.abs(resultar[0])),resultar[1])*2;

        return resultar;
    }

    public static double[] sameSizeAll(double[][] ar, int col1, int col2){
        //all version
        double[] result;
        result=sameSize(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double[] unequal(double[][] ar,int rowMin1, int rowMax1, int col1, int rowMin2, int rowMax2, int col2){
        //t-test for two groups with different populations and very differnet variances.

        double[] resultar=new double[3];
        double result=0;
        double m1=mean.Array(ar,rowMin1,rowMax1,col1);
        double m2=mean.Array(ar,rowMin2,rowMax2,col2);
        double v1=dev.var(ar, rowMin1, rowMax1, col1);
        double v2=dev.var(ar, rowMin2, rowMax2, col2);
        double n1=rowMax1-rowMin1+1;
        double n2=rowMax2-rowMin2+1;
        double vv1=Math.pow(v1, 2)/n1;
        double vv2=Math.pow(v2, 2)/n2;

        double free=Math.pow(vv1+vv2,2)/ (Math.pow(vv1, 2)/(n1-1) + Math.pow(vv2, 2)/(n2-1));

        result=(m1-m2)/Math.sqrt(v1/n1+v2/n2);
        if (!(result>0||result<0)){
            result=0;
        } 

        resultar[0]=result;
        resultar[1]=free;
        resultar[2]=tDist(-(Math.abs(resultar[0])),resultar[1])*2;

        return resultar;
    }

    public static double[] unequalAll(double[][] ar, int col1, int col2){
        //all version
        double[] result;
        result=unequal(ar, 0, ar.length-1, col1, 0, ar.length-1, col2);
        return result;
    }

    public static double tDist(double t, double n){
        //takes in t test ingredients,outputs t table result, to be calculated into p test
        double result=1.01;

        TDistribution r = new TDistribution(n);
        result=r.cumulativeProbability(t);

        return result;
    }

}
