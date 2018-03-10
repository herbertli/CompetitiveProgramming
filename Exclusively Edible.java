import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {
            String[] temp = in.readLine().split("\\s+");

            int width = Integer.parseInt(temp[0]);
            int length = Integer.parseInt(temp[1]);

            int pos_width = Integer.parseInt(temp[2]);
            int pos_length = Integer.parseInt(temp[3]);

            int top = length - pos_length - 1;
            int bottom = pos_length;
            int left = width - pos_width - 1;
            int right = pos_width;

            if ((top ^ bottom ^ left ^ right) > 0) {
                System.out.println("Gretel");
            } else {
                System.out.println("Hansel");
            }

        }

    }

}