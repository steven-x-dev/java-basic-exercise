import java.util.*;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码

        // Check if two adjacent commas exist, or String.split(",") will have empty String elements
        if (firstWordList.contains(",,") || secondWordList.contains(",,"))
            throw new RuntimeException("input not valid");

        List<String> list1 = Arrays.asList(firstWordList.toUpperCase().split(","));

        // Use HashSet to store secondWordList for higher querying efficiency
        Set<String> set2 = new HashSet<>(Arrays.asList(secondWordList.toUpperCase().split(",")));

        List<String> commonWords = new ArrayList<>();

        /*
         * Validate each word in firstWordList. If word is valid, add it to commonWords
         * if secondWordList contains it as well, and commonWords doesn't
         */
        list1.stream().forEach(w -> {
            validateWord(w);
            if (set2.contains(w) && !commonWords.contains(w)) commonWords.add(w);
        });

        // Words in secondWordList hasn't be validated yet, so validate them here
        set2.stream().forEach(GrammarExercise::validateWord);

        // Sort the string list using Comparator.naturalOrder()
        commonWords.sort(Comparator.naturalOrder());

        // Convert the result list to the required format
        return getResult(commonWords);
    }

    private static void validateWord(String word) {
        for (char c : word.toCharArray())
            if (!(c > 64 && c < 91)) throw new RuntimeException("input not valid");
    }

    private static List<String> getResult(List<String> commonWords) {

        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        commonWords.stream().forEach(w -> {

            if (w == null || w.length() == 0) return;

            for (char c : w.toCharArray()) {
                sb.append(c);
                sb.append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);

            result.add(sb.toString());
            sb.setLength(0);
        });

        return result;
    }
}
