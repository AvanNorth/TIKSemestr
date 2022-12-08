package tat.itis.encode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArithmCoding {
    public static void startEncode() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/tat/itis/encode/txt/ArithmFile.txt"));
        String s = scanner.nextLine();
        char[] letters = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
        double[] probability = new double[letters.length];
        for (int i = 0; i < letters.length; i++) {
            probability[i] = scanner.nextDouble();
        }
        System.out.println("Результат кодирования: " + encode(letters,probability,s));
        System.out.println("Длина исходной строки: " + s.length());
    }

    static Segment[] defineSegments(char[] letters, double[] probability) {
        Segment[] segment = new Segment[10000];
        double l = 0;
        for (int i = 0; i < letters.length; i++) {
            segment[letters[i]] = new Segment(l, l + probability[i]);
            l = segment[letters[i]].right;
        }
        return segment;
    }

    public static double encode(char[] letters, double[] probability, String message) {
        Segment[] segments = defineSegments(letters, probability);
        double left = 0;
        double right = 1;
        for (int i = 0; i < letters.length; i++) {
            char symbol = message.charAt(i);

            right = left + (right - left) * segments[symbol].right;
            left = left + (right - left) * segments[symbol].left;
        }
        return (left + right) / 2;
    }
}
