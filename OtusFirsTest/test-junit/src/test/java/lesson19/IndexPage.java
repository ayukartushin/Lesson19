package lesson19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class IndexPage {

    public String url = "https://ruseller.com/lessons/les1452/demo/index.html";

    @FindBy(css = ".sample1")
    private WebElement Example1;
    @FindBy(css = ".sample2")
    private WebElement Example2;
    @FindBy(css = ".sample3")
    private WebElement Example3;
    @FindBy(css = ".sample4")
    private WebElement Example4;
    @FindBy(css = ".sample5")
    private WebElement Example5;

    @FindBy(css = ".results")
    public WebElement Result;

    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public IndexPage Example1Click() throws InterruptedException {
        Example1.click();
        sleep(600);
        return this;
    }

    public IndexPage Example2Click() throws InterruptedException {
        Example2.click();
        sleep(600);
        return this;
    }

    public IndexPage Example3Click() throws InterruptedException {
        Example3.click();
        sleep(600);
        return this;
    }

    public IndexPage Example4Click() throws InterruptedException {
        Example4.click();
        sleep(600);
        return this;
    }

    public IndexPage Example5Click() throws InterruptedException {
        Example5.click();
        sleep(600);
        return this;
    }

    public String GetResultText() {
        return Result.getText();
    }
}
