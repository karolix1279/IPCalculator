package conversions;

import java.io.IOException;

public class Conversion {

    public static Long stringToLong(String[] arr) {


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }

        return Long.parseLong(sb.toString(), 2);
    }

    public static String[] chanegeIpToBinaryString(String[] ipFromScanner) throws ArrayIndexOutOfBoundsException, NullPointerException {

        String[] ipBinary = new String[4];
        for (int i = 0; i < 4; i++) {

    ipBinary[i] = Long.toBinaryString(Long.valueOf(ipFromScanner[i]));

            if (ipBinary[i].length() < 8) {
                int k = ipBinary[i].length() - 8;
                StringBuilder sb = new StringBuilder();

                for (; k < 0; k++) {
                    sb.append("0");
                }


                ipBinary[i] = sb.toString() + ipBinary[i];


            }

        }

        return ipBinary;
    }

    public static Long changeMaskToLongBin(String mask) {

        Long valueOfMask = Long.parseLong(mask);

        return createMask(valueOfMask);
    }

    private static Long createMask(Long mask) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            if (i < mask)
                sb.append(1);
            else
                sb.append(0);
        }

        return Long.parseLong(sb.toString(), 2);
    }

    public static Long createContradictMask(Long maskB) {

        long maskaBReverse = ~maskB;

        StringBuilder sb = new StringBuilder();
        String maskBReverseString = Long.toBinaryString(maskaBReverse);

        for (int i = 32; i < 64; i++) {
            sb.append(maskBReverseString.charAt(i));
        }

        return Long.parseLong(sb.toString(), 2);
    }

    public static String convertLongToShowWithDots(Long input) {

        String inputInString = Long.toBinaryString(input);
        if (inputInString.length() != 32) {

            int k = inputInString.length() - 32;
            StringBuilder sb = new StringBuilder();

            for (; k < 0; k++) {
                sb.append("0");
            }

            inputInString = sb.toString() + inputInString;

        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < inputInString.length(); i++) {

            if (i % 8 == 0 && i > 1) {
                sb.append(".");
            }

            sb.append(inputInString.charAt(i));
        }

        String[] result = sb.toString().split("\\.");

        sb = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            sb.append(String.valueOf(Long.parseLong(result[i], 2) + "."));
        }
        sb.deleteCharAt(sb.length() - 1);


        return sb.toString();
    }

    public static String converStringToStringWithDots(String[] input) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);

            if (i < input.length - 1)
                sb.append(".");
        }

        return sb.toString();
    }

    public static String convertLongToStringWithDots(Long input) {

        String temp = Long.toBinaryString(input);

        if (temp.length() != 32) {

            int k = temp.length() - 32;
            StringBuilder sb = new StringBuilder();

            for (; k < 0; k++) {
                sb.append("0");
            }

            temp = sb.toString() + temp;

        }


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < temp.length(); i++) {
            if (i % 8 == 0 && i > 0) {
                sb.append(".");
            }

            sb.append(temp.charAt(i));
        }


        return sb.toString();
    }

}
