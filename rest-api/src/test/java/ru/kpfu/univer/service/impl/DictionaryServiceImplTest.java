package ru.kpfu.univer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.univer.service.*;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;
import ru.kpfu.univer.service.models.WordProbability;
import ru.kpfu.univer.webapp.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DictionaryServiceImplTest {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    CSVReader csvReader;

    @Autowired
    ProbabilityService probabilityService;

    @Autowired
    BayesService bayesService;

    @Autowired
    FisherService fisherService;

    @Test
    public void generateDictionaryTest(){
        final FileInputStream stream;
        try {
            stream = new FileInputStream("C:\\Users\\ASUS-PC\\Downloads\\spam (1).csv");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        csvReader.readFile(stream);
        dictionaryService.generateDictionary();
        final List<Category> categories = dictionaryService.getCategories();
        final FeatureSet featureSet = dictionaryService.getFeatureSet();

        probabilityService.countWordsProbe(categories, featureSet);
        final List<WordProbability> wordProb = probabilityService.getWordProb();
        final List<String> strings = Arrays.asList("Want", "Ring", "explicit ");
        bayesService.probForList(strings, wordProb, categories);
        fisherService.probForList(strings, wordProb, categories);

        System.out.println();
    }
}
