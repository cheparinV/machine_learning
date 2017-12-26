package ru.kpfu.univer.webapp.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.univer.service.*;
import ru.kpfu.univer.service.models.Category;
import ru.kpfu.univer.service.models.FeatureSet;
import ru.kpfu.univer.service.models.WordProbability;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
@RequestMapping("/main")
public class MainResource {

    private CSVReader csvReader;
    private DictionaryService dictionaryService;
    private ProbabilityService probabilityService;
    private BayesService bayesService;
    private FisherService fisherService;
    private MainService mainService;


    @Autowired
    public MainResource(CSVReader csvReader, DictionaryService dictionaryService,
                        ProbabilityService probabilityService, BayesService bayesService,
                        FisherService fisherService, MainService mainService) {
        this.csvReader = csvReader;
        this.dictionaryService = dictionaryService;
        this.probabilityService = probabilityService;
        this.bayesService = bayesService;
        this.fisherService = fisherService;
        this.mainService = mainService;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            csvReader.readFile(file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return "redirect:/start";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public List<WordProbability> startLearn() {
        dictionaryService.generateDictionary();
        final List<Category> categories = dictionaryService.getCategories();
        final FeatureSet featureSet = dictionaryService.getFeatureSet();
        probabilityService.countWordsProbe(categories, featureSet);
        return probabilityService.getWordProb();
    }

    @RequestMapping(value = "/word", method = RequestMethod.PUT)
    public Map<String, String> countProbe(@RequestParam String string) {
        return mainService.countProbe(string);
    }


}
