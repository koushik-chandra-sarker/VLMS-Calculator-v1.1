package VLSM;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HostCalculator {

    ArrayList<String> allhostList = new ArrayList<String>();
    ArrayList<String> allhostListIp = new ArrayList<String>();
    private String ip_in;
    private int subnetMask;
    String ip_binary;
    String mask_binary = "";
    private String first_binary;
    private String last_binary;
    String firstAssignableHost;
    String lastAssignableHost;
    String NetworkHost;
    String BroadCast;


    public HostCalculator(String ip_in, int subnetMask) {
        this.ip_in = ip_in;
        this.subnetMask = subnetMask;
        Conversion c = new Conversion();

        ip_binary = c.IpToBinaryConversion(ip_in);
        int sm = subnetMask;
        while (sm != 0) {
            mask_binary = mask_binary + "1";
            sm--;
        }
        int ln = mask_binary.length();
        while (ln != 32) {
            mask_binary = mask_binary + "0";
            ln++;
        }
        int totalhost = (int) Math.pow(2, (32 - subnetMask));

        Generator(totalhost);


        for (String s : allhostList) {
            Conversion con = new Conversion();
            con.binaryToIpConversion(s);
            allhostListIp.add(con.getOutIP());
        }
        NetworkHost = allhostListIp.get(0);
        //System.out.println("Network host: " + NetworkHost);
        BroadCast = allhostListIp.get(allhostListIp.size() - 1);
        //System.out.println("BroadCast Address: " + BroadCast);
        firstAssignableHost = allhostListIp.get(1);
        //System.out.println("firstAssignableHost Address: " + firstAssignableHost);
        lastAssignableHost = allhostListIp.get(allhostListIp.size()-2);
        //System.out.println("lastAssignableHost Address: " + lastAssignableHost);

    }


    public void Generator(int host) {
        powerCalculator p = new powerCalculator(host);
        int Power = p.Calculator();
        double Pow = Power;
        int q = (int) Math.pow(2.0, Pow);
        if (q < host) {
            Power = Power + 1;
        }

        first_binary = ip_binary.substring(0, subnetMask);
        //last_binary = ip_binary.substring((ip_binary.length() - 1) - (Power - 1), ip_binary.length());
        int n = 32 - subnetMask;


        int bitrow = (int) Math.pow(2, n);
        int bit = bitrow;

        if (bitrow == 2) {
            bit = 1;
        } else if (bitrow > 2 && bitrow <= 4) {
            bit = 2;
        } else if (bitrow >= 5 && bitrow <= 8) {
            bit = 3;
        } else if (bitrow >= 9 && bitrow <= 16) {
            bit = 4;
        } else if (bitrow >= 17 && bitrow <= 32) {
            bit = 5;
        } else if (bitrow >= 33 && bitrow <= 64) {
            bit = 6;
        } else if (bitrow >= 65 && bitrow <= 128) {
            bit = 7;
        } else if (bitrow >= 129 && bitrow <= 256) {
            bit = 8;
        }


        for (int i = 0; i < bitrow; i++) {

            String binary = Integer.toBinaryString(i);
            while (binary.length() != bit) {
                binary = "0" + binary;
            }
            binary = first_binary + binary;
            allhostList.add(binary);

        }
    }

}

