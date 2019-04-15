package calculations;

import conversions.Conversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ip {

    private String[] ipBinary;
    private Long maskBinary;

    public Ip(String[] ipFromScanner, Long mask) {
        this.ipBinary = ipFromScanner;
        this.maskBinary = mask;
    }

    public Character classOfIp() {
        int ip = Integer.parseInt(ipBinary[0], 2);

        if (ip > 0 && ip < 127)
            return 'A';
        else if (ip > 127 && ip < 192)
            return 'B';
        else if (ip >= 192 && ip < 224)
            return 'C';
        else if (ip >= 224 && ip < 240)
            return 'D';
        else if (ip >= 240 && ip <= 247)
            return 'E';
        else
            return 'F';
    }

    public String checkPublicPrivate() {

        Integer[] ipNumber = new Integer[ipBinary.length];
        for (int i = 0; i < ipNumber.length; i++) {
            ipNumber[i] = Integer.parseInt(ipBinary[i]);
        }


        if (ipNumber[0] == 10) { // Class A
            return "Private";
        } else if (ipNumber[0] == 172 && (ipNumber[1] >= 16 && ipNumber[1] <= 31)) { // Class B
            return "Private";
        } else if (ipNumber[0] == 192 && ipNumber[1] == 168) { // Class C
            return "Private";
        }


        return "Public";

    }

    public Double calculateHostsQuantity() {

        String maskDec = Long.toBinaryString(maskBinary);
        int counter = 0;
        for (int i = 0; i < maskDec.length(); i++) {
            Character x = maskDec.charAt(i);
            if (x.equals('1'))
                counter++;
        }

        return Math.pow(2, (32 - counter)) - 2;
    }

    public Long createNetAdress() {

        return Conversion.stringToLong(ipBinary) & maskBinary;
    }

    public Long createBrodcast() {

        Long maskContradicted = Conversion.createContradictMask(maskBinary);

        return maskContradicted | Conversion.stringToLong(ipBinary);

    }

    public Long createFirstHost() {
        String firstHost = Long.toBinaryString(createNetAdress());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < firstHost.length(); i++) {

            if (firstHost.length() - 1 != i)
                sb.append(firstHost.charAt(i));
            else
                sb.append(1);
        }

        return Long.parseLong(sb.toString(), 2);
    }

    public Long createLastHost() {

        String firstHost = Long.toBinaryString(createBrodcast());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < firstHost.length(); i++) {
            if (firstHost.length() - 1 != i)
                sb.append(firstHost.charAt(i));
            else
                sb.append(0);
        }

        return Long.parseLong(sb.toString(), 2);
    }

    public void pingIp() throws IOException, InterruptedException {

        String command = "ping -c 4 " + Conversion.convertLongToShowWithDots(Conversion.stringToLong(ipBinary));

        Process proc = Runtime.getRuntime().exec(command);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();


    }

    public String[] getIpBinary() {
        return ipBinary;
    }

    public Long getMaskBinary() {
        return maskBinary;
    }
}
