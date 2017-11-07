package ru.kpfu.univer.webapp;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;
import ru.kpfu.univer.ServiceConfig;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Import({
        ServiceConfig.class
})
@SpringBootApplication
public class Application {

    protected Application() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .sources(Application.class)
                .listeners(new ApplicationPidFileWriter())
                .run(args);
    }
}
