package hu.nye.ta.stepdefinition;

import hu.nye.ta.factory.WebDriverFactory;
import hu.nye.ta.pageobjects.ArticlesPage;
import hu.nye.ta.pageobjects.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
            case "Articles" -> driver.get("https://wearecommunity.io/articles");
            case "Main" -> driver.get("https://wearecommunity.io/");
        }

    }

    @When("I search for {string}")
    public void iSearchFor(String input){
        articlesPage.searchForInput(input);
    }

    @Then("I see {int} article card")
    public void iSeeArticleCard(int expectedCardCount) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            var actualCardCount = articlesPage.getArticleCards().size();
            if(actualCardCount == expectedCardCount){
                return;
            }
            Thread.sleep(1000);
        }
        Assert.fail("Expected card count did not match with actual card count.");
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
            if(!title.contains(word)){
                Assert.fail();
            }
        }
    }
}
