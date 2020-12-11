package lesson19;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.StringReader;

public class AjaxTest {

    protected static IndexPage indexPage;
    protected static WebDriver driver;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void firstTest() throws InterruptedException {
        indexPage = new IndexPage(driver);
        driver.get(indexPage.url);

        String actual = indexPage.Example5Click().GetResultText();

        String response = RestAssured.given().contentType(ContentType.JSON).log().everything()
                .when().get("https://ruseller.com/lessons/les1452/demo/response.php?action=sample5")
                .then().log().everything().extract().response().asString();

        StringReader reader = new StringReader(response);
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(reader, User.class);
            String expected = user.toString();
            Assert.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
