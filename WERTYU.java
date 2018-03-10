import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final StringBuilder t = new StringBuilder("`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./");
        while (sc.hasNextLine()) {
            StringBuilder s = new StringBuilder(sc.nextLine());

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    continue;
                }
                s.setCharAt(i, t.charAt(t.indexOf(s.charAt(i) + "") - 1));
            }
            System.out.println(s);
        }
    }

}
