package ru.kpfu.univer.service.impl;

import ru.kpfu.univer.service.BayesService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class BayesSeviceImpl implements BayesService {
    @Override
    public void probForList(List<String> words, List<WordProbability> wordProbabilities, List<Category> categories) {

        final List<WordProbability> list = wordProbabilities.stream().filter(word -> words.contains(word.getWord()))
                .collect(Collectors.toList());

        final Double[] doub = {0.0};
        list.forEach(
                word -> {
                    doub[0] = doub[0] * word.getProbGood();
                }
        );

    }
}
