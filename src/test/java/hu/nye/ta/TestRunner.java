package hu.nye.ta;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"hu.nye.ta"},
        plugin = {"pretty"}
)
public class TestRunner {
}
