package ru.kpfu.univer.service;

import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface ProbabilityService {

    void countWordsProbe(List<Category> categories, FeatureSet featureSet);

    List<WordProbability> getWordProb();

}
