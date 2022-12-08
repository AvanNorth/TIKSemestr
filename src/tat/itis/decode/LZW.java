package tat.itis.decode;

import tat.itis.decode.entities.DataFromFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LZW {
    private static final String splitRegex = " ";
    private static final String voidRegex = "";
    private static final String dictionaryPath = "src/tat/itis/decode/txt/LZWDictionary.txt";
    private static final String messagePath = "src/tat/itis/decode/txt/LZWFile.txt";

    public static void startDecode() throws FileNotFoundException {
        ArrayList<String> dictionary = getDictionary();

        DataFromFile dataFromFile = readDataFromFile();

        System.out.println("Закодированное сообщение: ");
        for (String str : dataFromFile.encodedSequence)
            System.out.print(" " + str + " ");
        System.out.println();

        String[] binaryCode = dataFromFile.encodedSequence;
        int[] code = stringToNum(binaryCode);

        String[] res = decode(dictionary, code).split(voidRegex);

        System.out.println("Сообщение после декодирования LZW: ");
        for (String str : res)
            System.out.print(" " + str + " ");
        System.out.println();

        String strRes = (BY.decode(res))[dataFromFile.strNumber - 1];

        System.out.println("Исходное сообщение: " + strRes);
    }

    public static ArrayList<String> getDictionary() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(dictionaryPath));
        String[] letters = in.nextLine().split(splitRegex);
        in.close();

        return new ArrayList<>(Arrays.asList(letters));
    }

    public static DataFromFile readDataFromFile() throws FileNotFoundException {
        Scanner message = new Scanner(new FileReader(messagePath));

        String[] seq = message.nextLine().split(splitRegex);
        int number = message.nextInt();
        message.close();

        return new DataFromFile(seq, number);
    }

    public static String decode(ArrayList<String> dictionary, int[] code) {
        String[] resultStr = new String[code.length];
        resultStr[0] = dictionary.get(code[0]);

        ArrayList<String> tmpStrArr = new ArrayList<>(1);

        tmpStrArr.add(dictionary.get(code[0])); // добавляем первый символ в словарь

        for (int i = 1; i < code.length; i++) {
            //проверяем, есть ли символ в словаре
            if ((dictionary.size() + 1) > code[i]) {
                String str = dictionary.get(code[i]);
                resultStr[i] = str;

                String[] symbols = str.split(voidRegex);

                // добавляем все новые символы
                tmpStrArr.addAll(Arrays.stream(symbols).toList());

                String temporaryStr = "";

                for (int j = 0; j < tmpStrArr.size(); j++) {
                    temporaryStr += tmpStrArr.get(j);
                    String lastSymbol = tmpStrArr.get(j);

                    if (!dictionary.contains(temporaryStr)) {
                        dictionary.add(temporaryStr);

                        tmpStrArr.clear();
                        tmpStrArr.add(lastSymbol);
                    }
                }
            }
        }

        return String.join(voidRegex, resultStr);
    }

    public static int[] stringToNum(String[] binaryArr) {
        int[] res = new int[binaryArr.length];

        for (int i = 0; i < binaryArr.length; i++) {
            res[i] = Integer.parseInt(binaryArr[i], 2);
        }

        return res;
    }
}