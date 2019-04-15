package InputOutput;

import conversions.Conversion;

import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private Scanner scanner;
    private Long mask;
    private String[] ip;


    public Input() {
        this.scanner = new Scanner(System.in);
        insertIpAndMask();
    }

    public void insertIpAndMask() throws ArrayIndexOutOfBoundsException, NullPointerException{
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        if(temp.isEmpty())
            temp = getThisPcIp();

        String[] ipAndMask = temp.split("/");
        String[] ip = ipAndMask[0].split("\\.");
        if(ip.length != 4) {
            System.out.println("Bledne IP");
            System.exit(1);
        }
        else {
            validateIP(ip);

            this.mask = Conversion.changeMaskToLongBin(ipAndMask[1]);
            this.ip = Conversion.chanegeIpToBinaryString(ip);
        }
    }

    private void validateIP(String[] ip) {

        for(int i = 0 ; i < ip.length ; i++) {

            int z = Integer.parseInt(ip[0]);

            if(z < 0 || z > 255) {
                throw new InputMismatchException("Wprowadzono zle dane");
            }

        }
    }

    private String getThisPcIp() {

        StringBuilder sb = new StringBuilder();
        try {
            InetAddress localHost = InetAddress.getLocalHost();

            sb.append(localHost.getHostAddress());
            sb.append("/");
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            try {
                sb.append(networkInterface.getInterfaceAddresses().get(3).getNetworkPrefixLength());
            } catch (IndexOutOfBoundsException e){
                sb.append(networkInterface.getInterfaceAddresses().get(2).getNetworkPrefixLength());
            }


        } catch (UnknownHostException | SocketException e) {
            System.out.println(e.getMessage());
        }

       System.out.println(sb.toString()+"\n");
        return sb.toString();

    }

    public Long getMask() {
        return mask;
    }

    public String[] getIp() {
        return ip;
    }

}
