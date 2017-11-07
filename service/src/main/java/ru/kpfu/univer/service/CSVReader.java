package ru.kpfu.univer.service;

import java.io.InputStream;
import java.util.Set;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface CSVReader {

    void readFile(InputStream inputStream);

    Set<String> getSpamMessages();

    Set<String> getHamMessages();



}
