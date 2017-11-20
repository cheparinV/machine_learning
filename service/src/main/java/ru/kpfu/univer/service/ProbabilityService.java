package ru.kpfu.univer.service;

import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;

import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface ProbabilityService {

    void printWordsProbe(List<Category> categories, FeatureSet featureSet);

}
