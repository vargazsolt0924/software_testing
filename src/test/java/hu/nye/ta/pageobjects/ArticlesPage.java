package hu.nye.ta.pageobjects;

import hu.nye.ta.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticlesPage {

    @FindBy(css = ".evnt-search-filter .form-control.evnt-search")
    private WebElement searchInput;

    @FindBy(css = ".evnt-articles-wrapper .evnt-card-wrapper")
    private List<WebElement> articleCards;

    @FindBy(css = ".evnt-articles-wrapper .evnt-article-name")
    private List<WebElement> articleCardTitles;


    @FindBy(id = "filter_tag")
    private WebElement tagFilter;

    @FindBy(css = ".evnt-filter-menu.show .evnt-search")
    private WebElement tagFilterInput;

    @FindBy(css = ".highlight-text")
    private WebElement tagFilterHighlightedItem;

    @FindBy(xpath = "//*[@id=\"agenda_filters\"]/div/div[1]/div[1]/div/div/div[4]")
    private WebElement moreFiltersOption;

    @FindBy(id = "filter_language")
    private WebElement languageFilter;

    @FindBy(css = ".evnt-filter-item")
    private WebElement languageFilterCheckBox;


    private final WebDriver webDriver;

    public ArticlesPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchForInput(String input) {
        searchInput.sendKeys(input);
    }

    public List<WebElement> getArticleCards() {
        return articleCards;
    }

    public List<String> getArticleCardTitles() {
        return articleCardTitles.stream()
                .map(WebElement::getText).toList();
    }

    public WebElement getTagFilter() {
        return tagFilter;
    }

    public WebElement getTagFilterInput() {
        return tagFilterInput;
    }

    public WebElement getTagFilterHighlightedItem() {
        return tagFilterHighlightedItem;
    }

    public WebElement getMoreFiltersOption() {
        return moreFiltersOption;
    }

    public WebElement getLanguageFilter() {
        return languageFilter;
    }

    public WebElement getLanguageFilterCheckBox() {
        return languageFilterCheckBox;
    }


}
