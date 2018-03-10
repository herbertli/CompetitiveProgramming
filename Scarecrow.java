import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            int l = Integer.parseInt(in.readLine());
            String[] row = in.readLine().split("");

            int scarecrows = 0;
            String prev = row[0];
            for (int i = 1; i < l; i++) {
                if (prev.equals(".")) {
                    scarecrows += 1;
                    row[i] = "#";
                    if (i != l - 1) row[i + 1] = "#";
                }
                prev = row[i];
            }

            if (row[l - 1].equals(".")) scarecrows += 1;

            System.out.println("Case " + (c + 1) + ": " + scarecrows);

        }

    }

}
