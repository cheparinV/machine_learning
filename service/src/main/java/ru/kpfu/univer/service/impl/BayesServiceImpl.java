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



    @Override
    public void probForList(List<String> words, List<WordProbability> wordProbabilities, List<Category> categories) {

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
        good[1] = good[0] * (double) 1 / categories.size();
        bad[1] = bad[0] * (double) 1 / categories.size();
        System.out.println(Arrays.toString(good));
        System.out.println(Arrays.toString(bad));
    }
}
