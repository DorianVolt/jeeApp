package myapp;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
public class LoggerClass implements ILogger {

    @PostConstruct
    public void start() {
        System.err.printf("Start %s\n", this);
    }

    @PreDestroy
    public void stop() {
        System.err.printf("Stop %s\n", this);
    }

    @Override
    public void log(String message) {
        System.err.printf("%tF %1$tR | %s\n", new Date(), message);
    }

}
