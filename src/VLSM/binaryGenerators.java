package VLSM;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.*;

public class binaryGenerators {


    int mask;
    int needSubnet;
    String initialBinaryValue;
    String firstBinary;
    String lastBinary;
    int allocatedSize;

    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<String> subnetlist = new ArrayList<String>();
    ArrayList<Integer> subnetMasklist = new ArrayList<Integer>();

    public binaryGenerators(int mask, String initialBinaryValue, int needSubnet) {
        this.mask = mask;
        this.initialBinaryValue = initialBinaryValue;
        this.needSubnet = needSubnet;
    }

    public void Generator(){
        powerCalculator p = new powerCalculator(needSubnet);
        int Power = p.Calculator();
        double Pow = Power;
        int q = (int) Math.pow(2.0, Pow);
        if (q<needSubnet) {
            Power=Power+1;
        }
        allocatedSize = (int) Math.pow(2,Power);
        firstBinary = initialBinaryValue.substring(0,mask);
        lastBinary = initialBinaryValue.substring((initialBinaryValue.length()-1)-(Power),initialBinaryValue.length()-1);
        int n = (31-Power)-(mask-1);
        //System.out.println(Power);

        int bitrow = (int) Math.pow(2,n);
        int bit =bitrow;

        if(bitrow==2){
            bit =1;
        }
        else if(bitrow>2&&bitrow<=4){
            bit=2;
        }else if(bitrow>=5&&bitrow<=8){
            bit=3;
        }else if(bitrow>=9&&bitrow<=16){
            bit=4;
        }
        else if(bitrow>=17&&bitrow<=32){
            bit=5;
        }
        else if(bitrow>=33&&bitrow<=64){
                    bit=6;
        }
        else if(bitrow>=65&&bitrow<=128){
            bit=7;
        }


        if ((mask+Power) == 32){
            subnetlist.add(initialBinaryValue);
            subnetMasklist.add(mask);
        }
        else {

            for (int i = 0; i < bitrow; i++) {

                String binary = Integer.toBinaryString(i);
                while (binary.length() != bit) {
                    binary = "0" + binary;
                }
                binary = firstBinary + binary + lastBinary;
                subnetlist.add(binary);

                int s = binary.length() - lastBinary.length();
                //int netmask = s.length();
                subnetMasklist.add(s);

            }
        }
        /*for(String s: subnetlist ){
            System.out.println(s);
        }*/



    }






}
