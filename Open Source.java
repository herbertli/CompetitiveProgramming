import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String s = in.readLine();

            if (s.equals("0")) break;

            TreeSet<String> removed = new TreeSet<>();
            TreeMap<String, TreeSet<String>> t = new TreeMap<>();
            TreeMap<Integer, TreeSet<String>> t2 = new TreeMap<>();

            String currClub = "";
            while (!s.equals("1")) {
                if (s.equals(s.toUpperCase())) {
                    currClub = s;
                    t.put(currClub, new TreeSet<>());
                } else {
                    boolean isUnique = true;
                    for (String k : t.keySet()) {
                        if (!k.equals(currClub) && t.get(k).contains(s)) {
                            t.get(k).remove(s);
                            removed.add(s);
                            isUnique = false;
                        }
                    }
                    if (isUnique && !removed.contains(s)) {
                        if (!t.get(currClub).contains(s)) {
                            t.get(currClub).add(s);
                        }
                    }
                }
                s = in.readLine();
            }

            for (String k : t.keySet()) {
                int size = t.get(k).size();
                if (!t2.containsKey(size)) t2.put(size, new TreeSet<>());
                t2.get(size).add(k);
            }

            for (Integer i : t2.descendingKeySet()) {
                String[] elements = new String[t2.get(i).size()];
                t2.get(i).toArray(elements);
                for (String e : elements) {
                    System.out.println(e + " " + i);
                }

            }

        }
    }
}
