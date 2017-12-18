package ru.kpfu.univer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.CSVReader;
import ru.kpfu.univer.service.DictionaryService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.Feature;
import ru.kpfu.univer.service.models.FeatureSet;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final CSVReader csvReader;
    private FeatureSet featureSet;
    private List<Category> categories;

    @Autowired
    public DictionaryServiceImpl(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public void generateDictionary() {
        final Set<String> ham = csvReader.getHamMessages();
        final Set<String> spam = csvReader.getSpamMessages();

        final Category hamCategory = new Category("ham", ham.size());
        final Category spamCategory = new Category("spam", spam.size());
        this.categories = new ArrayList<>();
        this.categories.add(hamCategory);
        this.categories.add(spamCategory);

        final Map<Category, Integer> categoryMap = new HashMap<>();
        categoryMap.put(hamCategory, 0);
        categoryMap.put(spamCategory, 0);

        final Pattern pattern = Pattern.compile("\\b(\\w*)\\b");
        final ArrayList<Set<String>> sets = new ArrayList<>();
        sets.add(ham);
        sets.add(spam);
        this.featureSet = this.iterString(sets, categoryMap, hamCategory, spamCategory);

    }

    private FeatureSet iterString(List<Set<String>> categories, Map<Category, Integer> categoryMap, Category hamCategory,  Category spamCategory) {
        final FeatureSet featureSet = new FeatureSet(new ArrayList<>());
        Category current = hamCategory;
        for (Set<String> strings : categories) {
            for (String string : strings) {
                final String[] split = string.split("[,�_#;\":\\\\.!?\\s\\d+]+");
                for (String s : split) {
                    final Optional<Feature> featureOpt = featureSet.findFeature(s);
                    Feature feature;
                    if (!featureOpt.isPresent()) {
                        feature = new Feature(s, categoryMap);
                        feature.incrementCategory(current);
                    } else {
                        featureOpt.get().incrementCategory(current);
                        feature = featureOpt.get();
                    }
                    featureSet.addFeature(feature);
                }
            }
            current = spamCategory;
        }
        return featureSet;
    }

    public FeatureSet getFeatureSet() {
        return featureSet;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
