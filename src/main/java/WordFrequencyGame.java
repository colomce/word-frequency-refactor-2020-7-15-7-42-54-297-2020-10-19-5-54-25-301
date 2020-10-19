import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class WordFrequencyGame {

    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {
        if (sentence.split(WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = computeWordFrequency(sentence);
                sortWordInfoByWordCount(wordInfoList);
                return getWordInfoListLines(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> computeWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int frequency = Collections.frequency(words, word);
            WordInfo wordInfo = new WordInfo(word, frequency);
            wordInfos.add(wordInfo);
        }
        return wordInfos;
    }

    private void sortWordInfoByWordCount(List<WordInfo> wordInfoList) {
        wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private String getWordInfoListLines(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining("\n"));
    }
}
