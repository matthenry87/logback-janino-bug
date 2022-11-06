package org.matthenry87.logbackjaninobugdemo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.rolling.RollingFileAppender;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

@SpringBootApplication
public class LogbackJaninoBugApplication {

    public static void main(String[] args) {

        SpringApplication.run(LogbackJaninoBugApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {

        return args -> {

            Logger root = (Logger) org.slf4j.LoggerFactory.getLogger(ROOT_LOGGER_NAME);

            RollingFileAppender fileAppender = (RollingFileAppender) root.getAppender("file_appender");

            if (fileAppender == null) {

                throw new RuntimeException("File appender should not be null.");
            }

            var log = LoggerFactory.getLogger(this.getClass());

            log.info("test 123");
        };
    }

}
