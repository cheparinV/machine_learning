package ru.kpfu.univer.service.models;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class WordProbability {

    private String word;
    private Double probGood;
    private Double probWGood;
    private Integer countGoodWord;
    private Integer countWord;
    private Integer countGood;

    public String getWord() {
        return word;
    }

    public Double getProbGood() {
        return probGood;
    }

    @Override
    public String toString() {
        return "WordProbability{" +
                "word='" + word + '\'' +
                ", probGood=" + probGood +
                ", probWGood=" + probWGood +
                ", countGoodWord=" + countGoodWord +
                ", countWord=" + countWord +
                ", countGood=" + countGood +
                '}';
    }

    public static final class WordProbabilityBuilder {
        private String word;
        private Double probGood;
        private Double probWGood;
        private Integer countGoodWord;
        private Integer countWord;
        private Integer countGood;

        private WordProbabilityBuilder() {
        }

        public static WordProbabilityBuilder aWordProbability() {
            return new WordProbabilityBuilder();
        }

        public WordProbabilityBuilder withWord(String word) {
            this.word = word;
            return this;
        }

        public WordProbabilityBuilder withProbGood(Double probGood) {
            this.probGood = probGood;
            return this;
        }

        public WordProbabilityBuilder withProbWGood(Double probWGood) {
            this.probWGood = probWGood;
            return this;
        }

        public WordProbabilityBuilder withCountGoodWord(Integer countGoodWord) {
            this.countGoodWord = countGoodWord;
            return this;
        }

        public WordProbabilityBuilder withCountWord(Integer countWord) {
            this.countWord = countWord;
            return this;
        }

        public WordProbabilityBuilder withCountGood(Integer countGood) {
            this.countGood = countGood;
            return this;
        }

        public WordProbability build() {
            WordProbability wordProbability = new WordProbability();
            wordProbability.probWGood = this.probWGood;
            wordProbability.probGood = this.probGood;
            wordProbability.countGoodWord = this.countGoodWord;
            wordProbability.countGood = this.countGood;
            wordProbability.countWord = this.countWord;
            wordProbability.word = this.word;
            return wordProbability;
        }
    }
}
