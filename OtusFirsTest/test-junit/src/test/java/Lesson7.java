
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;

public class Lesson7 {
    protected static WebDriver driver;
/*    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }*/

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*@Test
    public void Lesson() throws IOException {
        // запуск прокси сервера
        ProxyServer server = new ProxyServer(4444);
        server.start();

        // получение Selenium proxy
        Proxy proxy = server.seleniumProxy();

        // конфигурация FirefoxDriver для использования прокси
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);


        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(capabilities);

        // создание HAR с меткой "yandex.ru"
        server.newHar("yandex.ru");

        // открытие страницы
        driver.get("http://yandex.ru");

        // получение данных HAR
        Har har = server.getHar();

        // здесь будет обработка полученных данных

        driver.quit();
        server.stop();

    }*/

    @Test
    public void JS(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://otus.ru");
        String js = "var i = 2+2;";
        String js2 = "return i;";
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(js);
        String i = jse.executeScript(js2).toString();

    }

    @Test
    public void bmpTest() throws Exception {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(4444);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);


        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(capabilities);


        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);


        // создание HAR с меткой
        proxy.newHar("example");

        // открытие страницы
        driver.get("http://internetka.in.ua");
        //
        //new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe("http://internetka.in.ua"));

        // получение данных HAR
        Har har = proxy.getHar();

        // здесь будет обработка полученных данных
        // например, сохранение файл
        proxy.getHar().writeTo(new FileWriter("Test.har", true));

        driver.quit();
        proxy.stop();
    }
}
