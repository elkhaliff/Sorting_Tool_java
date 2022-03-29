package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SortingSystem {
    static void sort(String typeSort, boolean sortIntegers) {
        var max = "";
        int percent;
        var sep = " ";
        var count = 0;
        var size = 0;

        final var LONG = "long";
        final var LINE = "line";
        final var WORD = "word";

        if (sortIntegers) typeSort = LONG;

        Scanner scanner = new Scanner(System.in);
        switch (typeSort) {
            case LONG: {
                var list = new ArrayList<Long>();
                var max_ = Long.MIN_VALUE;

                while (scanner.hasNextLong()) {
                    long number = scanner.nextLong();
                    list.add(number);
                    if (max_ == number) count++;
                    else if (max_ < number) {
                        max_ = number;
                        count = 1;
                    }
                }
                if (sortIntegers) {
                    list.sort(Comparator.naturalOrder());
                    String tmp = list.toString().replaceAll(",", "");
                    max = tmp.substring(1, tmp.length() - 1);
                } else
                    max = String.valueOf(max_);
                size = list.size();
                break;
            }
            case LINE:
            case WORD: {
                var list = new ArrayList<String>();
                if (LINE.equals(typeSort)) {
                    sep ="\n";
                    while (scanner.hasNextLine())
                        list.add(scanner.nextLine());
                } else {
                    while (scanner.hasNext()) {
                        list.add(scanner.next());
                    }
                }
                for (String s: list) {
                    if (s.length() == max.length()) count++;
                    else if (s.length() > max.length()) {
                        max = s;
                        count = 1;
                    }
                }
                size = list.size();
                break;
            }
        }

        System.out.printf("Total numbers: %d%n", size);

        if (sortIntegers) {
            System.out.printf("Sorted data: %s%n", max);
        } else {
            percent = (int) (((double) count / (double) size) * 100);
            System.out.printf("The greatest number:%s%s%s(%d time(s)), %d).%n", sep, max, sep, count, percent);
        }
    }
}