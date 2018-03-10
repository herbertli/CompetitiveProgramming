import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int groups = 1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split("");
            int first = Integer.parseInt(temp[0]);
            int second = Integer.parseInt(temp[1]);
            if (i == 0) {
                end = second;
            }
            if (end == first) {
                groups++;
            }
            end = second;
        }
        System.out.println(groups);

    }

}