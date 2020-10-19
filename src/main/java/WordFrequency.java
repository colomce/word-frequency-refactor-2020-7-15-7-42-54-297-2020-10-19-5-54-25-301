import static java.lang.String.format;

class WordFrequency {
    private String word;
    private int wordCount;

    WordFrequency(String word, int wordCount) {
        this.word = word;
        this.wordCount = wordCount;
    }

    String getWord() {
        return this.word;
    }

    int getWordCount() {
        return this.wordCount;
    }

    String getWordFrequencyLine(){
        return format("%s %d", word, wordCount);
    }
}
