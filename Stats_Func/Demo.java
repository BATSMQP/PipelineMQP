package Stats_Func;

import Gen_Algo.ReadFile;

public class Demo {
    public static void main(String[] args) {
        double m[];
        double b;
        double[][] ar=ReadFile.fromCSVtoD2("Data\\Test.csv", 0, 1);

        /* m= tTest.paired(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        m= tTest.pairedAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);  */   


        /* m= tTest.sameSize(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= tTest.sameSizeAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b); */  
        
        m= tTest.unequal(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= tTest.unequalAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);
    }
}
