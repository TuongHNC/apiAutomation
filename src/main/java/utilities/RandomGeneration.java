package utilities;

import java.util.Random;

public class RandomGeneration {

    public String randomStringAndDigits(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static int randomInRange(int min, int max) {
        if (max - min == 0) {
            return 0;
        } else {
            Random rnd = new Random();
            int randomNumber = rnd.nextInt(max - min) + min;
            return randomNumber;
        }
    }
}
