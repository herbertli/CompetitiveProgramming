import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s1 = in.readLine();
        String s2 = in.readLine();

        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                System.out.println(-1);
                return;
            } else if (s2.charAt(i) == s1.charAt(i)) {
                newS.append('z');
            } else {
                newS.append(s2.charAt(i));
            }

        }

        System.out.println(newS.toString());

    }

}