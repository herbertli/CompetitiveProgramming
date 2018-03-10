import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.*;

public class Main {

    private static boolean isPossible(int[] vessels, int numContainers, int maxVolume) {
        int filledCon = 0;
        int volLeft = maxVolume;
        for (int i: vessels) {
            if (i > maxVolume) return false;
            if (i > volLeft) {
                filledCon++;
                if (filledCon == numContainers) return false;
                else volLeft = maxVolume;
            }
            volLeft -= i;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s;
        while ((s = in.readLine()) != null) {
            String[] temp = s.split("\\s+");

            int vessels = Integer.parseInt(temp[0]);
            int containers = Integer.parseInt(temp[1]);

            int[] v = new int[vessels];

            String[] vs = in.readLine().split("\\s+");
            for (int i = 0; i < vessels; i++) {
                v[i] = Integer.parseInt(vs[i]);
            }

            int high = 1000000000;
            int low = 0;

            while (low <= high) {
                int test = (high + low) / 2;
                if (isPossible(v, containers, test)) {
//                    System.out.println(test + " is possible");
                    high = test - 1;
                } else {
                    low = test + 1;
//                    System.out.println(test + " is not possible");
                }
            }
            System.out.println(low);

        }

    }

}
