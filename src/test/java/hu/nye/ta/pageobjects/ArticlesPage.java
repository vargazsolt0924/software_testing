package hu.nye.ta.pageobjects;

import hu.nye.ta.factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticlesPage {

    @FindBy(css = ".evnt-search-filter .form-control.evnt-search")
    private WebElement searchInput;

    @FindBy(css = ".evnt-articles-wrapper .evnt-card-wrapper")
    private List<WebElement> articleCards;

    @FindBy(css = ".evnt-articles-wrapper .evnt-article-name")
    private List<WebElement> articleCardTitles;

    private WebDriver webDriver;

    private WebDriverFactory webDriverFactory;

    public ArticlesPage(WebDriverFactory webDriverFactory) {
        var webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void searchForInput(String input){
        searchInput.sendKeys(input);
    }

    public List<WebElement> getArticleCards() {
        return articleCards;
    }

    public List<String> getArticleCardTitles(){
        List<String> cardTitles = new ArrayList<>();
        for (WebElement card: articleCardTitles) {
            cardTitles.add(card.getText());
        }
        return cardTitles;
    }
}
