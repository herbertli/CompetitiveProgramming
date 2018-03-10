import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int ins = scanner.nextInt();
            Stack<Integer> s = new Stack<>();
            LinkedList<Integer> l = new LinkedList<>();
            PriorityQueue<Integer> p = new PriorityQueue<>(10,  Collections.reverseOrder());
            boolean isStack = true;
            boolean isQueue = true;
            boolean isPQueue = true;
            for (int in = 0; in < ins; in++) {
                int type = scanner.nextInt();
                if (type == 1) {
                    int next = scanner.nextInt();
                    l.add(next);
                    p.add(next);
                    s.push(next);
                } else if (type == 2){
                    int exp = scanner.nextInt();
                    if (!l.isEmpty()) {
                        if (exp != l.remove()) {
                            isQueue = false;
                        }
                    } else {
                        isQueue = false;
                    }
                    if (!s.isEmpty()) {
                        if (exp != s.pop()) {
                            isStack = false;
                        }
                    } else {
                        isStack = false;
                    }
                    if (!p.isEmpty()) {
                        if (exp != p.poll()) {
                            isPQueue = false;
                        }
                    } else {
                        isPQueue = false;
                    }
                }
            }
            if (!isStack && !isQueue && !isPQueue) {
                System.out.println("impossible");
            } else if (isStack && !isQueue && !isPQueue) {
                System.out.println("stack");
            } else if (!isStack && isQueue && !isPQueue) {
                System.out.println("queue");
            } else if (!isStack && !isQueue && isPQueue) {
                System.out.println("priority queue");
            } else {
                System.out.println("not sure");
            }
        }
        scanner.close();
    }

}
