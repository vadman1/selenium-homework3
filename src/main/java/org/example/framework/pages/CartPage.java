package org.example.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy(xpath = "//a[@class='cart-items__product-name-link']")
    private List<WebElement> listProductName;

    @FindBy(xpath = "//span[@class='restore-last-removed']")
    private WebElement btnRestoreLastRemoved;

    @FindBy(xpath = "//h1[@class='cart-title']")
    private WebElement cartTitle;

    public CartPage deleteProduct(String nameProduct) {
        for (WebElement productName : listProductName) {
            if(productName.getText().trim().equalsIgnoreCase(nameProduct)){
                productName.findElement(By.xpath("./../..//button[text()='Удалить']")).click();
                return this;
            }
        }
        return this;
    }


    public CartPage addProduct(String nameProduct) throws InterruptedException {
        for (WebElement productName : listProductName) {
            if(productName.getText().trim().equalsIgnoreCase(nameProduct)){

                WebElement plusBtn = productName
                        .findElement(By.xpath("./../../../../..//button[contains(@class, 'button_plus')]"));


                driverManager.getDriver().findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.HOME);
                Thread.sleep(2000);
                js.executeScript("arguments[0].click();", plusBtn);

                return this;
            }
        }
        return this;
    }

    public CartPage restoreLastRemoved() {
        js.executeScript("arguments[0].click();", btnRestoreLastRemoved);

        return this;
    }


    public CartPage waitUpdatePrice(String expectedPrice) {
        WebElement currentPrice = driverManager.getDriver().findElement(By
                .xpath("//a[contains(text(), 'Nintendo')]/../../../../..//span[@class='price__current']"));

        wait.until(ExpectedConditions.textToBePresentInElement(currentPrice, expectedPrice));

        return this;
    }

    public CartPage checkGuaranteeTwoYear() {
        WebElement guaranteeTwoYear = driverManager.getDriver().findElement(By
                .xpath("//span[contains(@class, 'checked') and contains(text(), '+ 24  мес.')]"));

        Assertions.assertNotNull(guaranteeTwoYear, "Элемента нет");

        return this;
    }

    public CartPage checkOpenCartPage() {
        Assertions.assertEquals(cartTitle.getText(),
                "Корзина", "Мы находимся не на странице Корзины");

        return this;
    }
}
