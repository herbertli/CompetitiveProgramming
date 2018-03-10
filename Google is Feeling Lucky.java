import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int c = 1; c <= cases; c++) {
            LinkedHashMap<String, Integer> h = new LinkedHashMap<>(16, .75f, false);
            for (int s = 0; s < 10; s++) {
                String site = sc.next();
                int rel = sc.nextInt();
                h.put(site, rel);
            }
            int max = 0;
            String s = "";
            Set set = h.entrySet();
            Iterator i = set.iterator();

            while(i.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry) i.next();
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    s = entry.getKey();
                } else if (entry.getValue() == max) {
                    s += "\n" + entry.getKey();
                }
            }
            System.out.printf("Case #%d:\n", c);
            System.out.println(s);
        }
    }

}
