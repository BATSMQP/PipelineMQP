package Stats_Func;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import Gen_Algo.ReadFile;
import Graphing.Graphing_Simp;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        double m[];
        double b;
        double[][] ar=ReadFile.fromCSVtoD2("Data\\Test.csv", 1, 2);
        double[][] t1=ReadFile.fromCSVtoD2("Data\\Test.csv", 0, 1);
        double[][] t2=ReadFile.fromCSVtoD2("Data\\Test.csv", 0, 2);
        //JFrame j1 = new JFrame();
        Graphing_Simp.printThisD2(t1);
        //TimeUnit.SECONDS.sleep(10);
       // JFrame j2 = new JFrame();
        Graphing_Simp.printThisD2(t2);

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
