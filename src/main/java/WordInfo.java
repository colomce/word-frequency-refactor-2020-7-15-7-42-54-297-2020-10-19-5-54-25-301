class WordInfo {
    private String word;
    private int count;

    WordInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    String getWord() {
        return this.word;
    }

    int getWordCount() {
        return this.count;
    }
}
