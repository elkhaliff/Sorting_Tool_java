package sorting;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class SortingSystem {
    public static final String LONG = "long";
    public static final String LINE = "line";
    public static final String WORD = "word";

    public static final String NATURAL = "natural";
    public static final String BY_COUNT = "byCount";

    private String sep;
    private String type;

    private final String dataType;
    private final String sortingType;

    private final String inputFile;
    private final String outputFile;

    private final List<String> list = new ArrayList<>();

    public SortingSystem(String dataType, String sortingType, String inputFile, String outputFile) {
        this.dataType = "".equals(dataType) ? WORD : dataType;
        this.sortingType = "".equals(sortingType) ? NATURAL : sortingType;
        this.inputFile = inputFile;
        this.outputFile = outputFile;

        sep = " ";

        if (LONG.equals(dataType))
            this.type = "numbers";
        else if (WORD.equals(dataType))
            this.type = "words";
        else if (LINE.equals(dataType)) {
            this.type = "lines";
            sep = "\n";
        }
    }

    public void getData() {
        if ("".equals(inputFile)) {
            scanData(new Scanner(System.in));
        } else {
            try (Scanner scanner = new Scanner(new File(inputFile))){
                scanData(scanner);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void scanData(Scanner scanner) {
        while (scanner.hasNext()) {
            var input = LINE.equals(dataType) ? scanner.nextLine() : scanner.next();
            if (LONG.equals(dataType)) {
                try {
                    Long test = Long.valueOf(input);
                    list.add(input);
                } catch (Exception e) {
                    System.out.printf("\"%s\" is not a long. It will be skipped.", input);
                }
            } else
                list.add(input);
        }
    }

    public void sort() {
        var size = list.size();

        list.sort(LONG.equals(dataType) ? Comparator.comparingLong(Long::parseLong) : Comparator.naturalOrder());

        print(String.format("Total %s: %d%n", type, size));

        if (NATURAL.equals(sortingType)) {
            print("Sorted data:");
            list.forEach(str -> print(String.format("%s%s", str, sep)));
        } else {
            List<String> sortedStrings = new ArrayList<>();
            list.forEach(str -> {if (!sortedStrings.contains(str)) {
                sortedStrings.add(str);}
            });
            sortedStrings.sort(Comparator.comparing(str -> Collections.frequency(list, str)));
            sortedStrings.forEach(str -> print(String.format("%s: %d time(s), %d%%%n", str,
                    Collections.frequency(list, str),
                    Collections.frequency(list, str) * 100 / size)));
        }
    }

    private void print(String str) {
        if ("".equals(outputFile))
            System.out.print(str);
        else {
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(str);
            } catch (Exception e) {
                System.out.println("File not found.");
            }
        }
    }
}
