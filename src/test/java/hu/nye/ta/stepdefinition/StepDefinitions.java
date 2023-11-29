package hu.nye.ta.stepdefinition;

import hu.nye.ta.factory.WebDriverFactory;
import hu.nye.ta.pageobjects.ArticlesPage;
import hu.nye.ta.pageobjects.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;

import static hu.nye.ta.helpers.Addresses.ARTICLES_PAGE;
import static hu.nye.ta.helpers.Addresses.MAIN_PAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class StepDefinitions {

    @Autowired
    ArticlesPage articlesPage;

    @Autowired
    MainPage mainPage;

    @Autowired
    WebDriverFactory webDriverFactory;

    @Given("the {string} site is opened")
    public void thePageIsOpened(String pageName){
        WebDriver driver = webDriverFactory.getDriver();

        switch (pageName){
            case "Articles" -> driver.get(ARTICLES_PAGE);
            case "Main" -> driver.get(MAIN_PAGE);
        }

    }

    @When("I search for {string}")
    public void iSearchFor(String input){
        articlesPage.searchForInput(input);
    }

    @Then("I see {int} article card")
    public void iSeeArticleCard(int expectedCardCount) throws InterruptedException {
        WebDriver driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        try{
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver){
                    return articlesPage.getArticleCards().size() == expectedCardCount;
                }
            });
        }catch (TimeoutException e){
            Assert.fail("Expected card count did not match actual card count");
        }
    }

    @Given("the {string} button is clicked")
    public void theArticlesButtonIsClicked(String buttonName) {
        switch (buttonName){
            case "Articles" -> mainPage.getArticlesButton().click();
        }
    }

    @And("All cards contain the {string} word")
    public void allCardsContainTheCardNameWord(String word) {
        for(String title : articlesPage.getArticleCardTitles()){
            assertThat(title.toLowerCase(), containsString(word.toLowerCase()));
        }
    }

    @When("I narrow the tag to {string}")
    public void iNarrowTheTagToBanks(String tag) {
        articlesPage.getTagFilter().click();
        articlesPage.getTagFilterInput().sendKeys(tag);
    }

    @And("I click the highlighted checkbox")
    public void iClickTheHighlightedCheckbox() {
        articlesPage.getTagFilterHighlightedItem().click();
    }

    @Then("the {string} option is opened")
    public void theMoreFiltersOptionIsOpened(String buttonName) {
        switch (buttonName){
            case "More Filters" -> articlesPage.getMoreFiltersOption().click();
        }
    }

    @And("the Language Filter Dropdown is opened")
    public void theFilterDropdownIsOpened() throws InterruptedException {
        Thread.sleep(1000);
        articlesPage.getLanguageFilter().click();
    }

    @When("I select the {string} checkbox")
    public void iSelectTheLanguageCheckBox(String language) throws InterruptedException {
        WebDriver driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        List<WebElement> checkboxes = articlesPage.getCheckBoxName();

        boolean checkboxFound = false;

        for (WebElement checkbox : checkboxes) {
            wait.until(ExpectedConditions.visibilityOf(checkbox));

            String actualCheckboxName = checkbox.getText();
            System.out.println("Actual Checkbox Name: " + actualCheckboxName);

            if (actualCheckboxName.toLowerCase().contains(language.toLowerCase())) {
                checkbox.click();
                checkboxFound = true;
                break;
            }
        }

        if (!checkboxFound) {
            Assert.fail("Checkbox for language " + language + " not found or cannot be selected");
        }
    }
}
