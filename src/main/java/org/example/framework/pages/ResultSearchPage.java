package org.example.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultSearchPage extends BasePage{

    @FindBy(xpath = "//div[@data-id='product']//a[contains(@class, 'product__name')]")
    private List<WebElement> listCardProduct;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement title;

    public ProductPage clickOnCardProduct() {
        listCardProduct.get(0).click();

        return pageManager.getProductPage();
    }

    public ResultSearchPage checkOpenResultSearchPage() {
        Assertions.assertEquals(title.getText(), "Найдено:",
                "Мы находимся не на странице с результатами поиска");

        return this;
    }

}
