package ru.kpfu.univer.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.univer.service.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class CSVReaderImpl implements CSVReader {

    @Value("${common.count}")
    private Integer count;

    private Set<String> spam;
    private Set<String> ham;

    @Override
    public void readFile(InputStream inputStream) {
        final Set<String> ham = new HashSet<>();
        final Set<String> spam = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final CSVParser records = CSVFormat.RFC4180.withHeader().withDelimiter(';').parse(reader);
            for (CSVRecord record : records) {
                if (record.size() < 2) {
                    continue;
                }
                if (record.get(0).equals("ham") && ham.size() <= count) {
                    ham.add(record.get(1));
                }
                if (record.get(0).equals("spam") && spam.size() <= count) {
                    spam.add(record.get(1));
                }
                if (spam.size() >= count && ham.size() >= count) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.ham = ham;
        this.spam = spam;
    }

    @Override
    public Set<String> getSpamMessages() {
        return this.spam;
    }

    @Override
    public Set<String> getHamMessages() {
        return this.ham;
    }
}
