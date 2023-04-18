package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Conf;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import ui.pages.home.HomePage;

import java.util.logging.Level;

@Execution(ExecutionMode.CONCURRENT)
public class BaseUITests{

    static {
        Configuration.driverManagerEnabled = Conf.browserConf().getDriverManager();
        Configuration.pollingInterval = Conf.browserConf().getPollingInterval();
        Configuration.timeout = Conf.browserConf().getTimeout();
        Configuration.browserSize = Conf.browserConf().getBrowserSize();
        SelenideLogger.addListener("AllureListener", new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL));
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--disable-dev-shm-usage");
        Configuration.browser = Conf.browserConf().getBrowser();
    }

    @BeforeEach
    public void before() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        System.setProperty("selenide.browser", "ui.CustomWebDriverProvider");
    }

    @Step
    public HomePage openHomePage() {
        Selenide.open(Conf.core().getUiUrl());
        return new HomePage();
    }

    @AfterEach
    public void tearDownBrowser() {
        WebDriverRunner.closeWebDriver();
        String testId = Allure.getLifecycle().getCurrentTestCase().get();
        BrowserWebDriverContainer container = ContainerHolder.getContainers(testId);
        if (container != null) {
            container.stop();
        }
    }
}
