import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int size = scanner.nextInt();
            String s_number = scanner.next();
            if (size == 0 && s_number.equals("0")) {
                break;
            }
            int[] number = new int[s_number.length()];
            for (int c = 0; c < s_number.length(); c++) {
                number[c] = Integer.parseInt("" + s_number.charAt(c));
            }
            for (int row = 0; row < 2 * size + 3; row++) {
                String r = "";
                for (int n = 0; n < number.length; n++) {
                    if (row == 0) {
                        // top row
                        if (number[n] == 1 || number[n] == 4) {
                            for (int c = 0; c < size + 2; c++) {
                                r += " ";
                            }
                        } else {
                            r += " ";
                            for (int c = 0; c < size; c++) {
                                r += "-";
                            }
                            r += " ";
                        }
                    }
                    else if (row == size + 1) {
                        // middle row
                        if (number[n] == 1 || number[n] == 7 || number[n] == 0) {
                            for (int c = 0; c < size + 2; c++) {
                                r += " ";
                            }
                        } else {
                            r += " ";
                            for (int c = 0; c < size; c++) {
                                r += "-";
                            }
                            r += " ";
                        }
                    } else if (row == 2 * size + 2) {
                        // bottom row
                        if (number[n] == 1 || number[n] == 7 || number[n] == 4) {
                            for (int c = 0; c < size + 2; c++) {
                                r += " ";
                            }
                        } else {
                            r += " ";
                            for (int c = 0; c < size; c++) {
                                r += "-";
                            }
                            r += " ";
                        }
                    } else if (row < size + 1) {
                        // top columns
                        if (number[n] == 1 || number[n] == 2 || number[n] == 3 || number[n] == 7) {
                            for (int c = 0; c < size + 1; c++) {
                                r += " ";
                            }
                            r += "|";
                        } else if (number[n] == 5 || number[n] == 6){
                            r += "|";
                            for (int c = 0; c < size + 1; c++) {
                                r += " ";
                            }
                        } else {
                            r += "|";
                            for (int c = 0; c < size; c++) {
                                r += " ";
                            }
                            r += "|";
                        }
                    } else {
                        // bottom columns
                        if (number[n] == 1 || number[n] == 3 || number[n] == 4 || number[n] == 5 || number[n] == 7 || number[n] == 9) {
                            for (int c = 0; c < size + 1; c++) {
                                r += " ";
                            }
                            r += "|";
                        } else if (number[n] == 2){
                            r += "|";
                            for (int c = 0; c < size + 1; c++) {
                                r += " ";
                            }
                        } else {
                            r += "|";
                            for (int c = 0; c < size; c++) {
                                r += " ";
                            }
                            r += "|";
                        }
                    }

                    if (n != number.length - 1) {
                        r += " ";
                    }
                }
                System.out.println(r);
            }
            System.out.println();
        }
        scanner.close();
    }

}