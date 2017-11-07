package ru.kpfu.univer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.univer.service.CSVReader;
import ru.kpfu.univer.webapp.Application;

import java.io.FileInputStream;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CSVReaderImplTest {

    @Autowired
    CSVReader csvReader;

    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testReadFile() throws Exception {
       // CSVReader csvReader = new CSVReaderImpl();
        final FileInputStream stream = new FileInputStream("C:\\Users\\Vladislav\\Documents\\spam.csv");
        csvReader.readFile(stream);
    }

}
