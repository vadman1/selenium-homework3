package org.example.framework.pages;

import io.qameta.allure.Step;
import org.example.framework.dataobject.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[@class='cart-items__product-name-link']")
    private List<WebElement> listProductName;

    @FindBy(xpath = "//span[@class='restore-last-removed']")
    private WebElement btnRestoreLastRemoved;

    @FindBy(xpath = "//h1[@class='cart-title']")
    private WebElement cartTitle;

    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> cartProductList;

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    private WebElement cartLinkBadge;



    @Step("Удаляем товар {nameProduct} из корзины")
    public CartPage deleteProduct(String nameProduct) {
        for (WebElement productName : listProductName) {
            if (productName.getText().trim().equalsIgnoreCase(nameProduct)) {

                WebElement deleteProductBtn = productName.findElement(By
                        .xpath("./../..//button[@class='menu-control-button' and text()='Удалить']"));
                scrollWithOffset(deleteProductBtn, 0, -200);
                waitUtilElementToBeVisible(deleteProductBtn);
                waitUtilElementToBeClickable(deleteProductBtn).click();

                Product.setCountProduct(Product.getCountProduct() - 1);

                wait.until(ExpectedConditions.textToBePresentInElement(cartLinkBadge, "" + Product.getCountProduct()));
                return this;
            }
        }
        return this;
    }


    @Step("Увеличиваем количество товара {nameProduct}")
    public CartPage addProduct(String nameProduct) {
        for (WebElement productName : listProductName) {
            if (productName.getText().trim().equalsIgnoreCase(nameProduct)) {

                WebElement plusBtn = productName
                        .findElement(By.xpath("./../../../../..//button[contains(@class, 'button_plus')]"));


                driverManager.getDriver().findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.HOME);

                WebElement countButtonInput = productName
                        .findElement(By.xpath("./../../../../..//input[@class='count-buttons__input']"));

                int countItemProduct = Integer.parseInt(countButtonInput.getAttribute("value"));


                js.executeScript("arguments[0].click();", plusBtn);


                Product.setCountProduct(Product.getCountProduct() + 1);
                wait.until(ExpectedConditions.textToBePresentInElement(cartLinkBadge, Product.getCountProduct() + ""));
                Assertions.assertEquals(Product.getCountProduct() + "", cartLinkBadge.getText(),
                        "Количество товара в корзине и в классе Product не совпадает");
                Assertions.assertEquals(countItemProduct + 1 + "", countButtonInput.getAttribute("value"),
                        "Количество товара на странице не совпадает со значением countItemProduct");

                return this;
            }
        }
        return this;
    }

    @Step("Восстанавливаем последний удалённый товар")
    public CartPage restoreLastRemoved() {
        js.executeScript("arguments[0].click();", btnRestoreLastRemoved);

        Product.setCountProduct(Product.getCountProduct() + 1);

        wait.until(ExpectedConditions.textToBePresentInElement(cartLinkBadge, Product.getCountProduct() + ""));
        Assertions.assertEquals(Product.getCountProduct() + "", cartLinkBadge.getText(),
                "Количество товаров в Product и на странице не совпадает");

        return this;
    }


    @Step("Проверяем у товара {productName}, что выбрана гарантия {nameGuarantee}")
    public CartPage checkSelectedGuaranteeByProductName(String productName, String nameGuarantee) {
        AtomicBoolean flag = new AtomicBoolean(false);
        cartProductList.forEach(item -> {
            WebElement title = item.findElement(By.xpath(".//a[contains(@class, 'product-name')]"));
            if (title.getText().contains(productName)) {

                List<WebElement> listGuarantee = item.findElements(By
                        .xpath(".//span[contains(@class,'base-ui-radio-button__icon')]"));
                listGuarantee.forEach(itemGuarantee -> {
                    if (itemGuarantee.getAttribute("class").contains("checked")) {
                        Assertions.assertTrue(itemGuarantee.getText().contains(nameGuarantee));
                        flag.set(true);
                        return;
                    }
                });
                if(flag.get() == true) {
                    return;
                }
                Assertions.fail("У продукта не выбрана гарантия\n " +
                        "Ожидали, что у продукта будет выбрана гарантия " + nameGuarantee);
                return;
            }
        });
        if(flag.get() == true) return this;
        Assertions.fail("Продукт в корзине не был найден");
        return this;
    }

    @Step("Проверяем открытие страницы Корзины")
    public CartPage checkOpenCartPage() {
        Assertions.assertEquals(cartTitle.getText(),
                "Корзина", "Мы находимся не на странице Корзины");

        return this;
    }
}
