import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomationTest1 {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //implicit method
    }
    @Test
    @DisplayName("Check if sucessful submission message is showing properly")
    public void formFillUp() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        try {
            WebElement cookieBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (cookieBtn.isDisplayed()) {
                cookieBtn.click();
            }
        } catch (Exception e) {

        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.findElement(By.id("edit-name")).sendKeys("Test User");
        driver.findElement(By.id("edit-number")).sendKeys("01787654981");
        WebElement dateInput = driver.findElement(By.id("edit-date"));
        JavascriptExecutor jns = (JavascriptExecutor) driver;
        jns.executeScript("arguments[0].value='2025-04-17'; arguments[0].dispatchEvent(new Event('change'));", dateInput);

        driver.findElement(By.id("edit-email")).sendKeys("test@email.com");

        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hi I am Labonno and I am a student of Road to SDET");

        driver.findElement(By.id("edit-uploadocument-upload")).
                sendKeys(System.getProperty("user.dir")+"/src/test/resources/uploadFile.png");

        driver.findElement(By.id("edit-age")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("edit-submit")).click();
        Thread.sleep(3000);

        String submitMsg= driver.findElement(By.tagName("h1")).getText();
        String actualMsg="Thank you for your submission!";
        Assertions.assertTrue(submitMsg.contains(actualMsg));

    }
}
