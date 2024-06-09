package Admin;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class AddReviewer {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\chromedriver-win64\\chromedriver.exe");

        // ChromeOptions to handle any issues related to browser version or capabilities
        ChromeOptions options = new ChromeOptions();

        // Instantiate the ChromeDriver with options
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testAddReviewer() throws InterruptedException {
        // Open a website
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        Thread.sleep(1000);

        // Wait for the page to load (optional, can use WebDriverWait for better control)
        Thread.sleep(2000);  // Simple sleep, replace with WebDriverWait if necessary

        // Print the title of the page
        System.out.println("Title of the page is: " + driver.getTitle());

        driver.findElement(By.xpath("//*[@id='idemail']")).sendKeys("sandbox@soc-conferences.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='idpass']")).sendKeys("Abc123");
        Thread.sleep(1000);

        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='idconf']")));
        Thread.sleep(1000);

        // Select SANDBOX24
        dropdown.selectByVisibleText("SANDBOX24");
        Thread.sleep(1000);

        // Select Remember Me
        driver.findElement(By.xpath("//*[@id='idremember']")).click();
        Thread.sleep(1000);

        // Click OK on Alert Dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);

        // Click Login
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/form/p[5]/button")).click();
        Thread.sleep(1000);

        // Get Text From Alert Dialog
        String actualTitle = alert.getText();

        // Click OK on Alert Dialog
        alert.accept();
        Thread.sleep(1000);

        // Expect Text
        String expectedTitle = "Login Successful";
        assertEquals(expectedTitle, actualTitle);

        driver.findElement(By.xpath("/html/body/nav/div[2]/a[4]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[3]/a/button")).click();
        Thread.sleep(1000);

        alert.accept();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[1]/div[2]/div")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/table/tbody/tr[2]/td[2]/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/table/tbody/tr[3]/td[2]/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
        Thread.sleep(1000);

        dropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[2]/div[1]/select")));
        Thread.sleep(1000);

        // Select Dr
        dropdown.selectByVisibleText("Dr");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idname']")).sendKeys("Poh");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idemail']")).sendKeys("Poh@gmail.com");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idphone']")).sendKeys("011-1398983");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idorg']")).sendKeys("UUM");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idabstract']")).sendKeys("Sintok, Kedah, UUM");
        Thread.sleep(1000);
        
        dropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[4]/div[1]/select")));
        Thread.sleep(1000);

        dropdown.selectByVisibleText("Malaysia");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='idsubmit']")).click();
        Thread.sleep(1000);

        alert.accept();
        Thread.sleep(1000);

        alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Thread.sleep(2000);

        // Assertion
        assertEquals("Registration Success. An email has been sent to the reviewer.", alertMessage);

        // Accept the alert
        alert.accept();
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        // Close the driver
        if (driver != null) {
            driver.quit();
        }
    }

   
}
