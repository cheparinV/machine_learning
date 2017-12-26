package ru.kpfu.univer.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.FisherService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.WordProbability;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class FisherServiceImpl implements FisherService {

    @Value("${common.fisherCoef}")
    private Double fisherCoef;

    @Override
    public List<String> probForList(List<String> words, List<WordProbability> wordProbabilities, List<Category> categories) {
        final List<WordProbability> list = wordProbabilities.stream().filter(word -> words.contains(word.getWord()))
                .collect(Collectors.toList());

        final Double[] good = {1.0, 1.0};
        final Double[] bad = {1.0, 1.0};
        list.forEach(
                word -> {
                    good[0] *= word.getFishProbGood();
                    bad[0] = bad[0] * word.getFishProbBad();
                }
        );
        final double goodRes = this.chiInv(-2 * Math.log(good[0]), list.size());
        final double badRes = this.chiInv(-2 * Math.log(bad[0]), list.size());
        String result = "not enough";
        if (goodRes > badRes && goodRes > fisherCoef) {
            result = "good";
        } else {
            if (badRes > fisherCoef)
            result = "bad";
        }
        final List<String> strings = Arrays.asList(
                String.valueOf(goodRes),
                String.valueOf(badRes),
                result
        );
        System.out.println(Arrays.toString(strings.toArray()));
        return strings;
    }

    private double chiInv(double chi, int df) {
        double m = chi / 2;
        double term = Math.exp(-m);
        double sum = Math.exp(-m);

        for (int i = 1; i <= df; ++i) {
            term *= m / i;
            sum += term;
        }

        return Math.min(sum, 1.0);
    }
}
