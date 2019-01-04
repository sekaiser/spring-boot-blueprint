package de.seka.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Reusable Spring Boot test application.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class NewsTestApplication {

    /**
     * Constructor.
     */
    protected NewsTestApplication() {
    }

    /**
     * Spring Boot Main.
     *
     * @param args Program arguments
     * @throws Exception For any failure during program execution
     */
    public static void main(final String[] args) throws Exception {
        new SpringApplication(NewsTestApplication.class).run(args);
    }
}
