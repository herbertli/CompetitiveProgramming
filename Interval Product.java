import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private int[] list;
    private int[] st;

    private Main(int[] list) {
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
            st[n] = st[l] * st[r];
        }
    }

    private int left(int n) {
        return 2 * n + 1;
    }

    private int right(int n) {
        return left(n) + 1;
    }

    private int product(int L, int R) {
        return product(L, R, 0, list.length - 1, 0);
    }

    private int product(int L, int R, int nL, int nR, int n) {
        if (R < nL || L > nR)
            return 1;
        if (L <= nL && nR <= R) {
            return st[n];
        } else {
            int mid = (nL + nR) / 2;
            int lProd = product(L, R, nL, mid, left(n));
            int rProd = product(L, R, mid + 1, nR, right(n));
            if (lProd * rProd > 0) {
                return 1;
            } else if (lProd * rProd < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private void update(int pos, int value) {
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
            st[n] = st[l] * st[r];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        while ((temp = in.readLine()) != null) {
            String[] s = temp.split(" ");
            int n = Integer.parseInt(s[0]);
            int[] list = new int[n];
            int queries = Integer.parseInt(s[1]);
            s = in.readLine().split(" ");
            for (int i = 0; i < s.length; i++) {
                int ii = Integer.parseInt("" + s[i]);
                if (ii < 0) list[i] = -1;
                else if (ii > 0) list[i] = 1;
                else list[i] = 0;
            }
            Main t = new Main(list);
            for (int q = 0; q < queries; q++) {

                String[] ins = in.readLine().split(" ");
                String type = ins[0];
                int x = Integer.parseInt(ins[1]) - 1;
                int y = Integer.parseInt(ins[2]);
                switch (type) {
                    case "C":
                        if (y < 0) t.update(x, -1);
                        else if (y > 0) t.update(x, 1);
                        else t.update(x, 0);
                        break;
                    case "P":
                        int ret = t.product(x, y - 1);
                        if (ret > 0) System.out.printf("+");
                        else if (ret < 0) System.out.printf("-");
                        else System.out.printf("0");
                        break;
                }
            }
            System.out.printf("\n");
        }
    }
}
