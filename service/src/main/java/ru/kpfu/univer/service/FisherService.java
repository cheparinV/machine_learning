package ru.kpfu.univer.service;

import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface FisherService {

    List<String> probForList(List<String> words, List<WordProbability> wordProbabilitiesm, List<Category> categories);
}
