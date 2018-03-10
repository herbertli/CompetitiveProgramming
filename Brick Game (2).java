import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int i = 1; i <= T; i++) {
            String[] temp = in.readLine().split("\\s+");
            int N = Integer.parseInt(temp[0]);
            System.out.printf("Case %d: %d\n", i, Integer.parseInt(temp[N / 2 + 1]));
        }

    }

}