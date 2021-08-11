package org.example.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//div[@class='product-buy__price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//div[@class='product-buy__price product-buy__price_active']")
    private WebElement productPriceWithGuarantee;

    @FindBy(xpath = "//div[text()='Гарантия: 12 мес.']")
    private WebElement guarantee;

    @FindBy(xpath = "//input[@value='1']/..")
    private WebElement guaranteeTwoYear;

    @FindBy(xpath = "//button[text()='Купить']")
    private WebElement buyBtn;

    @FindBy(xpath="//nav[@id='header-search']//input[@placeholder='Поиск по сайту']")
    private WebElement inputSearch;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cartLink;

    @FindBy(xpath = "//h1[@data-product-title]")
    private WebElement dataProductTitle;

    public ProductPage rememberNintendoSwitchPrice() {
        int price = Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""));
        nintendoSwitch.setPrice(price);

        return this;
    }

    public ProductPage rememberNintendoSwitchPriceWithGuarantee() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='цена изменена']")));

        int price = Integer.parseInt(productPriceWithGuarantee.getText().replaceAll("[^0-9]", ""));
        nintendoSwitch.setPriceWithGuarantee(price);

        System.out.println(nintendoSwitch.getPrice());
        System.out.println(nintendoSwitch.getPriceWithGuarantee());

        return this;
    }

    public ProductPage rememberDetroitPrice() {
        int price = Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""));
        detroit.setPrice(price);
        System.out.println(detroit.getPrice());

        return this;
    }

    public ProductPage checkCartSum() throws InterruptedException {
        Thread.sleep(1000);
        int cartSum = Integer.parseInt(cartLink.getText().replaceAll("[^0-9]", ""));

        int productsSum = nintendoSwitch.getPriceWithGuarantee() + detroit.getPrice();

        Assertions.assertEquals(productsSum, cartSum, "Цены разные");
        return this;
    }

    public ProductPage selectAdditionalGuarantee() {

        guarantee.click();
        guaranteeTwoYear.click();

        return this;
    }

    public ProductPage clickBuyBtn() {
        buyBtn.click();
        return this;
    }

    public ResultSearchPage searchProduct(String productName) {
        inputSearch.click();

        inputSearch.sendKeys(productName);
        inputSearch.sendKeys(Keys.ENTER);

        return pageManager.getResultSearchPage();
    }

    public CartPage goToCart() {
        cartLink.click();

        return pageManager.getCartPage();
    }


    public ProductPage checkOpenProductPage() {
        Assertions.assertNotNull(dataProductTitle, "Мы находимся не на странице товара");

        return this;
    }


}
