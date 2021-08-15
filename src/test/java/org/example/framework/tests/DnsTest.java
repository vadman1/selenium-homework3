package org.example.framework.tests;

import org.example.framework.basetestclass.BaseTests;
import org.junit.jupiter.api.Test;

public class DnsTest extends BaseTests {


    @Test
    public void startTest() {
        app.getHomePage()
                .agreeCity()
                .searchProduct("Nintendo Switch")
                .checkOpenResultSearchPage()
                .clickOnCardProduct("Nintendo Switch")
                .checkOpenProductPage()
                .rememberProductPrice("Nintendo Switch")
                .selectAdditionalGuarantee("+ 24 мес.")
                .rememberProductPriceWithGuarantee("Nintendo Switch")
                .clickBuyBtn()
                .searchProduct("Detroit")
                .checkOpenResultSearchPage()
                .clickOnCardProduct("Detroit")
                .checkOpenProductPage()
                .rememberProductPrice("Detroit")
                .clickBuyBtn()
                .checkCartSum()
                .goToCart()
                .checkOpenCartPage()
                .checkSelectedGuaranteeByProductName("Nintendo Switch", "24")
                .deleteProduct("Игра Detroit: Стать человеком (PS4)")
                .addProduct("Игровая консоль Nintendo Switch 32 GB Neon Red/Blue")
                .addProduct("Игровая консоль Nintendo Switch 32 GB Neon Red/Blue")
                .restoreLastRemoved();
    }

}
