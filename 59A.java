import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s = in.readLine();

        int upper = 0;
        int lower = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a') lower++;
            else upper++;
        }

        if (upper > lower) {
            System.out.println(s.toUpperCase());
        } else {
            System.out.println(s.toLowerCase());
        }

    }

}