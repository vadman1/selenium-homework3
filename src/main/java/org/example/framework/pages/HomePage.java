package org.example.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-additional']")
    private WebElement agreementCity;

    @FindBy(xpath="//nav[@id='header-search']//input[@placeholder='Поиск по сайту']")
    private WebElement inputSearch;


    @Step("Соглашаемся с текущим городом")
    public HomePage agreeCity() {
        driverManager.getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try {
            agreementCity.click();
        } catch (NoSuchElementException ignore) {
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        return this;
    }

    @Step("Выбираем товар {productName}")
    public ResultSearchPage searchProduct(String productName) {
        inputSearch.click();

        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);

        return pageManager.getResultSearchPage();
    }

}
