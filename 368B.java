import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = in.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[] arr = new int[n];
        temp = in.readLine().split(" ");
        HashSet<Integer> t = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            t.add(arr[i]);
            arr[i] = t.size();
        }

        for (int q = 0; q < m; q++) {
            int l = Integer.parseInt(in.readLine());
            System.out.println(arr[l - 1]);
        }

    }

}
