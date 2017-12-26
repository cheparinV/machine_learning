package ru.kpfu.univer.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.ProbabilityService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.Feature;
import ru.kpfu.univer.service.models.FeatureSet;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class ProbabilityServiceImpl implements ProbabilityService {

    private final static Double coef = 0.5;
    private final static Integer w = 1;
    private List<WordProbability> words;

    @Override
    public void countWordsProbe(List<Category> categories, FeatureSet featureSet) {
        words = new ArrayList<>();
        final List<Feature> features = featureSet.getFeatures();
        for (Feature feature : features) {
            words.add(this.probByFeature(feature, categories));
        }
        for (WordProbability word : words) {
            System.out.println(word);
        }
    }

    public WordProbability probByFeature(Feature feature, List<Category> categories) {
        final Category spam = categories.stream().filter(category -> category.getName().equals("spam")).findFirst().get();
        final Category ham = categories.stream().filter(category -> category.getName().equals("ham")).findFirst().get();
        final Map<Category, Integer> map = feature.getMap();
        final Integer countInGood = map.get(ham);
        final Integer countInBad = map.get(spam);
        final Double probGood = (double) countInGood / ham.getCount();
        final Double probWGood = (w * coef + countInGood * probGood) / ((double) countInGood + w);
        final Double probBad = (double) countInBad / spam.getCount();
        final Double probWBad = (w * coef + countInBad * probBad) / ((double) countInBad + w);
        final int allCount = ham.getCount() + spam.getCount();
        final double fishGood = (double) countInGood / allCount;
        final double fishBad = (double) countInBad / allCount;

        return new WordProbability()
                .setWord(feature.getFeature())
                .setProbGood(probGood)
                .setProbWGood(probWGood)
                .setProbBad(probBad)
                .setProbWBad(probWBad)
                .setCountGoodWord(countInGood)
                .setCountWord(feature.getAllCount())
                .setCountGood(ham.getCount())
                .setFishProbGood(fishGood)
                .setFishProbBad(fishBad);
    }

    @Override
    public List<WordProbability> getWordProb() {
        return words;
    }
}
