package org.example.framework.managers;


import org.example.framework.pages.CartPage;
import org.example.framework.pages.HomePage;
import org.example.framework.pages.ProductPage;
import org.example.framework.pages.ResultSearchPage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    //объявить каждую страничку полем

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страница с результатом поиска
     */
    private ResultSearchPage resultSearchPage;

    /**
     * Страница продукта
     */
    private ProductPage productPage;

    /**
     * Страница корзины
     */
    private CartPage cartPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }


    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link ResultSearchPage}
     *
     * @return ResultSearchPage
     */
    public ResultSearchPage getResultSearchPage() {
        if (resultSearchPage == null) {
            resultSearchPage = new ResultSearchPage();
        }
        return resultSearchPage;
    }


    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if(cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

}
