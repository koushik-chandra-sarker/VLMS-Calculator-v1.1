package VLSM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MakeFile {
    public void makeFile(ArrayList<String> AvailableSubnet,ArrayList<Integer> SubnetMaskList){
        new File("C:\\VLSM Calculator").mkdir();

        try {
            FileWriter fw = new FileWriter("C:\\VLSM Calculator\\totalSubnet.txt");
            FileWriter fw2 = new FileWriter("C:\\VLSM Calculator\\totalSubnetMask.txt");

            for (String st :AvailableSubnet) {
                fw.write(st+ System.lineSeparator());
            }
            fw.close();
            for (int st2 :SubnetMaskList) {
                fw2.write(st2+ System.lineSeparator());
            }
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
