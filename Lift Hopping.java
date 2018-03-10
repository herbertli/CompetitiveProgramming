import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int minDistance(int[] dist, boolean[] intree) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!intree[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    static int dijkstra(int[][] floors, int[] times, int dest) {
        int[] dist = new int[500];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int e = 0; e < times.length; e++) {
            if (floors[e][0] == 1) {
                dist[e * 100] = 0;
            }
        }
        boolean[] intree = new boolean[500];
        Arrays.fill(intree, false);

        while (true) {

            int u = minDistance(dist, intree);
            if (u == dest || u == -1) break;
            intree[u] = true;

            int currentFloor = u % 100;
            int currentElevator = Math.floorDiv(u, 100);

            // switch floors (staying on the current elevator)
            for (int adjFloor = 0; adjFloor < 100; adjFloor++) {
                if (floors[currentElevator][adjFloor] == 1) {
                    int time = times[currentElevator] * Math.abs(adjFloor - currentFloor);
                    if (!intree[adjFloor + currentElevator * 100] && dist[u] != Integer.MAX_VALUE &&
                            time + dist[u] < dist[currentElevator * 100 + adjFloor]) {
                        dist[adjFloor + currentElevator * 100] = time + dist[u];
                    }
                }
            }

            // switch elevators (staying at the current floor)
            for (int adjEle = 0; adjEle < 5; adjEle++) {
                if (floors[adjEle][currentFloor] == 1 && adjEle != currentElevator) {
                    if (!intree[adjEle * 100 + currentFloor] && dist[u] != Integer.MAX_VALUE &&
                            60 + dist[u] < dist[adjEle * 100 + currentFloor]) {
                        dist[adjEle * 100 + currentFloor] = 60 + dist[u];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int e = 0; e < times.length; e++) {
            min = Math.min(dist[e * 100 + dest], min);
        }
        return min;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            int nelevators = Integer.parseInt(s.split("\\s+")[0]);
            int target = Integer.parseInt(s.split("\\s+")[1]);

            int[] elevators = new int[nelevators];
            String[] stimes = in.readLine().split("\\s+");
            for (int i = 0; i < elevators.length; i++) elevators[i] = Integer.parseInt(stimes[i]);

            int[][] floors = new int[5][100];
            for (int i = 0; i < nelevators; i++) {
                String[] temp = in.readLine().split("\\s+");
                for (int j = 0; j < temp.length; j++) {
                    floors[i][Integer.parseInt(temp[j])] = 1;
                }
            }

            int dist = dijkstra(floors, elevators, target);
            if (dist == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
            else System.out.println(dist);
        }
    }
}
