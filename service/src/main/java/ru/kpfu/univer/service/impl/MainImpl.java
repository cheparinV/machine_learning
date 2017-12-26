package ru.kpfu.univer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@Service
public class MainImpl implements MainService {

    private DictionaryService dictionaryService;

    private ProbabilityService probabilityService;

    private BayesService bayesService;

    private FisherService fisherService;

    @Autowired
    public MainImpl(DictionaryService dictionaryService, ProbabilityService probabilityService,
                    BayesService bayesService, FisherService fisherService) {
        this.dictionaryService = dictionaryService;
        this.probabilityService = probabilityService;
        this.bayesService = bayesService;
        this.fisherService = fisherService;
    }

    @Override
    public Map<String, String> countProbe(String string) {
        final List<String> strings = Arrays.asList(string.split("[,�_#;\":\\\\.!?\\s\\d+]+"));

        final HashMap<String, String> map = new HashMap<>();
        final List<String> bayes = bayesService.probForList(strings,
                probabilityService.getWordProb(),
                dictionaryService.getCategories());
        final List<String> fisher = fisherService.probForList(strings, probabilityService.getWordProb(), dictionaryService.getCategories());

        map.put("BayesGood", bayes.get(0));
        map.put("BayesBad", bayes.get(1));
        map.put("BayesResult", bayes.get(2));
        map.put("FisherGood", fisher.get(0));
        map.put("FisherBad", fisher.get(1));
        map.put("FisherResult", fisher.get(2));
        return map;
    }
}
