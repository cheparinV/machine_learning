package ru.kpfu.univer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.univer.service.impl.CSVReaderImpl;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Configuration
@ComponentScan(basePackageClasses = {CSVReaderImpl.class})
public class ServiceConfig {
}
