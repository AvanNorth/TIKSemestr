package tat.itis;

import tat.itis.decode.LZW;
import tat.itis.encode.ArithmCoding;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите 1 - для демонстрации Алгоритм. кодирования\nВведите 2 - для демонстрации LZW + Б-У декодирования");
        int choose = sc.nextInt();

        while (choose != 1 &&  choose != 2) {
            System.out.println("Введите 1 или 2");
            choose = sc.nextInt();
        }

        switch (choose) {
            case 1 -> ArithmCoding.startEncode();
            case 2 -> LZW.startDecode();
            default -> {
            }
        }
    }
}
