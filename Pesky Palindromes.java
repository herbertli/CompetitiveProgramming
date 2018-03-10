import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            TreeSet<String> t = new TreeSet<>();
            String s = scanner.next();
            int count = 0;
            for (int start = 0; start < s.length(); start++) {
                for (int end = start; end < s.length(); end++) {
                    String check = s.substring(start, end + 1);
                    int i = 0;
                    int j = check.length() - 1;
                    boolean isPalindrome = true;
                    while (i < j) {
                        if (check.charAt(i) != check.charAt(j)) {
                            isPalindrome = false;
                            break;
                        } else {
                            i++;
                            j--;
                        }
                    }
                    if (isPalindrome && !t.contains(check)) {
                        count++;
                        t.add(check);
                    }
                }
            }
            System.out.printf("The string '%s' contains %d palindromes.\n", s, count);
        }
    }

}
