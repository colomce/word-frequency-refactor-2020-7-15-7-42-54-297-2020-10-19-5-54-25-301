import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
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
        String[] words = sentence.split("\\s+");

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        Map<String, List<WordInfo>> wordInfoMap = getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;
        return wordInfoList;
    }

    private void sortWordInfoByWordCount(List<WordInfo> wordInfoList) {
        wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return map;
    }

    private String getWordInfoListLines(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining("\n"));
    }
}
