package sorting;

public class Main {
    public static void main(final String[] args) {
        var dataType = "";
        var sortingType = "";
        var inputFile = "";
        var outputFile = "";
        final String errData = "No data type defined!";
        final String errSort = "No sorting type defined!";

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].contains("-"))
                    switch (args[i]) {
                        case "-dataType": {
                            try {
                                dataType = args[++i];
                                if (!(SortingSystem.LONG.equals(dataType) ||
                                        SortingSystem.LINE.equals(dataType) ||
                                        SortingSystem.WORD.equals(dataType)))
                                    throw new Exception("");
                            } catch (Exception e) {
                                System.out.println(errData);
                                return;
                            }
                            break;
                        }
                        case "-sortingType": {
                            try {
                                sortingType = args[++i];
                                if (!(SortingSystem.NATURAL.equals(sortingType) ||
                                        SortingSystem.BY_COUNT.equals(sortingType)))
                                    throw new Exception("");
                            } catch (Exception e) {
                                System.out.println(errSort);
                                return;
                            }
                            break;
                        }
                        case "-inputFile": {
                            inputFile = args[++i];
                            break;
                        }
                        case "-outputFile": {
                            outputFile = args[++i];
                            break;
                        }
                        default: {
                            System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n", args[i]);
                        }
                    }
            }
        }
        SortingSystem sortSys = new SortingSystem(dataType, sortingType, inputFile, outputFile);
        sortSys.getData();
        sortSys.sort();
    }
}
