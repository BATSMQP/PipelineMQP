import java.io.File;

import UpperLevel.RunAlgo;



public class test {
    public static void main(String[] args) {
        String name="test_d";
        File file = new File("./src/main/java/",name+".csv" );
        
        RunAlgo.run(file,"lowpass");

        //File f=RunAlgo.run(file,"lowpass");
        //System.out.println(f.getName());
    }
}
