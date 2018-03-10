import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        int[] cols = new int[n];

        for (int i = 0; i < n; i++) {
            cols[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(cols);

        for (int i = 0; i < n; i++) {
            System.out.printf("%d", cols[i]);
            if (i != n - 1) System.out.printf(" ");
        }
        System.out.println();
    }

}