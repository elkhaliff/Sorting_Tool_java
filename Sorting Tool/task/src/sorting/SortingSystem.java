package sorting;

import java.util.*;
import java.util.stream.Collectors;

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

    private List list;
    private Map map;

    public SortingSystem(String dataType, String sortingType) {
        this.dataType = "".equals(dataType) ? WORD : dataType;
        this.sortingType = "".equals(sortingType) ? NATURAL : sortingType;
        sep = " ";
        Scanner scanner = new Scanner(System.in);

        switch (this.dataType) {
            case LONG: {
                type = "numbers";
                list = new ArrayList<Long>();
                map = new LinkedHashMap<Long, Integer>();
                while (scanner.hasNextLong()) {
                    long number = scanner.nextLong();
                    list.add(number);
                }
                break;
            }
            case LINE:
            case WORD: {
                list = new ArrayList<String>();
                map = new LinkedHashMap<String, Integer>();
                if (LINE.equals(dataType)) {
                    type = "lines";
                    sep = "\n";
                    while (scanner.hasNextLine())
                        list.add(scanner.nextLine());
                } else if (WORD.equals(dataType)) {
                    type = "words";
                    while (scanner.hasNext()) {
                        list.add(scanner.next());
                    }
                }
                break;
            }
        }
    }

    private static Map<Long, Integer> sortedMapLong(Map<Long, Integer> map) {
        Map sorted = new LinkedHashMap<Long, Integer>();
        var sortedValue = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        var sortedKey = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        for (var values: sortedValue.entrySet()) {
            for (var keys: sortedKey.entrySet()) {
                if (values.getValue() == keys.getValue()) {
                    sorted.put(keys.getKey(), keys.getValue());
                    sortedKey.remove(keys.getKey());
                    break;
                }
            }
        }
        return sorted;
    }

    private static Map<String, Integer> sortedMapString(Map<String, Integer> map) {
        Map sorted = new LinkedHashMap<String, Integer>();
        var sortedValue = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        var sortedKey = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        for (var values: sortedValue.entrySet()) {
            for (var keys: sortedKey.entrySet()) {
                if (values.getValue() == keys.getValue()) {
                    sorted.put(keys.getKey(), keys.getValue());
                    sortedKey.remove(keys.getKey());
                    break;
                }
            }
        }
        return sorted;
    }

    public void sort() {
        var outStr = "";
        var size = list.size();

        System.out.printf("Total %s: %d%n", type, size);

        if (NATURAL.equals(sortingType)) {
            list.sort(Comparator.naturalOrder());
            outStr = list.toString().replaceAll(", ", " ");
            outStr = outStr.substring(1, outStr.length() - 1);
            outStr = outStr.replaceAll(" ", sep);

            System.out.printf("Sorted data: %s%n", outStr);
        } else {
            for (Object o : list) {
                int count;
                if (LONG.equals(dataType)) {
                    long curr = (long) o;
                    count = map.containsKey(curr) ? (int)map.get(curr) + 1 : 1;
                    map.put(curr, count);
                } else {
                    String curr = String.valueOf(o);
                    count = map.containsKey(curr) ? (int)map.get(curr) + 1 : 1;
                    map.put(curr, count);
                }
            }
            if (LONG.equals(dataType)) {
                map = sortedMapLong(map);
            } else {
                map = sortedMapString(map);
            }

            Map<String, Integer> sorted = new LinkedHashMap(map);

            for (var entry: sorted.entrySet()) {
                int percent = (int) (((double) entry.getValue() / (double) size) * 100);
                String perc = new StringBuilder().append(percent).append("%").toString();
                System.out.printf("%s: %d time(s), %s %n", entry.getKey(), entry.getValue(), perc);
            }
        }
    }
}
