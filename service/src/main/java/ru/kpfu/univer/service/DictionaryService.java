package ru.kpfu.univer.service;

import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;

import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface DictionaryService {

    public void generateDictionary();

    public FeatureSet getFeatureSet();

    public List<Category> getCategories();
}
