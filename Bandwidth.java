import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // from class notes
    private static boolean nextPermutation(char[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }

    private static int getBandwidth(char[] ch, TreeMap<Character, TreeSet<Character>> t) {
        int bandwidth = 0;

        HashMap<Character, Integer> h = new HashMap<>();

        for (int i = 0; i < ch.length; i++) {
            h.put(ch[i], i);
        }

        for (char parent: t.keySet()) {

            for (char child: t.get(parent)) {

                int dist = Math.abs(h.get(parent) - h.get(child));

                if (dist > bandwidth) bandwidth = dist;

            }

        }

        return bandwidth;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String s = in.readLine();

            if (s.equals("#")) break;

            String[] line = s.split(";");

            TreeMap<Character, TreeSet<Character>> t = new TreeMap<>();

            for (String c : line) {

                String[] temp = c.split(":");

                Character parent = temp[0].charAt(0);
                String[] children = temp[1].split("");

                for (String ch : children) {
                    if (!t.containsKey(parent)) {
                        t.put(parent, new TreeSet<>());
                    }

                    char cha = ch.charAt(0);

                    if (!t.get(parent).contains(cha)) {
                        t.get(parent).add(cha);
                    }

                    if (!t.containsKey(cha)) {
                        t.put(cha, new TreeSet<>());
                    }


                    if (!t.get(cha).contains(parent)) {
                        t.get(cha).add(parent);
                    }

                }
            }

            char[] nodes = new char[t.size()];
            int i = 0;
            for (char c: t.keySet()) {
                nodes[i] = c;
                i++;
            }

            int min = 8;
            char[] order = null;
            do {
                if (getBandwidth(nodes, t) < min) {
                    min = getBandwidth(nodes, t);
                    order = Arrays.copyOf(nodes, nodes.length);
                }
            } while (nextPermutation(nodes));

            for (char c: order) {
                System.out.printf(c + " ");
            }
            System.out.printf("-> " + min + "\n");


        }


    }

}
