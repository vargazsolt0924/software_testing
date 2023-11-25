package hu.nye.ta.config;

import hu.nye.ta.factory.WebDriverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.nye.ta")
public class TestConfig {

    @Bean(destroyMethod = "tearDown")
    WebDriverFactory webDriverFactory(){
        return new WebDriverFactory();
    }
}
