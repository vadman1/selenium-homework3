package org.example.framework.basetestclass;

import org.example.framework.managers.DriverManager;
import org.example.framework.managers.InitManager;
import org.example.framework.managers.PageManager;
import org.example.framework.managers.TestPropManager;
import org.example.framework.utils.MyAllureListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.framework.utils.PropConst.BASE_URL;


@ExtendWith(MyAllureListener.class )
public class BaseTests {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }


}
