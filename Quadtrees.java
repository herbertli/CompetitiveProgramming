import java.util.*;

public class Main {

    static int index = -1;

    private static int countBlack(int depth, String s) {
        index++;
        if (index == s.length()) {
            return 0;
        }
        if (s.charAt(index) == 'e') {
            return 0;
        } else if (s.charAt(index) == 'f') {
            return (int) (1024 * Math.pow(4, -depth));
        } else {
            return countBlack(depth + 1, s) +
                    countBlack(depth + 1, s) +
                    countBlack(depth + 1, s) +
                    countBlack(depth + 1, s);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for (int c = 0; c < cases; c++) {

            String a = scanner.next();
            String b = scanner.next();
            String s = "";
            int ia = 0;
            int ib = 0;

            while (ia < a.length() && ib < b.length()) {
                if (a.charAt(ia) == b.charAt(ib)) {
                    s += a.charAt(ia);
                    ia++;
                    ib++;
                } else if (a.charAt(ia) == 'f') {
                    s += a.charAt(ia);
                    ia++;
                    if (b.charAt(ib) == 'p') {
                        ib += 5;
                    } else {
                        ib++;
                    }
                } else if (b.charAt(ib) == 'f') {
                    s += b.charAt(ib);
                    ib++;
                    if (a.charAt(ia) == 'p') {
                        ia += 5;
                    } else {
                        ia++;
                    }
                } else if (a.charAt(ia) == 'p') {
                    s += a.substring(ia, ia + 5);
                    ia += 5;
                    ib++;
                } else if (b.charAt(ib) == 'p') {
                    s += b.substring(ib, ib + 5);
                    ib += 5;
                    ia++;
                }
            }
            int black = countBlack(0, s);
            index = -1;
            System.out.println("There are " + black + " black pixels.");
        }
        scanner.close();
    }
}
