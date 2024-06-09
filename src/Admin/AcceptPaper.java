package Admin;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;



public class AcceptPaper {

    private WebDriver driver;
    private String originalWindow;

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
    public void testAcceptPaper() throws InterruptedException {
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

        driver.findElement(By.xpath("//*[@id='mySidebar']/div[2]/a[2]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/form/div[1]/input")).sendKeys("Qil");
        Thread.sleep(1000);

        dropdown = new Select(driver.findElement(By.xpath("//*[@id='idoption']")));
        Thread.sleep(1000);

        // Select Author Name
        dropdown.selectByVisibleText("Author Name");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(1000);

        // Save the current window handle (first tab)
        originalWindow = driver.getWindowHandle();

        // Click the link that opens a new tab
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/form/table/tbody/tr[2]/td[9]/a")).click();
        Thread.sleep(3000);

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the new window handle (second tab)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        driver.manage().window().maximize();

        // It will wait for a maximum of 10 seconds for each object
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    

        dropdown = new Select(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a/div/div/form/p[1]/select")));
        Thread.sleep(1000);

        // Select Author Name
        dropdown.selectByVisibleText("Accepted");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a/div/div/form/p[3]/button")).click();
        Thread.sleep(3000);

        // Wait for the alert and get its text
        alert.accept();
        Thread.sleep(1000);

        alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Thread.sleep(2000);

        // Assertion
        assertEquals("Success", alertMessage);

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
