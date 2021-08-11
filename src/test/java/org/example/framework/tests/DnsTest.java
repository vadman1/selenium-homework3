package org.example.framework.tests;

import org.example.framework.basetestclass.BaseTests;
import org.junit.jupiter.api.Test;

public class DnsTest extends BaseTests {

    @Test
    public void startTest() throws InterruptedException {
        app.getHomePage()
                .agreeCity()
                .searchProduct("Nintendo Switch")
                .checkOpenResultSearchPage()
                .clickOnCardProduct()
                .checkOpenProductPage()
                .rememberNintendoSwitchPrice()
                .selectAdditionalGuarantee()
                .rememberNintendoSwitchPriceWithGuarantee()
                .clickBuyBtn()
                .searchProduct("Detroit")
                .checkOpenResultSearchPage()
                .clickOnCardProduct()
                .checkOpenProductPage()
                .rememberDetroitPrice()
                .clickBuyBtn()
                .checkCartSum()
                .goToCart()
                .checkOpenCartPage()
                .checkGuaranteeTwoYear()
                .deleteProduct("Игра Detroit: Стать человеком (PS4)")
                .addProduct("Игровая консоль Nintendo Switch 32 GB Neon Red/Blue")
                .waitUpdatePrice("46 998 ₽")
                .addProduct("Игровая консоль Nintendo Switch 32 GB Neon Red/Blue")
                .waitUpdatePrice("70 497 ₽")
                .restoreLastRemoved();
    }

}
