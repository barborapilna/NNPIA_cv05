package cz.upce.eshop.ui;

import cz.upce.eshop.dataFactory.Creator;
import cz.upce.eshop.repository.ProduktRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class ProduktUITest {

    private WebDriver driver;

    @Autowired
    private Creator creator;

    @Autowired
    private ProduktRepository produktRepository;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {

        String path = TestImplementation.class.getResource("/chromedriver.exe").getFile();
        try {
            String chromeDriverPath = URLDecoder.decode(path, "UTF-8");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        } catch (UnsupportedEncodingException e) {

        }
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        produktRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void produktyList() {
        driver.get("/");
        driver.findElement(By.id("nazev")).sendKeys("produkt1");
        driver.findElement(By.id("nazev")).sendKeys("produkt2");
        driver.findElement(By.id("nazev")).sendKeys("produkt3");
        driver.findElement(By.id("popis")).sendKeys("super ostre nuzky");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Nase Produkty']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='nuzky']")).size());
    }

    @Test
    public void addOneProductTest() {
        driver.get("http://localhost:8080/produkt-form");
        driver.findElement(By.id("nazev")).sendKeys("nuzky");
        driver.findElement(By.id("popis")).sendKeys("super ostre nuzky");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Nase Produkty']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='nuzky']")).size());
    }
}
