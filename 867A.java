import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        String s = in.readLine();

        int sea = 0;
        int fran = 0;

        char start = s.charAt(0);

        for (int i = 0; i < s.length(); i++) {

            if (start != s.charAt(i)) {
                if (s.charAt(i) == 'S') {
                    sea++;
                    start = s.charAt(i);
                } else {
                    fran++;
                    start = s.charAt(i);
                }
            }

        }

        if (fran > sea) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }



    }

}