import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();

        for (int c = 1; c <= cases; c++) {
            int b = in.nextInt();

            int max = 0;
            int maxStart = 0;
            int maxEnd = 0;

            int currStart = 1;
            int currEnd = 1;
            int niceness = 0;

            for (int i = 0; i < b - 1; i++) {
                currEnd++;
                niceness += in.nextInt();
                if (niceness < 0) {
                    niceness = 0;
                    currStart = currEnd;
                }
                if (niceness >= max) {
                    if (niceness == max) {
                        if (currEnd - currStart > maxEnd - maxStart) {
                            max = niceness;
                            maxStart = currStart;
                            maxEnd = currEnd;
                        }
                    } else {
                        max = niceness;
                        maxStart = currStart;
                        maxEnd = currEnd;
                    }
                }
            }

            if (max > 0)
                System.out.printf("The nicest part of route %d is between stops %d and %d\n", c, maxStart, maxEnd);
            else
                System.out.printf("Route %d has no nice parts\n", c);
        }
    }

}
