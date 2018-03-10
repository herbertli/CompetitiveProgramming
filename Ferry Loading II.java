import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.*;

public class Main {

    private static int[] dp;
    private static int[] trips;

    private static int travelTime;
    private static int[] arrivalTimes;
    private static int maxCars;

    private static int best(int end) {
        if (dp[end] != 0) return dp[end];
        if (end + 1 <= maxCars) {
            dp[end] = arrivalTimes[end] + travelTime;
            trips[end] = 1;
            return arrivalTimes[end] + travelTime;
        }
        int min = Integer.MAX_VALUE;
        int numtrips = 0;
        for (int cut = end - 1; cut >= end - maxCars && cut >= 0; cut--) {
            int endTime = best(cut);
            if (endTime + travelTime <= arrivalTimes[end])
                endTime = arrivalTimes[end] + travelTime;
            else
                endTime += 2 * travelTime;
            min = Math.min(endTime, min);
            numtrips = 1 + trips[cut];
        }
        trips[end] = numtrips;
        dp[end] = min;
        return min;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            String[] s = in.readLine().split("\\s+");
            maxCars = Integer.parseInt(s[0]);
            travelTime = Integer.parseInt(s[1]);
            int numCars = Integer.parseInt(s[2]);

            arrivalTimes = new int[numCars];
            for (int car = 0; car < numCars; car++) {
                arrivalTimes[car] = Integer.parseInt(in.readLine());
            }

            dp = new int[numCars];
            trips = new int[numCars];

            int endTime = best(numCars - 1);
            int numTrips = trips[numCars - 1];

            System.out.println(endTime + " " + numTrips);
        }


    }

}
