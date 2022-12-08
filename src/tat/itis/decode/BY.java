package tat.itis.decode;

import java.util.Arrays;

public class BY {
    public static String[] decode(String[] firstValues) {
        String[] resArr = new String[firstValues.length];

        for (int i = 0; i < firstValues.length; i++) {
            sort(add(resArr, firstValues));
        }
        return resArr;
    }

    public static String[] add(String[] arr, String[] firstValues) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = firstValues[i] + (arr[i] == null ? "" : arr[i]);
        }
        return arr;
    }

    public static String[] sort(String[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}
