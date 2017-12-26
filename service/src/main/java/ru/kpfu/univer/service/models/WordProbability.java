package ru.kpfu.univer.service.models;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class WordProbability {

    private String word;
    private Double probGood;
    private Double probWGood;
    private Double probBad;
    private Double probWBad;
    private Integer countGoodWord;
    private Integer countWord;
    private Integer countGood;
    private Double fishProbGood;
    private Double fishProbBad;

    public String getWord() {
        return word;
    }

    public Double getProbGood() {
        return probGood;
    }

    public Double getProbWGood() {
        return probWGood;
    }

    public WordProbability setWord(String word) {
        this.word = word;
        return this;
    }

    public WordProbability setProbGood(Double probGood) {
        this.probGood = probGood;
        return this;
    }

    public WordProbability setProbWGood(Double probWGood) {
        this.probWGood = probWGood;
        return this;
    }

    public Double getProbBad() {
        return probBad;
    }

    public WordProbability setProbBad(Double probBad) {
        this.probBad = probBad;
        return this;
    }

    public Double getProbWBad() {
        return probWBad;
    }

    public WordProbability setProbWBad(Double probWBad) {
        this.probWBad = probWBad;
        return this;
    }

    public Integer getCountGoodWord() {
        return countGoodWord;
    }

    public WordProbability setCountGoodWord(Integer countGoodWord) {
        this.countGoodWord = countGoodWord;
        return this;
    }

    public Integer getCountWord() {
        return countWord;
    }

    public WordProbability setCountWord(Integer countWord) {
        this.countWord = countWord;
        return this;
    }

    public Integer getCountGood() {
        return countGood;
    }

    public WordProbability setCountGood(Integer countGood) {
        this.countGood = countGood;
        return this;
    }

    public Double getFishProbGood() {
        return fishProbGood;
    }

    public WordProbability setFishProbGood(Double fishProbGood) {
        this.fishProbGood = fishProbGood;
        return this;
    }

    public Double getFishProbBad() {
        return fishProbBad;
    }

    public WordProbability setFishProbBad(Double fishProbBad) {
        this.fishProbBad = fishProbBad;
        return this;
    }

    @Override
    public String toString() {
        return "WordProbability{" +
                "word='" + word + '\'' +
                ", probGood=" + probGood +
                ", probWGood=" + probWGood +
                ", probBad=" + probBad +
                ", probWBad=" + probWBad +
                ", countGoodWord=" + countGoodWord +
                ", countWord=" + countWord +
                ", countGood=" + countGood +
                ", fishProbGood=" + fishProbGood +
                ", fishProbBad=" + fishProbBad +
                '}';
    }
}
