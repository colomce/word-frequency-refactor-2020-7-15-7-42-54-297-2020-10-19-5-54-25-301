import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.frequency;

public class WordFrequencyGame {

    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = computeWordFrequency(sentence);
            sortWordInfoByWordCount(wordInfoList);
            return getWordInfoListLines(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> computeWordFrequency(String sentence) {
        List<String> words = asList(sentence.split(WHITE_SPACE));
        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
                .map(word -> new WordInfo(word, frequency(words, word)))
                .collect(Collectors.toList());
    }

    private void sortWordInfoByWordCount(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private String getWordInfoListLines(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining("\n"));
    }
}
