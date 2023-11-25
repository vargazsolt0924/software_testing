package hu.nye.ta.pageobjects;

import hu.nye.ta.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class MainPage {

    @FindBy(xpath = "//*[@id=\"app\"]/header/div/div/ul[1]/li[6]/a")
    private WebElement articlesButton;

    private WebDriver webDriver;
    public MainPage(WebDriverFactory webDriverFactory) {
        var webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getArticlesButton() {
        return articlesButton;
    }
}
