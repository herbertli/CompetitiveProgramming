import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class C {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int num = (1 << n) - 1 - n;
        System.out.println(num);

    }

}
