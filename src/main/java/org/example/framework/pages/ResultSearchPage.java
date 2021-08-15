package org.example.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultSearchPage extends BasePage{

    @FindBy(xpath = "//div[@data-id='product']//a[contains(@class, 'product__name')]/span")
    private List<WebElement> listCardProduct;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement title;

    @Step("Кликаем по карточке товара {nameProduct}")
    public ProductPage clickOnCardProduct(String nameProduct) {

        for (WebElement product : listCardProduct) {
            if(product.getText().trim().contains(nameProduct)){
                product.click();
                return pageManager.getProductPage();
            }
        }

        Assertions.fail("Товар '" + nameProduct + "' не найден в списке товаров");

        return pageManager.getProductPage();
    }

    @Step("Проверяем открытие страницы с результатами поиска")
    public ResultSearchPage checkOpenResultSearchPage() {
        Assertions.assertEquals(title.getText(), "Найдено:",
                "Мы находимся не на странице с результатами поиска");

        return this;
    }

}
