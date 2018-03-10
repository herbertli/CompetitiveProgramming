import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class E {

    static int KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int j = 0;  // index for pat[]
        int found = 0;

        int[] lps = computeLPSArray(pat,M);

        int i = 0;  // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                found++;
                j = 0;
            }

            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return found;
    }

    static int[] computeLPSArray(String pat, int M) {
        int len = 0;
        int i = 1;
        int[] lps = new int[pat.length()];
        lps[0] = 0;  // lps[0] is always 0

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s = in.readLine();

        TreeMap<String, Integer> m = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String st = "";
                int k;
                for (k = i; k <= j; k++) {
                    st += s.charAt(k);
                }
                if (m.containsKey(st)) continue;
                String search = s.substring(k);
                int occ = KMPSearch(st, search) + 1;
                m.put(st, occ);
            }
        }

        int best = s.length();
        for (String k : m.keySet()) {
            int calc = s.length() - m.get(k) * (k.length() - 1) + k.length();
            if (calc > 0) best = Math.min(best, calc);
        }

        System.out.println(best);
    }

}