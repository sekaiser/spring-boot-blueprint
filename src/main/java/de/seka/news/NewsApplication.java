package de.seka.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Configuration class.
 */
@SpringBootApplication
public class NewsApplication {

    /**
     * Protected constructor.
     */
    protected NewsApplication() {
    }

    /**
     * Spring Boot Main.
     *
     * @param args Program arguments
     * @throws Exception For any failure during program execution
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(NewsApplication.class, args);
    }

}

