package sorting;

public class Main {
    public static void main(final String[] args) {
        var typeSort = "word";
        var sortIntegers = false;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if ("-dataType".equals(args[i])) {
                    typeSort = args[i+1];
                }
                if ("-sortIntegers".equals(args[i])) {
                    sortIntegers = true;
                }
            }
        }
        SortingSystem.sort(typeSort, sortIntegers);
    }
}