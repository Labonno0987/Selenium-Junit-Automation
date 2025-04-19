import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomationTest2 {
    WebDriver driver;
    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Check if user is registered successfully.")

    public void userRegistration() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        //name
        driver.findElement(By.id("first_name")).sendKeys("Labonno");
        driver.findElement(By.id("last_name")).sendKeys("Ahmed");
//email generate
        Random random = new Random();
        int rdNumber = random.nextInt(1000);
        String randomEmail = "user" + rdNumber + "@gmail.com";
        driver.findElement(By.id("user_email")).sendKeys(randomEmail);
//gender
        driver.findElement(By.id("radio_1665627729_Female")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100)");
//pass
        driver.findElement(By.id("user_pass")).sendKeys("ABCabc12345$");
        //date of Birth
        WebElement datePicker = driver.findElement(By.cssSelector("input[data-id='date_box_1665628538']"));
        datePicker.click();

        // Select September
        Select month = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));
        month.selectByValue("8"); // September is 8
        Thread.sleep(1000);

        // Select 1999
        WebElement year = driver.findElement(By.className("cur-year"));
        year.click();
        year.clear();
        year.sendKeys("1999");
        year.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // Select September 26
        List<WebElement> days = driver.findElements(By.cssSelector("span.flatpickr-day"));
        for (WebElement day : days) {
            if (day.getAttribute("aria-label").equals("September 26, 1999")) {
                day.click();
                break;
            }
        }
//nationality
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");
//country
        Select option=new Select(driver.findElement(By.id("country_1665629257")));
        option.selectByVisibleText("Bangladesh");
//Phn no
        List<WebElement> phnNum=driver.findElements(By.id("phone_1665627880"));
        phnNum.get(1).sendKeys("0123456789");
        Thread.sleep(1000);

        //Scrolling.scroll(driver,800);
        JavascriptExecutor jns = (JavascriptExecutor) driver;
        jns.executeScript("window.scrollBy(0, 500)");

        //privacy
        driver.findElement(By.id("privacy_policy_1665633140")).click();
        Thread.sleep(1000);
//submit
        driver.findElement(By.className("ur-submit-button")).click();

        String submitMsg= driver.findElement(By.id("ur-submit-message-node")).getText();
        String expectedMsg="User successfully registered.";

        Assertions.assertTrue(submitMsg.contains(expectedMsg));

    }
}
