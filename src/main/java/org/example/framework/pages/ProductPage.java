package org.example.framework.pages;

import io.qameta.allure.Step;
import org.example.framework.dataobject.Product;
import org.example.framework.dataobject.datamanagers.DataManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//div[@class='product-buy__price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//div[@class='product-buy__price product-buy__price_active']")
    private WebElement productPriceWithGuarantee;

    @FindBy(xpath = "//div[text()='Гарантия: 12 мес.']")
    private WebElement guarantee;

    @FindBy(xpath = "//input[@value='1']/..")
    private WebElement guaranteeTwoYear;

    @FindBy(xpath = "//div[@class='product-warranty__items']//span[@class='product-warranty__period']")
    private List<WebElement> listGuarantee;

    @FindBy(xpath = "//button[text()='Купить']")
    private WebElement buyBtn;

    @FindBy(xpath="//nav[@id='header-search']//input[@placeholder='Поиск по сайту']")
    private WebElement inputSearch;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cartLink;

    @FindBy(xpath = "//h1[@data-product-title]")
    private WebElement dataProductTitle;

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    private WebElement cartLinkBadge;

    @Step("Запоминаем цену товара {productName}")
    public ProductPage rememberProductPrice(String productName) {

        int price = Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""));
        Product product = new Product(productName, price);

        DataManager.getDataManager().getProductList().add(product);

        return this;
    }

    @Step("Запоминаем цену с гарантией товара {nameProduct}")
    public ProductPage rememberProductPriceWithGuarantee(String nameProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='цена изменена']")));

        int price = Integer.parseInt(productPriceWithGuarantee.getText().replaceAll("[^0-9]", ""));
        DataManager.getDataManager().getProductByName(nameProduct).setPrice(price);


        return this;
    }


    @Step("Проверяем сумму корзины")
    public ProductPage checkCartSum()  {

        AtomicReference<Integer> sum = new AtomicReference<>(0);
        DataManager.getDataManager().getProductList().forEach(item -> {
            sum.updateAndGet(v -> v + item.getPrice());
        });

        int cartSum = Integer.parseInt(cartLink.getText().replaceAll("[^0-9]", ""));

        Assertions.assertEquals(sum.get(), cartSum, "Цены разные");
        return this;
    }

    @Step("Выбираем дополнительную гарантию {additionalGuarantee}")
    public ProductPage selectAdditionalGuarantee(String additionalGuarantee) {

        guarantee.click();
        for (WebElement itemGuarantee : listGuarantee) {
            if(itemGuarantee.getText().contains(additionalGuarantee)) {
                itemGuarantee.click();
                return this;
            }
        }

        Assertions.fail("Элемент не найден с такой гарантией " + additionalGuarantee);

        return this;
    }

    @Step("Нажимаем на кнопку \"Купить\"")
    public ProductPage clickBuyBtn() {
        buyBtn.click();

        wait.until(ExpectedConditions.visibilityOf(cartLinkBadge));

        Product.setCountProduct(Product.getCountProduct() + 1);
        wait.until(ExpectedConditions.textToBePresentInElement(cartLinkBadge, Product.getCountProduct() + ""));

        Assertions.assertEquals(Product.getCountProduct() + "", cartLinkBadge.getText(),
                "Количество товара в корзине и в классе Product разное");


        return this;
    }

    @Step("Ищем товар {productName}")
    public ResultSearchPage searchProduct(String productName) {
        inputSearch.click();

        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);

        return pageManager.getResultSearchPage();
    }

    @Step("Переходим в корзину")
    public CartPage goToCart() {
        cartLink.click();

        return pageManager.getCartPage();
    }

    @Step("Проверяем открытие страницы с товаром")
    public ProductPage checkOpenProductPage() {
        Assertions.assertTrue(dataProductTitle.isDisplayed(), "Мы находимся не на странице товара");
        return this;
    }


}
