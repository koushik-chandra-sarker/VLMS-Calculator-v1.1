/*
package VLSM;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class SubnetCalculator  {
    String ip_in;
    int subnetMask;
    private int  needSize;
    private int  new_mask;
    private String  new_ip;
    private int  allocatedSize;

    private String  First_valid_Host;
    private String  Last_valid_Host;
    private String  BroadcastAddress;
    public  ArrayList<String> AvailableSubnet = new ArrayList<String>();
    public  ArrayList<Integer> SubnetMaskList = new ArrayList<Integer>();


    public SubnetCalculator(){


    }

    public void calculator(int SubnetMask,String ip_in, int host){
        this.ip_in = ip_in;
        this.needSize = host;
        this.subnetMask = SubnetMask;
        Conversion c = new Conversion();
        c.IpToBinaryConversion(ip_in);
        String initialBinary = c.getOutBinary();

        binaryGenerators b= new binaryGenerators(subnetMask,initialBinary,needSize);
        b.Generator();

        //Set Subnet to Database
        this.AvailableSubnet.addAll(b.subnetlist);
        //get subnet for required host
        String A = this.AvailableSubnet.get(this.AvailableSubnet.size()-1);
        c.binaryToIpConversion(A);
        this.new_ip = c.getOutIP();
        this.AvailableSubnet.remove(this.AvailableSubnet.size()-1);



        //Set Subnet mask to database
        this.SubnetMaskList.addAll(b.subnetMasklist);
        this.new_mask =this.SubnetMaskList.get(this.SubnetMaskList.size()-1);
        this.SubnetMaskList.remove(this.SubnetMaskList.size()-1);
        this.allocatedSize = b.allocatedSize;

        HostCalculator h = new HostCalculator(new_ip,new_mask);
        this.First_valid_Host= h.firstAssignableHost;
        this.Last_valid_Host = h.lastAssignableHost;
        this.BroadcastAddress = h.BroadCast;

        */
/*System.out.println("subnet = "+new_ip);
        System.out.println("Subnet mask = "+new_mask);
        System.out.println("Required Size = "+host);
        System.out.println("Allocated Size = "+allocatedSize);
        System.out.println("First_valid_Host = "+First_valid_Host);
        System.out.println("Last_valid_Host = "+Last_valid_Host);
        System.out.println("BroadcastAddress = "+BroadcastAddress);*//*

        System.out.println(this.AvailableSubnet.size());
        makeFile();
    }

    public void makeFile(){

        try {
            FileWriter fw = new FileWriter("totalSubnet.txt");
            FileWriter fw2 = new FileWriter("totalSubnetMask.txt");

            for (String st :this.AvailableSubnet) {
                fw.write(st+ System.lineSeparator());
            }
            for (int st2 :this.SubnetMask) {
                fw2.write(st2+ System.lineSeparator());
            }
            //fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNew_ip() {
        return new_ip;
    }

    public void setNew_ip(String new_ip) {
        this.new_ip = new_ip;
    }

    public String getFirst_valid_Host() {
        return First_valid_Host;
    }

    public void setFirst_valid_Host(String first_valid_Host) {
        First_valid_Host = first_valid_Host;
    }

    public String getLast_valid_Host() {
        return Last_valid_Host;
    }

    public void setLast_valid_Host(String last_valid_Host) {
        Last_valid_Host = last_valid_Host;
    }

    public String getBroadcastAddress() {
        return BroadcastAddress;
    }

    public void setBroadcastAddress(String broadcastAddress) {
        BroadcastAddress = broadcastAddress;
    }

    public int getNew_mask() {
        return new_mask;
    }

    public void setNew_mask(int new_mask) {
        this.new_mask = new_mask;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public void setAllocatedSize(int allocatedSize) {
        this.allocatedSize = allocatedSize;
    }

    public int getNeedSize() {
        return needSize;
    }

    public void setNeedSize(int needSize) {
        this.needSize = needSize;
    }




}
*/
