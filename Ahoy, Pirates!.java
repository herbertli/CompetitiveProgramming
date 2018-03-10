import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private StringBuilder list;
    private int[] st;
    private char[] lazy;

    private Main(StringBuilder list) {
        this.list = list;
        this.st = new int[4 * list.length()];
        this.lazy = new char[4 * list.length()];
        build(0, list.length() - 1, 0);
    }

    private void build(int nL, int nR, int n) {
        if (nL == nR) {
            st[n] = Integer.parseInt(list.charAt(nL) + "");
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

    private void lazyPropagate(int nL, int nR, int n) {
        int l = left(n);
        int r = right(n);
        if (lazy[n] == 's') {
            st[n] = nR - nL + 1;
        } else if (lazy[n] == 'e') {
            st[n] = 0;
        } else if (lazy[n] == 'f') {
            st[n] = nR - nL + 1 - st[n];
        } else if (lazy[n] == '\u0000') {
            return;
        }

        if (nL != nR) {
            if (lazy[n] == 's') {
                lazy[l] = 's';
                lazy[r] = 's';
            } else if (lazy[n] == 'e') {
                lazy[l] = 'e';
                lazy[r] = 'e';
            } else if (lazy[n] == 'f') {
                if (lazy[left(n)] == 'e') {
                    lazy[left(n)] = 's';
                } else if (lazy[left(n)] == 's') {
                    lazy[left(n)] = 'e';
                } else if (lazy[left(n)] == 'f') {
                    lazy[left(n)] = '\u0000';
                } else {
                    lazy[left(n)] = 'f';
                }
                if (lazy[right(n)] == 'e') {
                    lazy[right(n)] = 's';
                } else if (lazy[right(n)] == 's') {
                    lazy[right(n)] = 'e';
                } else if (lazy[right(n)] == 'f') {
                    lazy[right(n)] = '\u0000';
                } else {
                    lazy[right(n)] = 'f';
                }
            }
        }
        lazy[n] = '\u0000';
    }

    private int sumQuery(int L, int R) {
        return sumQuery(L, R, 0, list.length() - 1, 0);
    }

    private int sumQuery(int L, int R, int nL, int nR, int n) {
        if (R < nL || L > nR)
            return 0;
        lazyPropagate(nL, nR, n);
        if (L <= nL && nR <= R) {
            return st[n];
        } else {
            int mid = (nL + nR) / 2;
            int lSum = sumQuery(L, R, nL, mid, left(n));
            int rSum = sumQuery(L, R, mid + 1, nR, right(n));
            return lSum + rSum;
        }
    }

    private void invert(int a, int b) {
        invert(a, b, 0, list.length() - 1, 0);
    }

    private void invert(int L, int R, int nL, int nR, int n) {
        lazyPropagate(nL, nR, n);
        if (R < nL || L > nR)
            return;
        if (nL == nR) {
            st[n] = (st[n] == 1) ? 0 : 1;
        } else if (L <= nL && nR <= R) {
            st[n] = nR - nL + 1 - st[n];
            if (lazy[left(n)] == 'e') {
                lazy[left(n)] = 's';
            } else if (lazy[left(n)] == 's') {
                lazy[left(n)] = 'e';
            } else if (lazy[left(n)] == 'f') {
                lazy[left(n)] = '\u0000';
            } else {
                lazy[left(n)] = 'f';
            }
            if (lazy[right(n)] == 'e') {
                lazy[right(n)] = 's';
            } else if (lazy[right(n)] == 's') {
                lazy[right(n)] = 'e';
            } else if (lazy[right(n)] == 'f') {
                lazy[right(n)] = '\u0000';
            } else {
                lazy[right(n)] = 'f';
            }
        } else {
            int mid = (nL + nR) / 2;
            invert(L, R, nL, mid, left(n));
            invert(L, R, mid + 1, nR, right(n));
            st[n] = st[left(n)] + st[right(n)];
        }
    }

    private void updateBarb(int a, int b) {
        updateBarb(a, b, 0, list.length() - 1, 0);
    }

    private void updateBarb(int L, int R, int nL, int nR, int n) {
        lazyPropagate(nL, nR, n);
        if (R < nL || L > nR)
            return;
        if (nL == nR) {
            st[n] = 0;
        } else if (L <= nL && nR <= R) {
            st[n] = 0;
            lazy[left(n)] = 'e';
            lazy[right(n)] = 'e';
        } else {
            int mid = (nL + nR) / 2;
            updateBarb(L, R, nL, mid, left(n));
            updateBarb(L, R, mid + 1, nR, right(n));
            st[n] = st[left(n)] + st[right(n)];
        }
    }

    private void updateBucc(int a, int b) {
        updateBucc(a, b, 0, list.length() - 1, 0);
    }

    private void updateBucc(int L, int R, int nL, int nR, int n) {
        lazyPropagate(nL, nR, n);
        if (R < nL || L > nR)
            return;
        if (nL == nR) {
            st[n] = 1;
        } else if (L <= nL && nR <= R) {
            st[n] = nR - nL + 1;
            lazy[left(n)] = 's';
            lazy[right(n)] = 's';
        } else {
            int mid = (nL + nR) / 2;
            updateBucc(L, R, nL, mid, left(n));
            updateBucc(L, R, mid + 1, nR, right(n));
            st[n] = st[left(n)] + st[right(n)];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int c = 1; c <= cases; ++c) {
            StringBuilder list = new StringBuilder();
            int instrs = Integer.parseInt(in.readLine());
            for (int i = 0; i < instrs; i++) {
                int reps = Integer.parseInt(in.readLine());
                String pat = in.readLine().trim();
                for (int rep = 0; rep < reps; rep++) {
                    for (int ch = 0; ch < pat.length(); ch++) {
                        list.append(pat.charAt(ch));
                    }
                }
            }
            Main t = new Main(list);
            System.out.println("Case " + c + ":");
            int queryNo = 1;
            int queries = Integer.parseInt(in.readLine());
            for (int q = 1; q <= queries; q++) {

//                for (int i = 0; i < t.st.length; i++) {
//                    System.out.printf(t.st[i] + " ");
//                }
//                System.out.println();
//                for (int i = 0; i < t.lazy.length; i++) {
//                    if (t.lazy[i] == '\u0000')
//                        System.out.printf(" ");
//                        System.out.printf(t.lazy[i] + " ");
//                }
//                System.out.println("\n======================");

                String[] ins = in.readLine().split(" ");
                String type = ins[0];
                int low = Integer.parseInt(ins[1]);
                int high = Integer.parseInt(ins[2]);
                switch (type) {
                    case "F":
                        t.updateBucc(low, high);
                        break;
                    case "E":
                        t.updateBarb(low, high);
                        break;
                    case "I":
                        t.invert(low, high);
                        break;
                    case "S":
                        System.out.println("Q" + queryNo++ + ": " + t.sumQuery(low, high));
                        break;
                }
            }
        }

    }
}