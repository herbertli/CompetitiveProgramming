import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String[] s = in.readLine().split(" ");

            int n = Integer.parseInt(s[0]);
            int d = Integer.parseInt(s[1]);
            int r = Integer.parseInt(s[2]);

            if (n == 0 && d == 0 && r == 0) {
                break;
            }

            ArrayList<Integer> morning = new ArrayList<>();
            ArrayList<Integer> night = new ArrayList<>();

            String[] mrt = in.readLine().split(" ");
            String[] nrt = in.readLine().split(" ");

            for (String temp: mrt) {
                morning.add(Integer.parseInt(temp));
            }
            Collections.sort(morning);


            for (String temp: nrt) {
                night.add(Integer.parseInt(temp));
            }
            Collections.sort(night);
            Collections.reverse(night);


            int overtimePay = 0;

            for (int j = 0; j < n; j++) {

                int overtimeHours = morning.get(j) + night.get(j) - d;

                if (overtimeHours > 0) {
                    overtimePay += overtimeHours * r;
                }

            }

            System.out.println(overtimePay);
        }


    }

}
