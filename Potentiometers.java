import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class SegmentTree {
    private int[] list;
    private int[] st;

    SegmentTree(int[] list) {
        this.list = list;
        this.st = new int[4 * list.length];
        build(0, list.length - 1, 0);
    }

    private void build(int nL, int nR, int n) {
        if (nL == nR) {
            st[n] = list[nL];
        } else {
            int mid = (nL + nR) / 2;
            int l = left(n);
            int r = right(n);
            build(nL, mid, l);
            build(mid + 1, nR, r);
            st[n] = st[l] + st[r];
        }
    }

    private int left(int n) {
        return 2 * n + 1;
    }

    private int right(int n) {
        return left(n) + 1;
    }

    int sum(int L, int R) {
        return sum(L, R, 0, list.length - 1, 0);
    }

    private int sum(int L, int R, int nL, int nR, int n) {
        if (R < nL || L > nR)
            return 0;
        if (L <= nL && nR <= R) {
            return st[n];
        } else {
            int mid = (nL + nR) / 2;
            int lSum = sum(L, R, nL, mid, left(n));
            int rSum = sum(L, R, mid + 1, nR, right(n));
            return lSum + rSum;
        }
    }

    void update(int pos, int value) {
        update(pos, value, 0, list.length - 1, 0);
    }

    private void update(int pos, int value, int nL, int nR, int n) {
        if (nL == nR) {
            st[n] = value;
        } else {
            int mid = (nL + nR) / 2, l = left(n), r = right(n);
            if (pos <= mid)
                update(pos, value, nL, mid, l);
            else
                update(pos, value, mid + 1, nR, r);
            st[n] = st[l] + st[r];
        }
    }

}

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int c = 1;
        while (true) {

            int pots = Integer.parseInt(in.readLine());

            if (pots == 0) break;

            int[] init = new int[pots];

            for (int i = 0; i < pots; i++) {
                init[i] = Integer.parseInt(in.readLine());
            }

            SegmentTree s = new SegmentTree(init);

            if (c != 1) System.out.println();

            System.out.println("Case " + c + ":");
            c++;

            while (true) {
                String ins = in.readLine();

                if (ins.equals("END")) break;

                String[] temp = ins.split("\\s+");

                if (temp[0].equals("M")) {
                    System.out.println(s.sum(Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]) - 1));
                } else {
                    s.update(Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
                }
            }

        }
    }

}
