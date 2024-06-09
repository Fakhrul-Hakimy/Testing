package Admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class SearchPaper {

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
    public void testSearchPaper() throws InterruptedException {
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
        String ActualTitle = alert.getText();

        // Click OK on Alert Dialog
        alert.accept();
        Thread.sleep(1000);
        
        // Expect Text
        String ExpectedTitle = "Login Successful";
        
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
        
        WebElement counterElement = driver.findElement(By.xpath("/html/body/div[2]/div[3]/b"));

        // Get the text content of the counter element
        String counterText = counterElement.getText();

        // Use regular expression to check if the text contains any number
        assertTrue("Counter text contains a positive integer", counterText.matches(".*[1-9]\\d*.*"));
    }

    @After
    public void tearDown() {
        // Close the driver
        if (driver != null) {
            driver.quit();
        }
    }

   
}
