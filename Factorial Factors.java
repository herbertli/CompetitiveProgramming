import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<Integer> primes = new TreeSet<Integer>();

        long[] factors = new long[1000001];

        // pre-generate primes
        int[] sieve = new int[1001];
        sieve[0] = 1;
        sieve[1] = 1;
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i] == 0) {
                primes.add(i);
                for (int j = i * 2; j < sieve.length; j += i) {
                    sieve[j] = 1;
                }
            }
        }

        for (int i = 2; i < factors.length; i++) {
            factors[i] = factors[i - 1];
            int n = i;
            for (int p: primes) {
                if (n % p == 0) {
                    int mult = 0;
                    while (n % p == 0) {
                        mult++;
                        n /= p;
                    }
                    factors[i] += mult;
                }
            }
            if (n > 1) factors[i]++;
        }

        String temp;
        while ((temp = in.readLine()) != null) {
            int n = Integer.parseInt(temp);
            System.out.println(factors[n]);
        }
    }

}
