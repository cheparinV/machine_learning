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

        final HashMap<Category, Integer> categoryMap = new HashMap<>();
        categoryMap.put(hamCategory, 0);
        categoryMap.put(spamCategory, 0);

        final Pattern pattern = Pattern.compile("\\b(\\w*)\\b");
        this.featureSet = new FeatureSet(new ArrayList<>());
        for (String string : ham) {
            final String[] split = string.split("[,�_#;\":\\\\.!?\\s+]+");
            for (String s : split) {
                final Optional<Feature> featureOpt = this.featureSet.findFeature(s);
                Feature feature;
                if (!featureOpt.isPresent()) {
                    feature = new Feature(s, categoryMap);
                    feature.incrementCategory(hamCategory);
                } else {
                    featureOpt.get().incrementCategory(hamCategory);
                    feature = featureOpt.get();
                }
                this.featureSet.addFeature(feature);
            }
        }
    }

    public FeatureSet getFeatureSet() {
        return featureSet;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
