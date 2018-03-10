import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] s = in.readLine().split("[+]");

        int[] t = new int[s.length];
        for (int i =0 ; i < s.length; i++) t[i] = Integer.parseInt(s[i]);
        Arrays.sort(t);

        for (int i = 0; i < t.length - 1; i++) System.out.print(t[i] + "+");
        System.out.println(t[t.length - 1]);
    }

}