package ru.kpfu.univer.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.BayesService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class BayesServiceImpl implements BayesService {


    private Integer bayesCoef = 1;

    @Override
    public List<String> probForList(List<String> words, List<WordProbability> wordProbabilities, List<Category> categories) {

        final List<WordProbability> list = wordProbabilities.stream().filter(word -> words.contains(word.getWord()))
                .collect(Collectors.toList());

        final Double[] good = {1.0, 1.0};
        final Double[] bad = {1.0, 1.0};
        list.forEach(
                word -> {
                    good[0] = good[0] * word.getProbWGood();
                    bad[0] = bad[0] * word.getProbWBad();
                }
        );
        good[1] = good[0] * ((double) 1 / categories.size());
        bad[1] = bad[0] * ((double) 1 / categories.size());
        String result = "good";
        if (good[0] > bad[0]) {
            if (good[0] > bad[0] * bayesCoef)
                result = "good";
            else result = "not enough";
        } else {
            if (bad[0] > good[0]*bayesCoef)
                result = "bad";
            else result = "not enough";
        }
        final List<String> strings = Arrays.asList(
                Arrays.toString(good), Arrays.toString(bad), result);
        System.out.println(Arrays.toString(strings.toArray()));
        return strings;
    }
}
