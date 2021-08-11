package org.example.framework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-additional']")
    private WebElement agreementCity;

    @FindBy(xpath="//nav[@id='header-search']//input[@placeholder='Поиск по сайту']")
    private WebElement inputSearch;

    public HomePage agreeCity() {
        agreementCity.click();
        return this;
    }

    public ResultSearchPage searchProduct(String productName) {
        inputSearch.click();

        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);

        return pageManager.getResultSearchPage();
    }

}
