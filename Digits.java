import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder s = new StringBuilder(scanner.next());

            if (s.toString().equals("END")) {
                break;
            }

            int length = s.length();
            int prevlength;
            int count = 1;

            if (s.toString().equals("1")) {
                System.out.println(1);
            } else {
                while (true) {
                    prevlength = length;
                    length = ("" + length).length();
                    count++;
                    if (length == prevlength) {
                        break;
                    }

                }

                System.out.println(count);
            }
        }
    }
}
