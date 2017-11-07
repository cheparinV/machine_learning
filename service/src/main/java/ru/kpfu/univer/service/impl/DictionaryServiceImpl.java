package ru.kpfu.univer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.CSVReader;
import ru.kpfu.univer.service.DictionaryService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.Feature;
import ru.kpfu.univer.service.models.FeatureSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final CSVReader csvReader;

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
        final HashMap<Category, Integer> categoryMap = new HashMap<>();
        categoryMap.put(hamCategory, 0);
        categoryMap.put(spamCategory, 0);

        final Pattern pattern = Pattern.compile("\\b(\\w*)\\b");
        final FeatureSet set = new FeatureSet(new ArrayList<>());
        for (String string : ham) {
            final String[] split = string.split("[,�_#;\":\\\\.!?\\s+]+");
            for (String s : split) {
                final Optional<Feature> featureOpt = set.findFeature(s);
                Feature feature;
                if (!featureOpt.isPresent()) {
                    feature = new Feature(s, categoryMap);
                    feature.incrementCategory(hamCategory);
                } else {
                    featureOpt.get().incrementCategory(hamCategory);
                    feature = featureOpt.get();
                }
                set.addFeature(feature);
            }
        }
    }
}
