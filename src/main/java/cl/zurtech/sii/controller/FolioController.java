package cl.zurtech.sii.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/folio")
@AllArgsConstructor
public class FolioController {


    //private final WebDriver driver;

    @GetMapping()
    public void getFolios() {

        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
        System.setProperty("java.awt.headless", "false");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(option);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10l));  // you can reuse this one

            driver.navigate().to("https://maullin.sii.cl/cvc_cgi/dte/of_solicita_folios");
            Alert alert = driver.switchTo().alert();
            wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            Thread.sleep(2000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            WebElement rut = driver.findElement(By.name("RUT_EMP"));
            wait.until(ExpectedConditions.visibilityOf(rut));
            rut.sendKeys("76857295");

            WebElement dv = driver.findElement(By.name("DV_EMP"));
            //wait.until(ExpectedConditions.visibilityOf(dv));
            dv.sendKeys("k");

            driver.findElement(By.name("ACEPTAR")).click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("COD_DOCTO"))));
            Select drpDocument = new Select(driver.findElement(By.name("COD_DOCTO")));

            //parameter document type
            drpDocument.selectByValue("39");

            //parameter
            WebElement documentAmount = driver.findElement(By.name("CANT_DOCTOS"));
            wait.until(ExpectedConditions.visibilityOf(documentAmount));
            documentAmount.sendKeys("5");

            // solictar numeracion
            WebElement accept1 = driver.findElement(By.name("ACEPTAR"));
            wait.until(ExpectedConditions.visibilityOf(accept1));
            accept1.click();

            // obtener folios
            WebElement getFoliosButton = driver.findElement(By.name("ACEPTAR"));
            wait.until(ExpectedConditions.visibilityOf(getFoliosButton));
            getFoliosButton.click();

            // download folios
            WebElement downloadButton = driver.findElement(By.name("ACEPTAR"));
            wait.until(ExpectedConditions.visibilityOf(downloadButton));
            downloadButton.click();



        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }

}
