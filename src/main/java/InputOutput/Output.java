package InputOutput;

import calculations.Ip;
import conversions.Conversion;


import java.io.*;
import java.util.Scanner;


public class Output {

    Ip show;

    public Output() {
        System.out.println("Enter picked IP or press Enter to load IP of this PC ");
        Input a = new Input();
        show = new Ip(a.getIp(), a.getMask());
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Network adress: \t\t\t\t")
                .append(Conversion.convertLongToShowWithDots(show.createNetAdress()))
                .append("\nClass of Network: \t\t\t" + show.classOfIp())
                .append("\nPublic or private: \t\t\t" + show.checkPublicPrivate())
                .append("\nSubnet mask: \t\t\t\t" + Conversion.convertLongToShowWithDots(show.getMaskBinary()) + "\t\t" + Conversion.converStringToStringWithDots(show.getIpBinary()))
                .append("\nBroadcast: \t\t\t\t\t" + Conversion.convertLongToShowWithDots(show.createBrodcast()) + "\t\t" + Conversion.convertLongToStringWithDots(show.createBrodcast()))
                .append("\nPFirst Host adress: \t\t" + Conversion.convertLongToShowWithDots(show.createFirstHost()) + "\t\t\t" + Conversion.convertLongToStringWithDots(show.createFirstHost()))
                .append("\nLast Host Adress: \t\t\t" + Conversion.convertLongToShowWithDots(show.createLastHost()) + "\t\t" + Conversion.convertLongToStringWithDots(show.createLastHost()))
                .append("\nMax quantity od Hosts:\t \t" + show.calculateHostsQuantity());
        System.out.println(sb.toString());

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"))){

            bufferedWriter.write(sb.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

        boolean isHost = Conversion.stringToLong(show.getIpBinary()).equals(show.createBrodcast()) || Conversion.stringToLong(show.getIpBinary()).equals(show.createNetAdress());

        if (!isHost) {
            System.out.println("\nWould you like to ping this adress ?  Y/N");
            String response = new Scanner(System.in).nextLine().toLowerCase();

            if (response.contains("y")) {
                try {
                    show.pingIp();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
