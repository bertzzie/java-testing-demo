package tech.namas.demo.tests.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.namas.demo.tests.demo.properties.WebClientProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    WebClientProperties.class
})
public class TestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDemoApplication.class, args);
    }

}
