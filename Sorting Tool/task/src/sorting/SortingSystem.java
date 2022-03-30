package sorting;

import java.util.*;

public class SortingSystem {
    final String LONG = "long";
    final String LINE = "line";
    final String WORD = "word";

    final String NATURAL = "natural";
//    final String BY_COUNT = "byCount";

    private String sep;
    private String type;

    private final String dataType;
    private final String sortingType;

    private final List<String> list = new ArrayList<>();

    public SortingSystem(String dataType, String sortingType) {
        this.dataType = "".equals(dataType) ? WORD : dataType;
        this.sortingType = "".equals(sortingType) ? NATURAL : sortingType;
        sep = " ";

        if (LONG.equals(dataType))
            type = "numbers";
        else if (WORD.equals(dataType))
            type = "words";
        else if (LINE.equals(dataType)) {
            type = "lines";
            sep = "\n";
        }
    }

    public void getData() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            list.add(LINE.equals(dataType) ? scanner.nextLine() : scanner.next());
        }

    }

    public void sort() {
        var size = list.size();

        list.sort(LONG.equals(dataType) ? Comparator.comparingLong(Long::parseLong) : Comparator.naturalOrder());

        System.out.printf("Total %s: %d%n", type, size);

        if (NATURAL.equals(sortingType)) {
            System.out.print("Sorted data:");
            list.forEach(str -> System.out.printf("%s%s", str, sep));
        } else {
            List<String> sortedStrings = new ArrayList<>();
            list.forEach(str -> {if (!sortedStrings.contains(str)) {
                sortedStrings.add(str);}
            });
            sortedStrings.sort(Comparator.comparing(str -> Collections.frequency(list, str)));
            sortedStrings.forEach(str -> System.out.printf("%s: %d time(s), %d%%%n", str,
                    Collections.frequency(list, str),
                    Collections.frequency(list, str) * 100 / size));
        }
    }
}
