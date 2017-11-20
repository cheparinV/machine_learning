package ru.kpfu.univer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.univer.service.CSVReader;
import ru.kpfu.univer.service.DictionaryService;
import ru.kpfu.univer.service.ProbabilityService;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;
import ru.kpfu.univer.webapp.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    @Test
    public void generateDictionaryTest(){
        final FileInputStream stream;
        try {
            stream = new FileInputStream("C:\\Users\\Vladislav\\Documents\\spam.csv");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        csvReader.readFile(stream);
        dictionaryService.generateDictionary();
        final List<Category> categories = dictionaryService.getCategories();
        final FeatureSet featureSet = dictionaryService.getFeatureSet();

        probabilityService.printWordsProbe(categories, featureSet);
        System.out.println();
    }
}
