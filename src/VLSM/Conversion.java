package VLSM;

public class Conversion {
    String data_in;
    String ip_in;
    String outIP;
    String outBinary="";

    public Conversion(){}

    public void binaryToIpConversion(String data_in){
        int a = Integer.parseInt(data_in.substring(0,8),2);
        int b = Integer.parseInt(data_in.substring(8,16),2);
        int c = Integer.parseInt(data_in.substring(16,24),2);
        int d = Integer.parseInt(data_in.substring(24,32),2);

        outIP =""+a+"."+b+"."+c+"."+d;
    }

    public String getOutIP(){
        return outIP;
    }

    public String IpToBinaryConversion(String ip_in){
        this.ip_in =ip_in;

        String[] octetArray = ip_in.split("\\.");
        for (String string : octetArray){
            int octet = Integer.parseInt(string);
            String binaryOctet = Integer.toBinaryString(octet);

            while (binaryOctet.length()!=8){
                binaryOctet = "0"+ binaryOctet;
            }

            outBinary = String.format("%s%s", outBinary, binaryOctet);


        }
        return outBinary;
    }
    public String getOutBinary(){
        return outBinary;
    }



}
