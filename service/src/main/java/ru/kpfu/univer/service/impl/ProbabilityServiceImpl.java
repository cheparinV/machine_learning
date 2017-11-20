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
    public void printWordsProbe(List<Category> categories, FeatureSet featureSet) {
        words = new ArrayList<>();
        for (Category category : categories) {
            if (category.getName().equals("spam")) {
                continue;
            }
            final List<Feature> features = featureSet.getFeatures();
            for (Feature feature : features) {
                final Map<Category, Integer> map = feature.getMap();
                final Integer countInGood = map.get(category);
                final Double probGood = (double) countInGood / category.getCount();
                final Double probWGood = (w * coef + countInGood * probGood) / (countInGood + w);
                words.add(
                WordProbability.WordProbabilityBuilder.aWordProbability()
                        .withWord(feature.getFeature())
                        .withProbGood(probGood)
                        .withProbWGood(probWGood)
                        .withCountGoodWord(countInGood)
                        .withCountWord(feature.getAllCount())
                        .withCountGood(category.getCount())
                        .build()
                );

            }
        }
        for (WordProbability word : words) {
            System.out.println(word);
        }
    }
}
