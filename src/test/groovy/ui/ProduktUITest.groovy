package ui

import cz.upce.eshop.EshopApplication;
import cz.upce.eshop.dataFactory.Creator;
import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.repository.ProduktRepository;
import cz.upce.eshop.ui.TestImplementation
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import([Creator.class])
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

        creator.saveEntities(
                new Produkt(nazev: "produkt1"),
                new Produkt(nazev: "produkt2"),
                new Produkt(nazev: "produkt3"),
        );

        driver.get("http://localhost:8080/");

        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='produkt1']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='produkt2']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='produkt3']")).size());
    }

}
