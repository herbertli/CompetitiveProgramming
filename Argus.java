import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer, Integer> h = new HashMap<>();

        PriorityQueue<Integer> p = new PriorityQueue<>();


        while (true) {
            String s = in.readLine();
            if (s.trim().equals("#")) {
                break;
            }
            String[] sp = s.split(" ");
            int id = Integer.parseInt(sp[1]);
            int time = Integer.parseInt(sp[2]);
            h.put(id, time);
            p.add(time * 10000 + id);
        }

        int k = Integer.parseInt(in.readLine());
        int pr = 0;
        while (pr < k) {
            int i = p.remove();
            p.add(i + h.get(i % 10000) * 10000);
            pr++;
            System.out.println(i % 10000);
        }

        in.close();
    }

}
