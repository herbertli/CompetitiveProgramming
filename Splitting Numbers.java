import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }
            int curr = 1;
            int mult = 0;
            int a = 0;
            int b = 0;
            while(n != 0) {
                if (n % 2 != 0) {
                    if (curr == 1) {
                        a += Math.pow(2, mult);
                        curr = 0;
                    } else {
                        b += Math.pow(2, mult);
                        curr = 1;
                    }
                }
                mult++;
                n = n >> 1;
            }
            System.out.println(a + " " + b);
        }
        scanner.close();
    }

}
