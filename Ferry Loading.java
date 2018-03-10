import java.util.*;

public class Main {

    private static ArrayList<Integer> cars;
    private static String[][] decisions;
    // lookup[carNum][used space on starboard side]
    private static int[][] lookup;
    private static int ferryLength;

    private static int max(int currCar, int usedPort, int usedStar) {
        if (currCar >= cars.size()) return 0;
        if (lookup[currCar][usedStar] != 0) return lookup[currCar][usedStar];
        if (cars.get(currCar) + usedPort > ferryLength && cars.get(currCar) + usedStar > ferryLength) {
            return 0;
        }
        if (cars.get(currCar) + usedPort > ferryLength) {
            decisions[currCar][usedStar] = "starboard";
            lookup[currCar][usedStar] = 1 + max(currCar + 1, usedPort, usedStar + cars.get(currCar));
            return lookup[currCar][usedStar];
        }
        if (cars.get(currCar) + usedStar > ferryLength) {
            decisions[currCar][usedStar] = "port";
            lookup[currCar][usedStar] = 1 + max(currCar + 1, usedPort + cars.get(currCar), usedStar);
            return lookup[currCar][usedStar];
        }

        int bestPort = 1 + max(currCar + 1, usedPort + cars.get(currCar), usedStar);
        int bestStar = 1 + max(currCar + 1, usedPort, usedStar + cars.get(currCar));

        if (bestStar > bestPort) {
            lookup[currCar][usedStar] = bestStar;
            decisions[currCar][usedStar] = "starboard";
            return bestStar;
        } else {
            lookup[currCar][usedStar] = bestPort;
            decisions[currCar][usedStar] = "port";
            return bestPort;
        }
    }

    private static void doDP() {
        lookup = new int[cars.size()][ferryLength + 1];
        decisions = new String[cars.size()][ferryLength + 1];

        System.out.println(max(0, 0, 0));

        int curr = 0;
        for (int i = 0; i < cars.size(); i++) {
            String s = decisions[i][curr];
            if (s == null) break;
            System.out.println(s);
            if (s.equals("starboard")) {
                curr += cars.get(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        for (int c = 0; c < cases; c++) {
            ferryLength = in.nextInt() * 100;
            cars = new ArrayList<>();
            while (true) {
                int car_length = in.nextInt();
                if (car_length == 0) {
                    doDP();
                    if (c < cases - 1) System.out.println();
                    break;
                }
                cars.add(car_length);
            }
        }
    }

}
