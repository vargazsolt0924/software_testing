package hu.nye.ta.stepdefinition;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class StepDefinitions {

    public void thePageIsOpened(String pageName){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(
                new ChromeOptions().addArguments("--remote-allow-origins=*")
        );

        switch (pageName){
            case "Articles" -> driver.get("https://wearecommunity.io/articles");
            case "Main" -> driver.get("https://wearecommunity.io/");
        }

    }
}
