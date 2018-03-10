import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        scanner.nextLine();
        for (int c = 0; c < cases; c++) {
            boolean hasLoop = false;
            String[] in = scanner.nextLine().split(" ");
            if (in.length != 1) {
                int mm = 0;
                int ff = 0;
                for (int i = 0; i < in.length; i++) {
                    if (in[i].equals("MM")) {
                        mm++;
                    } else if (in[i].equals("FF")) {
                        ff++;
                    }
                }
                if (mm == ff) {
                    hasLoop = true;
                }
            }
            if (hasLoop) {
                System.out.println("LOOP");
            } else {
                System.out.println("NO LOOP");
            }
        }
    }

}
