import java.util.HashSet;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.frequency;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WordFrequencyGame {

    public String getResult(String sentence) {
        try {
            validateInput(sentence);
            List<WordFrequency> wordFrequencyList = computeWordFrequency(sentence);
            sortWordInfoByWordCount(wordFrequencyList);
            return getWordInfoListLines(wordFrequencyList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private void validateInput(String sentence) {
        if ("".equals(sentence)) {
            throw new InvalidInputException();
        }
    }

    private List<WordFrequency> computeWordFrequency(String sentence) {
        List<String> words = asList(sentence.split("\\s+"));
        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
                .map(word -> new WordFrequency(word, frequency(words, word)))
                .collect(toList());
    }

    private void sortWordInfoByWordCount(List<WordFrequency> wordFrequencyList) {
        wordFrequencyList.sort(reverseOrder(comparingInt(WordFrequency::getWordCount)));
    }

    private String getWordInfoListLines(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .map(WordFrequency::getWordFrequencyLine)
                .collect(joining("\n"));
    }
}
