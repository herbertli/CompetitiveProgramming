import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int[] n = new int[4];
        for (int i = 0; i < 4; i++) n[i] = Integer.parseInt(temp[i]);

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (n[i] + n[j] > n[k] && n[k] + n[j] > n[i] && n[i] + n[k] > n[j]) {
                        System.out.println("TRIANGLE");
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (n[i] + n[j] == n[k] || n[j] + n[k] == n[i] || n[i] + n[k] == n[j]) {
                        System.out.println("SEGMENT");
                        return;
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");

    }

}