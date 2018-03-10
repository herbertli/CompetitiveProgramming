import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();

        for (int c = 0; c < cases; c++) {
            String bro = scanner.next();

            if (bro.length() == 5) {
                System.out.println("3");
            } else {
                if (bro.matches("[a-z]ne") || bro.matches("on[a-z]") || bro.matches("o[a-z]e")) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }

        }


    }

}
