import java.io.BufferedReader;
import java.io.InputStreamReader;

class SegTree {

    private int[] list;
    private int[] st;
    private static int MAX_TREE = 0;
    private static int MIN_TREE = 1;

    SegTree(int[] list, int type) {
        this.list = list;
        this.st = new int[4 * list.length];
        build(0, list.length - 1, 0, type);
    }

    private void build(int nL, int nR, int n, int type) {
        if (nL == nR) {
            st[n] = list[nL];
        } else {
            int mid = (nL + nR) / 2;
            int l = left(n);
            int r = right(n);
            build(nL, mid, l, type);
            build(mid + 1, nR, r, type);
            if (type == SegTree.MAX_TREE) st[n] = st[l] >= st[r] ? st[l] : st[r];
            else if (type == SegTree.MIN_TREE) st[n] = st[l] <= st[r] ? st[l] : st[r];
        }
    }

    private int left(int n) {
        return 2 * n + 1;
    }

    private int right(int n) {
        return left(n) + 1;
    }

    int minQuery(int L, int R) {
        return minQuery(L, R, 0, list.length - 1, 0);
    }

    private int minQuery(int L, int R, int nL, int nR, int n) {
        if (R < nL || L > nR) return Integer.MAX_VALUE;
        if (L <= nL && nR <= R)
            return st[n];
        int mid = (nL + nR) / 2;
        int lMin = minQuery(L, R, nL, mid, left(n));
        int rMin = minQuery(L, R, mid + 1, nR, right(n));
        return lMin <= rMin ? lMin : rMin;
    }

    int maxQuery(int L, int R) {
        return maxQuery(L, R, 0, list.length - 1, 0);
    }

    private int maxQuery(int L, int R, int nL, int nR, int n) {
        if (R < nL || L > nR) return -1;
        if (L <= nL && nR <= R)
            return st[n];
        int mid = (nL + nR) / 2;
        int lMax = maxQuery(L, R, nL, mid, left(n));
        int rMax = maxQuery(L, R, mid + 1, nR, right(n));
        return lMax >= rMax ? lMax : rMax;
    }

    void updateMin(int pos, int value) {
        updateMin(pos, value,0, list.length - 1, 0);
    }

    private void updateMin(int pos, int value, int nL, int nR, int n) {
        if (nL == nR) {
            st[n] = value;
        } else {
            int mid = (nL + nR) / 2;
            if (pos <= mid)
                updateMin(pos, value, nL, mid, left(n));
            else
                updateMin(pos, value, mid + 1, nR, right(n));
            st[n] = st[left(n)] <= st[right(n)] ? st[left(n)] : st[right(n)];
        }
    }

    void updateMax(int pos, int value) {
        updateMax(pos, value, 0, list.length - 1, 0);
    }

    private void updateMax(int pos, int value, int nL, int nR, int n) {
        if (nL == nR) {
            st[n] = value;
        } else {
            int mid = (nL + nR) / 2;
            if (pos <= mid)
                updateMax(pos, value, nL, mid, left(n));
            else
                updateMax(pos, value, mid + 1, nR, right(n));
            st[n] = st[left(n)] >= st[right(n)] ? st[left(n)] : st[right(n)];
        }
    }

}

public class Main {

    private SegTree[] maxs;
    private SegTree[] mins;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Main cen = new Main();

        int size = Integer.parseInt(in.readLine());
        cen.maxs = new SegTree[size];
        cen.mins = new SegTree[size];
        String[] s;
        for (int r = 0; r < size; r++) {
            s = in.readLine().split(" ");
            int[] temp = new int[size];
            for (int i = 0; i < size; i++) {
                temp[i] = Integer.parseInt(s[i]);
            }
            cen.maxs[r] = new SegTree(temp, 0);
            cen.mins[r] = new SegTree(temp, 1);
        }

        int queries = Integer.parseInt(in.readLine());

        for (int q = 0; q < queries; q++) {
            s = in.readLine().split(" ");

            int b = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            int d = Integer.parseInt(s[3]);
            int e;
            if (s.length == 5) {
                e = Integer.parseInt(s[4]);
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int row = b - 1; row <= d - 1; row++) {
                    int rowmax = cen.maxs[row].maxQuery(c - 1,e - 1);
                    int rowmin = cen.mins[row].minQuery(c - 1, e - 1);
                    if (rowmax > max) max = rowmax;
                    if (rowmin < min) min = rowmin;
                }
                System.out.println(max + " " + min);
            } else {
                cen.mins[b - 1].updateMin(c - 1, d);
                cen.maxs[b - 1].updateMax(c - 1, d);
            }

        }

        in.close();

    }

}
