package sorting;

public class Main {
    public static void main(final String[] args) {
        var dataType = "";
        var sortingType = "";

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if ("-dataType".equals(args[i])) {
                        dataType = args[++i];
                }
                if ("-sortingType".equals(args[i])) {
                    sortingType = args[++i];
                }
            }
        }
        SortingSystem sortSys = new SortingSystem(dataType, sortingType);
        sortSys.getData();
        sortSys.sort();
    }
}
